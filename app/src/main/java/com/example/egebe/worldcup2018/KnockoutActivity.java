package com.example.egebe.worldcup2018;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.rd.PageIndicatorView;
import com.squareup.picasso.Picasso;


public class KnockoutActivity extends AppCompatActivity {
    KnockoutAdapter knockoutAdapter;
    ImageView imgFlag;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knockout_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.round_viewpager);
        knockoutAdapter = new KnockoutAdapter(getSupportFragmentManager());
        viewPager.setAdapter(knockoutAdapter);
        PageIndicatorView pageIndicatorView = findViewById(R.id.page_indicator_view);
        pageIndicatorView.setCount(5);

        imgFlag = findViewById(R.id.deneme);
        Picasso.with(this).load("https://images.pexels.com/photos/47730/the-ball-stadion-football-the-pitch-47730.jpeg?cs=srgb&dl=ball-field-football-47730.jpg&fm=jpg").into(imgFlag);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}