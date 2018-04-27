package com.example.venson.soho.Case.caseapply;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.venson.soho.R;

import java.util.ArrayList;
import java.util.List;

public class CaseApplyActivity extends AppCompatActivity{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<PageView> pageList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caseapply_layout);
        initData();
        initView();

    }

    private void initData() {
        pageList = new ArrayList<>();
        pageList.add(new ApplicationPageView(CaseApplyActivity.this));
        pageList.add(new ProcessingPageView(CaseApplyActivity.this));
        pageList.add(new EndingPageView(CaseApplyActivity.this));
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutThree);
        tabLayout.addTab(tabLayout.newTab().setText("申請中"));
        tabLayout.addTab(tabLayout.newTab().setText("進行中"));
        tabLayout.addTab(tabLayout.newTab().setText("已結案"));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new SamplePagerAdapter());
        initListener();
    }

    private void initListener() {
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageList.get(position));
            return pageList.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
