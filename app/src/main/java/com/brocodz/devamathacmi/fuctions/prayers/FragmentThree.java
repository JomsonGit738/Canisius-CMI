package com.brocodz.devamathacmi.fuctions.prayers;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brocodz.devamathacmi.BuildConfig;
import com.brocodz.devamathacmi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Calendar;

import static android.app.Activity.DEFAULT_KEYS_DISABLE;
import static android.view.View.GONE;


public class FragmentThree extends Fragment {

    private DatabaseReference reference_mozi;
    String day="1";
    Dialog downDialog;
    private Integer alpha;
    ImageView share1,download1;
    public String interchange;
    private String currentDate1,stringday;
    private ImageView imageView;
    private static final int WRITE_EXTERNAL_STORAGE_CODES = 1;
    View v3;
    public FragmentThree() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v3 = inflater.inflate(R.layout.fragment_three,container,false);

        Toast.makeText(getContext(), "Sorry we are working on it... Keep tuned for new Updates...", Toast.LENGTH_LONG).show();
        share1 = (ImageView) v3.findViewById(R.id.mage1);
        download1 = (ImageView) v3.findViewById(R.id.mage2);


        //
        //calender update
        Calendar calendar = Calendar.getInstance();
        currentDate1= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        SharedPreferences mydates = getContext().getSharedPreferences("mydates", Context.MODE_PRIVATE);
        stringday = mydates.getString("store", String.valueOf(DEFAULT_KEYS_DISABLE));
        alpha = mydates.getInt("numb", DEFAULT_KEYS_DISABLE);
        //
        if(!currentDate1.equals(stringday) || alpha == 0) {
            stringday = currentDate1;

            //this close of bound is limited upto 305
            //after resets the maxided into 1
            // "IMPORTANT ERROR CRASH on daily updation"
            if(alpha >= 300)
            {
                alpha = 0;
            }

            mydates = getContext().getSharedPreferences("mydates", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mydates.edit();
            editor.putString("store", stringday);
            alpha++;
            editor.putInt("numb",alpha);
            editor.apply();
        }else{
            Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();

        }
        //

        //

        //
        imageView = (ImageView) v3.findViewById(R.id.mozhi);
        reference_mozi = FirebaseDatabase.getInstance().getReference("mozhikal");
        reference_mozi.keepSynced(true);
        //
        return v3;
    }

    @Override
    public void onStart() {
        super.onStart();




        reference_mozi.child(String.valueOf(alpha)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String parser = snapshot.child("moz").getValue().toString();
                interchange = parser;



                //Picasso.get().load(parser).into(imageView);
                //for image
               /* Picasso.get().load(R.drawable.ic_about__team).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(parser).into(imageView);
                    }
                });
                */

                //
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable=imageView.getDrawable();
                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();

                try {
                    File file = new File(getActivity().getExternalCacheDir(), File.separator +"mozhikal.jpg");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri photoURI = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID +".provider", file);

                    intent.putExtra(Intent.EXTRA_STREAM, photoURI);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("image/jpg");

                    startActivity(Intent.createChooser(intent, "Share image via WhatsApp Status..."));
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });*/
//        download1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//                    NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
//                    if(activeNetwork==null){
//                        ImageView image_close7;
//                        downDialog = new Dialog(getActivity());
//                        downDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        downDialog.setContentView(R.layout.internet);
//                        downDialog.show();
//                        image_close7 = (ImageView) downDialog.findViewById(R.id.close_dia);
//                        image_close7.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                downDialog.dismiss();
//                            }
//                        });
//
//                    }else{
//
//                                try {
//                                    Toast.makeText(getActivity(), "Image Saved to Downloads...", Toast.LENGTH_SHORT).show();
//                                    Uri uri = Uri.parse("" + interchange + "");
//                                    DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
//                                    DownloadManager.Request request = new DownloadManager.Request(uri);
//                                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//
//                                    request.setTitle("Image saved to storage");
//                                    request.setDescription("android download manager...");
//
//                                    request.allowScanningByMediaScanner();
//                                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//                                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/images/" + "/" + "Canisius" + ".png");
//                                    request.setMimeType("*/*");
//                                    downloadManager.enqueue(request);
//                                }
//                                catch (Exception e){
//                                    for(int se=0;se<5;se++){
//                                        Toast.makeText(getActivity(),"Check INTERNET connection..."+e.getMessage(),Toast.LENGTH_LONG).show();
//                                    }
//
//                                }
//
//
//
//
//
//                            }
//
//
//
//
//
//
//
//
//            }
//
//        });

    }

}