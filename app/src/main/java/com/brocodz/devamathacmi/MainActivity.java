package com.brocodz.devamathacmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.brocodz.devamathacmi.fuctions.gallery.Gallery;
import com.brocodz.devamathacmi.fuctions.gallery.Muc;
import com.brocodz.devamathacmi.fuctions.lifehistory.History1;
import com.brocodz.devamathacmi.fuctions.more.More;
import com.brocodz.devamathacmi.R;
import com.brocodz.devamathacmi.fuctions.prayerrequests.PrayerRequests;
import com.brocodz.devamathacmi.fuctions.prayers.Prayers;
import com.brocodz.devamathacmi.fuctions.readbooks.ReadBooks1;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView life, gallery, prayer, prayer_requests, more, about, read;
    Button b1,b2,b3,b4;
    private String string1;
    TextView virus1;
    Dialog medialog,NetDialog;


    public static final String CHANNEl_ID="simple_nature";
    private static final String CHANNEl_NAME="simple coding";
    private static final String CHANNEl_DESC="coding description";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //gradient color combination
        LinearLayout linearLayout = findViewById(R.id.color_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();
        //
        virus1 = (TextView) findViewById(R.id.virus);
        virus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int moz = 44;
                Intent moc= new Intent(MainActivity.this,Prayers.class);
                moc.putExtra("VALUE",moz);
                startActivity(moc);
            }
        });




        //offline data from firebase


        //internet check
        checkConnection();


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEl_ID,CHANNEl_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEl_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        
        //initialise dialog box
        medialog = new Dialog(this);
        //language = new Dialog(this);
        //

        about = (CardView) findViewById(R.id.aboutusc);
        life = (CardView) findViewById(R.id.lifehistorya);
        gallery = (CardView) findViewById(R.id.gallerya);
        prayer = (CardView) findViewById(R.id.prayersa);
        prayer_requests = (CardView) findViewById(R.id.prayerrequestsa);
        more = (CardView) findViewById(R.id.morea);
        read = (CardView) findViewById(R.id.readsa);
        life.setOnClickListener(this);
        about.setOnClickListener(this);
        //prayer.setOnClickListener(this);
        prayer_requests.setOnClickListener(this);
        more.setOnClickListener(this);
        read.setOnClickListener(this);
        final TextView txt2 = findViewById(R.id.text2);
        final TextView txt1 = findViewById(R.id.text1);
        final Typeface typeface = ResourcesCompat.getFont(this, R.font.aladine);
        final Typeface typeface1 = ResourcesCompat.getFont(this, R.font.amaranthq);
        txt1.setTypeface(typeface1);
        txt2.setTypeface(typeface);

        //calender update
       // Calendar calendar = Calendar.getInstance();
       // String currentDate= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text_close;
                ImageView imm1,imm2,imm3;
                medialog.setContentView(R.layout.custompopup);
                text_close = (TextView) medialog.findViewById(R.id.txt_close);
                imm1 = (ImageView) medialog.findViewById(R.id.imag1);
                imm2 = (ImageView) medialog.findViewById(R.id.imag2);
                imm3 = (ImageView) medialog.findViewById(R.id.imag3);
                text_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        medialog.dismiss();
                    }
                });
                imm1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //music activity
                        Intent mus1 = new Intent(MainActivity.this, Muc.class);
                        startActivity(mus1);
                        medialog.dismiss();
                    }
                });
                imm2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //video activity
                        Toast.makeText(MainActivity.this, "NO video Stream Available...", Toast.LENGTH_SHORT).show();

                    }
                });
                imm3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                            ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo activeNetwork12 = manager.getActiveNetworkInfo();
                            if (null == activeNetwork12) {
                                Toast.makeText(MainActivity.this, "NO internet Connection found", Toast.LENGTH_SHORT).show();
                            } else {
                                //photo activity
                                Intent ch1 = new Intent(MainActivity.this, Gallery.class);
                                startActivity(ch1);
                            }
                            medialog.dismiss();
                        }else{
                            for(int gv=0;gv<5;gv++){
                                Toast.makeText(MainActivity.this, "Your Phone restricted +VDM/ Function", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                medialog.show();

            }
        });

        //2021

        prayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                //alert.setTitle("Android Studio");
                alert.setMessage("Select the LANGUAGE you want?");
                alert.setPositiveButton("ENGLISH", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int eng=22;
                        Intent en = new Intent(MainActivity.this,Prayers.class);
                        en.putExtra("VALUE",eng);
                        startActivity(en);
                        dialog.dismiss();
                        //Toast.makeText(MainActivity.this, "you selected english", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("MALAYALAM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int mal=33;
                        Intent ma = new Intent(MainActivity.this,Prayers.class);
                        ma.putExtra("VALUE",mal);
                        startActivity(ma);
                        dialog.dismiss();
                       // Toast.makeText(MainActivity.this, "you selected malayalam", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.create().show();
            }
        });

        //2021





    }
    public void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo  activeNetwork = manager.getActiveNetworkInfo();
        if(null==activeNetwork){
            ImageView image_close;
            NetDialog = new Dialog(this);
            NetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            NetDialog.setContentView(R.layout.internet);
            NetDialog.show();
            image_close = (ImageView) NetDialog.findViewById(R.id.close_dia);
            image_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NetDialog.dismiss();
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.lifehistorya:
                i = new Intent(this, History1.class);
                startActivity(i);
                break;
            //case R.id.prayersa:
            //    i = new Intent(this, Prayers.class);
             //   startActivity(i);
             //   break;
            case R.id.prayerrequestsa:
                i = new Intent(this, PrayerRequests.class);
                startActivity(i);
                break;
            case R.id.morea:
                i = new Intent(this, More.class);
                startActivity(i);
                break;
            case R.id.aboutusc:
                i = new Intent(this, AboutUs.class);
                startActivity(i);
                break;
            case R.id.readsa:
                i = new Intent(this, ReadBooks1.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
