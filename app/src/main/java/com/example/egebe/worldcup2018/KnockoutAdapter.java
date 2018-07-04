package com.example.egebe.worldcup2018;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class KnockoutAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 5;

    private static WorldCupResponse singleton = WorldCupData.getInstance().getWorldCupResponse();

    public KnockoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {



        return KnockoutFragment.newInstance(singleton.getKnockout().getAsList().get(position));
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
