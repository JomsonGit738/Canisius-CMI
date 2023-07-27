package com.brocodz.devamathacmi.fuctions.lifehistory;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.brocodz.devamathacmi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class History1 extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    List<String> title_list,story_list;
    ArrayAdapter<String> adapterq;
    HistoryC historyC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history1);
        listView = (ListView) findViewById(R.id.list_name);


        databaseReference = FirebaseDatabase.getInstance().getReference("history");
        databaseReference.keepSynced(true);
        historyC = new HistoryC();
        title_list = new ArrayList<>();
        story_list = new ArrayList<>();
        adapterq =new ArrayAdapter<>(this,R.layout.item,R.id.item_txt,title_list);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds :snapshot.getChildren()){
                    historyC =ds.getValue(HistoryC.class);
                    assert historyC != null;
                    title_list.add(historyC.getTitle());
                    story_list.add(historyC.getStory());
                }

                listView.setAdapter(adapterq);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Intent intent1 = new Intent(History1.this,Histroy2.class);
                            String p =story_list.get(position);
                            intent1.putExtra("key",p);
                            startActivity(intent1);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}