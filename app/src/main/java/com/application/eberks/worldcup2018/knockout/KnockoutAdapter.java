package com.application.eberks.worldcup2018.knockout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.application.eberks.worldcup2018.models.WorldCupDataSingleton;
import com.application.eberks.worldcup2018.WorldCupResponse;

public class KnockoutAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 5;

    private static WorldCupResponse singleton = WorldCupDataSingleton.getInstance().getWorldCupResponse();

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
