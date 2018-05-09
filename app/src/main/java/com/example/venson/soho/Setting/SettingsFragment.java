package com.example.venson.soho.Setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.venson.soho.BottomNavigation;
import com.example.venson.soho.Common;
import com.example.venson.soho.LoginFragment;
import com.example.venson.soho.R;

/**
 * Created by ricky on 2018/4/21.
 */

public class SettingsFragment extends Fragment {
    private Button btSetLogout,btSetLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        btSetLogout = view.findViewById(R.id.btSetLogout);
        btSetLogin = view.findViewById(R.id.btSetLogin);

        btSetLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences(Common.PREF_FILE,
                        Context.MODE_PRIVATE);
                pref.edit().putBoolean("login", false).putString("email","")
                        .putString("password","").apply();
                Common.disconnectServer();
                Fragment fragment = new BottomNavigation();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
            }
        });

        btSetLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LoginFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
