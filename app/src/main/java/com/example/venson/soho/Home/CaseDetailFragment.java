package com.example.venson.soho.Home;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.venson.soho.CaseCompany;
import com.example.venson.soho.CaseTag;
import com.example.venson.soho.Common;
import com.example.venson.soho.Company;
import com.example.venson.soho.MyCase;
import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.example.venson.soho.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CaseDetailFragment extends Fragment {
    private static final String TAG = "CaseDetailFragment";
    private TextView detail_tvCase,detail_tvLocation,detail_tvConten,
            detail_tvExpire,detail_tvRelease,detail_tvSkill,detail_tvUser,
            detail_tvWrokDay,detail_tvPay_min,detail_tvPay_max,detail_tvCompany;
    private TextView tvToolbar_title;
    private Button detail_btApply;
    private MyCase myCase;
    private CaseTag caseTag;
    private MyTask caseDetailTask;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_case_detail_fragment, container, false);
        findView(view);
        tvToolbar_title.setText(R.string.case_detail);
        Bundle bundle = new Bundle();
        User user = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        myCase = (MyCase) getArguments().getSerializable("myCase");
        if (Common.networkConnected(getActivity())) {
            String url = Common.URL + "/SohoServlet";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "getUserById");
            jsonObject.addProperty("ownerId", myCase.getCaseOwnerId());
            String result = "";
            try {
                result = new MyTask(url, jsonObject.toString()).execute().get();
                user = gson.fromJson(result, User.class);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }  if (user == null) {
//                Common.showToast(getActivity(), R.string.msg_InsertFail);
            } else {
                detail_tvUser.setText(user.getUserName());
            }
        }

        if (Common.networkConnected(getActivity())) {
            List<CaseTag> caseTags = new ArrayList<>();
            String url = Common.URL + "/SohoServlet";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "caseTags");
            jsonObject.addProperty("caseId", myCase.getCaseId());
            String jsonOut = jsonObject.toString();
            caseDetailTask = new MyTask(url, jsonOut);
            try {
                String jsonIn = caseDetailTask.execute().get();
                Log.d(TAG, jsonIn);
                Type listType = new TypeToken<List<CaseTag>>() {}.getType();
                caseTags = new Gson().fromJson(jsonIn, listType);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }  if (caseTags == null) {
//                Common.showToast(getActivity(), R.string.msg_InsertFail);
            } else {
                String text = "";
                for (int i = 0; i < caseTags.size(); i++){
                    text += caseTags.get(i).getName();
                }
                detail_tvSkill.setText(text);
            }
        }

        if (Common.networkConnected(getActivity())) {
            Company com = null;
            String url = Common.URL + "/SohoServlet";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "companyName");
            jsonObject.addProperty("caseId", myCase.getCaseId());
            String result = "";
            try {
                result = new MyTask(url, jsonObject.toString()).execute().get();
                com = gson.fromJson(result, Company.class);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }  if (result.isEmpty()) {
                Common.showToast(getActivity(), R.string.msg_InsertFail);
            } else {
//                Common.showToast(getActivity(), R.string.msg_InsertSuccess);
                Geocoder geocoder = new Geocoder(getActivity(), Locale.TRADITIONAL_CHINESE);
                try {
                    List<Address> addressList = geocoder.getFromLocation(com.getLatitude(), com.getLongitude(),1);
                    String returnAddress = addressList.get(0).getAddressLine(0);
                    detail_tvCompany.setText(returnAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        detail_tvCase.setText(myCase.getCaseName());
        detail_tvLocation.setText(myCase.getCaseLocation());
        detail_tvConten.setText(myCase.getCaseDes());
        detail_tvPay_min.setText(String.valueOf(myCase.getCasePayMin()));
        detail_tvPay_max.setText(String.valueOf(myCase.getCasePayMax()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expire = myCase.getCaseRecruitEnd();
        Date release = myCase.getCaseRecruitStart();
        String expireStr = dateFormat.format(expire);
        String releaseStr = dateFormat.format(release);
        detail_tvExpire.setText(expireStr);
        detail_tvRelease.setText(releaseStr);

        return view;
    }

    private void findView(View view) {
        detail_tvCase = view.findViewById(R.id.case_detail_tvCase);
        detail_tvLocation = view.findViewById(R.id.case_detail_tvLocation);
        detail_tvCompany = view.findViewById(R.id.case_detail_tvCompany);
        detail_tvConten = view.findViewById(R.id.case_detail_tvConten);
        detail_tvExpire = view.findViewById(R.id.case_detail_tvExpire);
        detail_tvRelease = view.findViewById(R.id.case_detail_tvRelease);
        detail_tvPay_min = view.findViewById(R.id.case_detail_tvPay_min);
        detail_tvPay_max = view.findViewById(R.id.case_detail_tvPay_max);
        detail_tvSkill = view.findViewById(R.id.case_detail_tvSkill);
        detail_tvUser = view.findViewById(R.id.case_detail_tvUser);
        detail_btApply = view.findViewById(R.id.case_detail_btApply);
        detail_tvWrokDay = view.findViewById(R.id.case_detail_tvWrokDay);
        tvToolbar_title = view.findViewById(R.id.tvTool_bar_title);

    }
}
