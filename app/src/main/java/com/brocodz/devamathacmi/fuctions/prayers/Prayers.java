package com.brocodz.devamathacmi.fuctions.prayers;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.brocodz.devamathacmi.R;
import com.google.android.material.tabs.TabLayout;


public class Prayers extends AppCompatActivity {

    private int track;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.langu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);
        //20/1/2021
        //to pass the language value

        Intent value = getIntent();
       if(this.getIntent().getExtras() != null) {
           track = value.getIntExtra("VALUE", 1);
          // Toast.makeText(this, "the number is,,,,,,,,,,,," + track, Toast.LENGTH_SHORT).show();
           //20/1/2021
       }


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if(track == 22) {
            adapter.AddFragment(new FragmentFour(),"PRAYER");
            adapter.AddFragment(new FragmentFive(),"ROSARY");
        }
        else if (track == 33) {
            //fragment adding with name
            adapter.AddFragment(new FragmentTwo(), "Prayer");
            adapter.AddFragment(new FragmentThree(), "Words");
            adapter.AddFragment(new FragmentOne(), "Rosary");
        }
        else if (track == 44){
            adapter.AddFragment(new FragmentThree(), "Words");
        }



        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //adding with the icon
       // tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
       // tabLayout.getTabAt(1).setIcon(R.drawable.ic_contact);
       // tabLayout.getTabAt(2).setIcon(R.drawable.ic_fav);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }
}