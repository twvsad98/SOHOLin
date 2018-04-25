package com.example.venson.soho.caseapply;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    PageAdapter(FragmentManager fragmentManager, int numOfTabs) {
        super(fragmentManager);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ApplicationCaseFragment();
            case 1:
                return new PostCaseFragment();
            case 2:
                return new ApplicationFragment();
            case 3:
                return new ProcessingFragment();
            case 4:
                return new EndingFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {return numOfTabs;}
}
