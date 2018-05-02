package com.example.venson.soho;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.venson.soho.Common.Common;
import com.example.venson.soho.Home.AddCaseFragment;
import com.example.venson.soho.Home.HomeFragment;
import com.example.venson.soho.LoginRegist.UserExistTask;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    private Button login_look, btLogin, btRegister;
    private EditText tvEmail, tvPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout,container ,false);
        login_look = view.findViewById(R.id.login_look);
        login_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BottomNavigation();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commit();
            }
        });

        ////////////登入按鈕  檢查 成功 進入主頁
        btLogin = view.findViewById(R.id.login_btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEmail = view.findViewById(R.id.login_tvEmail);
                tvPassword = view.findViewById(R.id.login_tvPassword);
                String email = tvEmail.getText().toString().trim();
                String password = tvPassword.getText().toString().trim();
                if (email.length() <= 0 || password.length() <= 0) {
                    tvEmail.setError("email or password can't be empty");
                    tvPassword.setError("email or password can't be empty");
                    return;
                }
                if (isUser(email, password)) {
                    /////////////////////
                    SharedPreferences preferences = getActivity().getSharedPreferences(
                            Common.PREF_FILE, MODE_PRIVATE);
                    preferences.edit().putBoolean("login", true)
                            .putString("email", email)
                            .putString("password", password).apply();
                    ////////////////////
                    getActivity().setResult(Activity.RESULT_OK);
                    Fragment fragment = new HomeFragment();
                    getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content, fragment).commit();
                } else {
                    tvEmail.setError("email or passwrod is wrong");
                    tvPassword.setError(" email or password is wrong");
                }
            }
        });
        btRegister = view.findViewById(R.id.login_btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegistFragment();
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.content, fragment).commit();


            }
        });


        return view;
        return view;
    }
           ///已經登入時，自動登入
    @Override
    public void onStart() {
        super.onStart();

//        SharedPreferences preferences = getActivity().getSharedPreferences(Common.PREF_FILE,
//                MODE_PRIVATE);
//        boolean login = preferences.getBoolean("login", false);
//        if (login) {
//            String email = preferences.getString("email", "");
//            String password = preferences.getString("password", "");
//            tvEmail.setText(email);
//            tvPassword.setText(password);
//            if (isUser(email, password)) {
//                getActivity().setResult(Activity.RESULT_OK);
//                Fragment fragment = new HomeFragment();
//                getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();

//            }

//        }


    }

    private boolean isUser(final String email, final String password) {
        boolean isUser;
        String url = Common.URL + "Login_RegistServlet";
        try {
            isUser = new UserExistTask().execute(url, email, password).get();
        } catch (Exception e) {
            isUser = false;
        }
        return isUser;
    }


}
