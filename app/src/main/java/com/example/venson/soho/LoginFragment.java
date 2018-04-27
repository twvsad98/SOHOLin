package com.example.venson.soho;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.venson.soho.Home.AddCaseFragment;
import com.example.venson.soho.Home.HomeFragment;

public class LoginFragment extends Fragment {
    private Button login_look;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout,container ,false);
        login_look = view.findViewById(R.id.login_look);
        login_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            }
        });
        return  view;
    }


}
