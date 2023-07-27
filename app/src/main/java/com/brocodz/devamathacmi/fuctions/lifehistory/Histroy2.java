package com.brocodz.devamathacmi.fuctions.lifehistory;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.brocodz.devamathacmi.R;

public class Histroy2 extends AppCompatActivity {
    TextView ttx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroy2);
        ttx = (TextView) findViewById(R.id.text_desc);

        String t = getIntent().getStringExtra("key");
        ttx.setText(t);
    }
}