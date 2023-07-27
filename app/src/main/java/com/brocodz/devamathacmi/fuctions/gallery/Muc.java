package com.brocodz.devamathacmi.fuctions.gallery;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.brocodz.devamathacmi.R;
import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Muc extends AppCompatActivity {

    ListView listView;
    List<String> arrayListSongName = new ArrayList<>();
    List<String> arrayListSongURL = new ArrayList<>();
    ArrayAdapter<String> adapterc;
    DatabaseReference databaseReference;
    Song song;
    JcPlayerView jcPlayerView;
    ArrayList<JcAudio> jcAudios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muc);
        checkConnection();


        song = new Song();
        listView = (ListView) findViewById(R.id.List_View1);
        adapterc =new ArrayAdapter<>(this,R.layout.itemss,R.id.item_txt,arrayListSongName);
        jcPlayerView = findViewById(R.id.jc_player);
        retrieveSongs();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                jcPlayerView.playAudio(jcAudios.get(i));
                jcPlayerView.setVisibility(View.VISIBLE);
                jcPlayerView.createNotification();
            }
        });

    }
    //check the internet connection
    public void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if(null==activeNetwork){
            Toast toast = Toast.makeText(this, "NO INTERNET SERVICE", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
    //

    private void retrieveSongs(){
        databaseReference = FirebaseDatabase.getInstance().getReference("songs");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayListSongName.clear();
                arrayListSongURL.clear();

                for(DataSnapshot ds :snapshot.getChildren()){

                    song = ds.getValue(Song.class);
                    arrayListSongName.add(song.getSname());
                    arrayListSongURL.add(song.getSurl());
                    jcAudios.add(JcAudio.createFromURL(song.getSname(),song.getSurl()));
                }
                jcPlayerView.initPlaylist(jcAudios,null);
                listView.setAdapter(adapterc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}