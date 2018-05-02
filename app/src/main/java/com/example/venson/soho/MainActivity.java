package com.example.venson.soho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.venson.soho.Home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caseapply_layout);

        getSupportFragmentManager().beginTransaction().replace(R.id.content, new LoginFragment()).commit();
    }

}
