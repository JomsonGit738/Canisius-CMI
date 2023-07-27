package com.brocodz.devamathacmi.fuctions.gallery;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.app.Dialog;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.brocodz.devamathacmi.R;



public class Gallery extends AppCompatActivity {

    private RecyclerView postRecyclerView;
    private DatabaseReference CRef,URef;
    Dialog myDialog;
    Bitmap bitmap1;
    ArrayList<String> gap;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        myDialog = new Dialog(this);
        postRecyclerView = (RecyclerView) findViewById(R.id.post_gallery_Recyclerview);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        CRef = FirebaseDatabase.getInstance().getReference("photos");
        URef = FirebaseDatabase.getInstance().getReference("photos");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE:{
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(this, "Permission enable", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void requestPermissions() {

    }


    @Override
    protected void onStart() {
        super.onStart();
        gap = new ArrayList<>();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<PostItem>().setQuery(CRef,PostItem.class).build();

        //


        FirebaseRecyclerAdapter<PostItem, ContactsViewHolder> adapter = new FirebaseRecyclerAdapter<PostItem, ContactsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ContactsViewHolder holder, int position, @NonNull PostItem model) {

                String UserID = getRef(position).getKey();

                URef.child(UserID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("pit")) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                String image_fire = dataSnapshot.child("pit").getValue().toString();
                                Picasso.get().load(image_fire).into(holder.postImageView);
                                gap.add(image_fire);

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Gallery.this, "ERROR, Connect Internet...", Toast.LENGTH_SHORT).show();
                    }
                });
            }


            @NonNull
            @Override
            public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_container,parent,false);
                final ContactsViewHolder viewHolder = new ContactsViewHolder(view2);
                myDialog.setContentView(R.layout.dialog_contact);
                viewHolder.postImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Button download;
                        download = (Button) myDialog.findViewById(R.id.popup_download);
                        final ImageView imagePOP = (ImageView) myDialog.findViewById(R.id.image_popups);
                        Picasso.get().load(gap.get(viewHolder.getAdapterPosition())).into(imagePOP);
                        myDialog.show();
                        download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                {
                                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                            PackageManager.PERMISSION_DENIED) {

                                        String [] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                        requestPermissions(permission,WRITE_EXTERNAL_STORAGE_CODE);
                                    }
                                    else{

                                        Toast.makeText(Gallery.this, "Image Saved to Downloads...", Toast.LENGTH_SHORT).show();
                                        String wrongu = gap.get(viewHolder.getAdapterPosition());
                                        //Log.d("url","wrongu"+wrongu);
                                        Uri uri = Uri.parse(""+wrongu+"");
                                        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                        DownloadManager.Request request = new DownloadManager.Request(uri);
                                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);

                                        request.setTitle("Image saved to storage");
                                        request.setDescription("android download manager...");

                                        request.allowScanningByMediaScanner();
                                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/images/"+"/"+"Canisius"+".png");
                                        request.setMimeType("*/*");
                                        downloadManager.enqueue(request);

                                    }
                                }


                            }
                        });

                    }
                });

                return viewHolder;
            }
        };
        postRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder{
        ImageView postImageView;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            postImageView = (ImageView) itemView.findViewById(R.id.imagePost);
        }
    }




}