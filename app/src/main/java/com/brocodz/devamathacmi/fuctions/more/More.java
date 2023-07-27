package com.brocodz.devamathacmi.fuctions.more;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.brocodz.devamathacmi.R;


public class More extends AppCompatActivity implements View.OnClickListener {
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13,img14,img15;
    Button website;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


        img1 = (ImageView) findViewById(R.id.facebook);
        img2 = (ImageView) findViewById(R.id.breaker);
        img3 = (ImageView) findViewById(R.id.youtube);
        img4 = (ImageView) findViewById(R.id.whatsapp);
        img5 = (ImageView) findViewById(R.id.twitter);
        img6 = (ImageView) findViewById(R.id.spotify);
        img7 = (ImageView) findViewById(R.id.instagram);
        img8 = (ImageView) findViewById(R.id.google);
        img9 = (ImageView) findViewById(R.id.wikipedia);
        img10 = (ImageView) findViewById(R.id.pintrest);
        img11 = (ImageView) findViewById(R.id.anchor);
        img12 = (ImageView) findViewById(R.id.pocketcasts);
        img13 = (ImageView) findViewById(R.id.radiopublic);
        img14 = (ImageView) findViewById(R.id.blogspot);
        img15 = (ImageView) findViewById(R.id.googlemap);
        website = (Button) findViewById(R.id.buturl);


        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);
        img9.setOnClickListener(this);
        img10.setOnClickListener(this);
        img11.setOnClickListener(this);
        img12.setOnClickListener(this);
        img13.setOnClickListener(this);
        img14.setOnClickListener(this);
        img15.setOnClickListener(this);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri urip = Uri.parse("https://www.canisiuscmi.com");
                startActivity(new Intent(Intent.ACTION_VIEW,urip));

            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.facebook:
                gotoURL("https://www.facebook.com/profile.php?id=100004262598063");
                break;
            case R.id.breaker:
                gotoURL("https://www.breaker.audio/kniissys-ddyrrikkurrippukll");
                break;
            case R.id.youtube:
                gotoURL("https://www.youtube.com/channel/UCkbYguIdlwdgVSllkipGBgg");
                break;
            case R.id.whatsapp:
                boolean installed = appInstalledOrNot("com.whatsapp");
                if(installed){
                    Intent w1 = new Intent(Intent.ACTION_VIEW);
                    w1.setData(Uri.parse("http://api.whatsapp.com/send?phone=+918289945270&text=Type_here"));
                    startActivity(w1);
                }else{
                    Toast.makeText(this, "Whats app is not INSTALLED in your PHONE", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.twitter:
                gotoURL("https://twitter.com/sg_cmi");
                break;
            case R.id.spotify:
                gotoURL("https://open.spotify.com/show/25V4tThKxkAnqMCR3CqTyK");
                break;
            case R.id.instagram:
                gotoURL("https://www.instagram.com/sgfrcanisiuscmi/");
                break;
            case R.id.google:
                gotoURL("https://www.google.com/podcasts?feed=aHR0cHM6Ly9hbmNob3IuZm0vcy8zNzRmOGE5NC9wb2RjYXN0L3Jzcw==");
                break;
            case R.id.wikipedia:
                gotoURL("https://en.wikipedia.org/wiki/Canisius_Thekkekara");
                break;
            case R.id.pintrest:
                gotoURL("https://in.pinterest.com/pin/694258098802623366/?nic_v2=1a4Tid6ax");
                break;
            case R.id.anchor:
                gotoURL("https://anchor.fm/canisius");
                break;
            case R.id.pocketcasts:
                gotoURL("https://pca.st/ifub8got");
                break;
            case R.id.radiopublic:
                gotoURL("https://radiopublic.com/-85BPbm/episodes");
                break;
            case R.id.googlemap:
                gotoURL("https://goo.gl/maps/4Zoop9ksf5KKFuYn8");
                break;
            case R.id.blogspot:
                gotoURL("http://insoulpursuit.blogspot.com/2014/03/fr-canisius-thekkekara-cmi-servant-of.html");
                break;
            default:
                break;
        }
    }

    private boolean appInstalledOrNot(String wap) {
        PackageManager packageManager1 = getPackageManager();
        boolean app_installed;
        try{
            packageManager1.getPackageInfo(wap,PackageManager.GET_ACTIVITIES);
            app_installed=true;
        }catch (Exception e){
            app_installed=false;
        }
        return app_installed;
    }

    private void gotoURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}