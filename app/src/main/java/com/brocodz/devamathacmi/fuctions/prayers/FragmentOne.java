package com.brocodz.devamathacmi.fuctions.prayers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brocodz.devamathacmi.R;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FragmentOne extends Fragment {

    private View ContactsView;
    private RecyclerView myRecyclerview;
    private ArrayList<Contact> Contact_list1;
    private DatabaseReference ContactRef,UserRef;
    private RecyclerView myContactList;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ContactsView = inflater.inflate(R.layout.fragment_one,container,false);

        myContactList = (RecyclerView) ContactsView.findViewById(R.id.contact_recyclerview);
        myContactList.setLayoutManager(new LinearLayoutManager(getContext()));

        ContactRef = FirebaseDatabase.getInstance().getReference("rosary");
        ContactRef.keepSynced(true);
        UserRef = FirebaseDatabase.getInstance().getReference("rosary");
        UserRef.keepSynced(true);

        return ContactsView;
    }



    @Override
    public void onStart() {
        super.onStart();
        Contact_list1 = new ArrayList<>();
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Contact>().setQuery(ContactRef,Contact.class).build();

        FirebaseRecyclerAdapter<Contact, ContactsViewHolder> adapter = new FirebaseRecyclerAdapter<Contact, ContactsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ContactsViewHolder holder, int position, @NonNull Contact model) {

                String UserID = getRef(position).getKey();

                UserRef.child(UserID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild("imma")) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                                final String image_fire = dataSnapshot.child("imma").getValue().toString();
                               // Picasso.get().load(image_fire).into(holder.profile_image);
                                //offline image parts contents 30-10-2020
                                Picasso.get().load(R.drawable.ic_about__team).networkPolicy(NetworkPolicy.OFFLINE).into(holder.profile_image, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
                                        Picasso.get().load(image_fire).into(holder.profile_image);
                                    }
                                });

                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @NonNull
            @Override
            public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
                ContactsViewHolder viewHolder = new ContactsViewHolder(view1);
                return viewHolder;
            }
        };
        myContactList.setAdapter(adapter);
        adapter.startListening();
    }






    public static class ContactsViewHolder extends RecyclerView.ViewHolder{

        ImageView profile_image;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_image = (ImageView) itemView.findViewById(R.id.image_contact);
        }
    }
}