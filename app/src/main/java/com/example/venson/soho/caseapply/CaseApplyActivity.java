package com.example.venson.soho.caseapply;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.venson.soho.R;

public class CaseApplyActivity extends AppCompatActivity{
    Toolbar toolbar;
    TabLayout tabLayout;
    TabLayout tabLayout1;
    PagerAdapter pagerAdapter;
    TabItem tab_applicationcase;
    TabItem tab_postcase;
    TabItem tab_application;
    TabItem tab_processing;
    TabItem tab_ending;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.caseapply_layout);
    }
}
