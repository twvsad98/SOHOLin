package com.example.venson.soho.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.venson.soho.R;
import com.example.venson.soho.MemberClass;
import com.example.venson.soho.myCase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CaseDetailFragment extends Fragment {
    private TextView detail_tvCase,detail_tvLocation,detail_tvCagetory,detail_tvConten,
            detail_tvExpire,detail_tvBudget,detail_tvSkill,detail_tvUser;
    private TextView tvToolbar_title;
    private Button detail_btApply;
    private myCase myCase;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_case_detail_fragment, container, false);
        findView(view);
        tvToolbar_title.setText(R.string.case_detail);
        Bundle bundle = new Bundle();
        myCase = (myCase) getArguments().getSerializable("myCase");
        detail_tvUser.setText(myCase.getUser_name());
        detail_tvCase.setText(myCase.getName());
        detail_tvLocation.setText(myCase.getLocation());
        detail_tvCagetory.setText(myCase.getCategory());
        detail_tvConten.setText(myCase.getDescription());
        detail_tvSkill.setText(myCase.getSkill());
        detail_tvBudget.setText(String.valueOf(myCase.getBudget()));
        Date date = myCase.getRecruit_end();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = dateFormat.format(date);
        detail_tvExpire.setText(dateStr);

        return view;
    }


    private void findView(View view) {
        detail_tvCase = view.findViewById(R.id.case_detail_tvCase);
        detail_tvLocation = view.findViewById(R.id.case_detail_tvLocation);
        detail_tvCagetory = view.findViewById(R.id.case_detail_tvCagetory);
        detail_tvConten = view.findViewById(R.id.case_detail_tvConten);
        detail_tvExpire = view.findViewById(R.id.case_detail_tvExpire);
        detail_tvBudget = view.findViewById(R.id.case_detail_tvBudget);
        detail_tvSkill = view.findViewById(R.id.case_detail_tvSkill);
        detail_tvUser = view.findViewById(R.id.case_detail_tvUser);
        detail_btApply = view.findViewById(R.id.case_detail_btApply);
        tvToolbar_title = view.findViewById(R.id.tvTool_bar_title);
    }
}
