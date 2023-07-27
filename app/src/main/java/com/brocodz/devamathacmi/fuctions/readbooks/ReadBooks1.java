package com.brocodz.devamathacmi.fuctions.readbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.brocodz.devamathacmi.R;
import com.brocodz.devamathacmi.fuctions.lifehistory.History1;
import com.brocodz.devamathacmi.fuctions.lifehistory.HistoryC;
import com.brocodz.devamathacmi.fuctions.lifehistory.Histroy2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadBooks1 extends AppCompatActivity {

    ListView listViewR;
    DatabaseReference databaseReferenceR;
    List<String> name_list,url_list;
    ArrayAdapter<String> adapterR;
    ReadC ReadC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books1);
        listViewR = (ListView) findViewById(R.id.pdf_nameR);

        databaseReferenceR = FirebaseDatabase.getInstance().getReference("ReadBooks");
        ReadC = new ReadC();
        name_list = new ArrayList<>();
        url_list = new ArrayList<>();
        adapterR =new ArrayAdapter<>(this,R.layout.pdf,R.id.pdf_name,name_list);

        databaseReferenceR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds :snapshot.getChildren()){
                    ReadC =ds.getValue(ReadC.class);
                    name_list.add(ReadC.getPdf_name());
                    url_list.add(ReadC.getPdf_url());
                }
                //intro = historyC.getIntros();
                //textint.setText(intro);
                listViewR.setAdapter(adapterR);
                listViewR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent intentR = new Intent(ReadBooks1.this, ReadBooks2.class);
                        String pR =url_list.get(position);
                        intentR.putExtra("keyR",pR);
                        startActivity(intentR);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReadBooks1.this, "Your Internet Connection is Poor", Toast.LENGTH_SHORT).show();
            }
        });








    }
}