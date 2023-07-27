package com.brocodz.devamathacmi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;



public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN=3500;
    Animation top,bottom,fade,backie;
    ImageView image_top;
    TextView textView,textBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView = (TextView) findViewById(R.id.tt1);
        /*Typeface typeface = ResourcesCompat.getFont(this,R.font.mohini);
        textView.setTypeface(typeface);*/

        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        fade = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        backie = AnimationUtils.loadAnimation(this,R.anim.bottom_animation_backie);

        //image_back = (ImageView) findViewById(R.id.splashbackie);
        image_top = (ImageView) findViewById(R.id.img1);
        textBottom = findViewById(R.id.txt2);

        //image_back.setAnimation(backie);
        image_top.setAnimation(fade);
        textBottom.setAnimation(bottom);
        textView.setAnimation(fade);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent r = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(r);
            }
        },SPLASH_SCREEN);


    }
}