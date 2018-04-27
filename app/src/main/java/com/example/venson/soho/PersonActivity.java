package com.example.venson.soho;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class PersonActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.cag_item_Home:
                    fragment = new HomeFragment();
                    switchFragment(fragment);
                    setTitle(R.string.cag_nav_home);
                    return true;
                case R.id.cag_item_Mail:
                    fragment = new MessageFragment();
                    switchFragment(fragment);
                    setTitle(R.string.cag_nav_message);
                    return true;
                case R.id.cag_item_case:
                    fragment = new CaseFragment();
                    switchFragment(fragment);
                    setTitle(R.string.cag_nav_case);
                    return true;
                case R.id.cag_item_Member:
                    fragment = new UserFragment();
                    switchFragment(fragment);
                    setTitle(R.string.cag_nav_member);
                    return true;
                case R.id.cag_item_Settimg:
                    fragment = new SettingsFragment();
                    switchFragment(fragment);
                    setTitle(R.string.cag_nav_settings);
                    return true;
                default:
                    initContent();
                    break;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_layout);

        initContent();
    }

    private void initContent() {
        Fragment fragment = new HomeFragment();
        switchFragment(fragment);
        setTitle(R.string.cag_nav_home);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    }

