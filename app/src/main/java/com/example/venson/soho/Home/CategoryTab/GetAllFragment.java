package com.example.venson.soho.Home.CategoryTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.example.venson.soho.MemberClass;


public class GetAllFragment extends Fragment {
    private static final String TAG = "getAllCaseFragment";
    private MyTask caseGetAllTask;
    private RecyclerView rvGetAll;
    private MemberClass user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_getall_fragment, container, false);
        findView(view);
        return view;
    }

    private void findView(View view) {

    }



} // end
