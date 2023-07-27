package com.brocodz.devamathacmi.fuctions.readbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brocodz.devamathacmi.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.squareup.picasso.Callback;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReadBooks2 extends AppCompatActivity {

    private PDFView pdfView;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_books2);
        String tR = getIntent().getStringExtra("keyR");
        pdfView = (PDFView) findViewById(R.id.pdf_content);
        pb = (ProgressBar) findViewById(R.id.progressR);


        new RetrievePdfStream().execute(tR);

    }
        class RetrievePdfStream extends AsyncTask<String, Void, InputStream> {


            @Override
            protected InputStream doInBackground(String... strings) {
                InputStream inputStream = null;
                try{
                    URL uRl= new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) uRl.openConnection();
                    if (urlConnection.getResponseCode() == 200)
                    {
                        inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    }
                }
                catch (IOException e){
                    return null;
                }
                return inputStream;
            }

            @Override
            protected void onPostExecute(InputStream inputStream) {
                    pdfView.fromStream(inputStream).load();
                    pb.setVisibility(View.GONE);
            }
        }



}
