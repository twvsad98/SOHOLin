package com.example.venson.soho.Home.CategoryTab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.venson.soho.Common;
import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.example.venson.soho.myCase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by venson on 2018/4/28.
 */

public class GetAllFragment extends Fragment {
    private static final String TAG = "getAllCaseFragment";
    private MyTask caseGetAllTask, caseDeleteTask;
    private RecyclerView rvGetAll;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_getall_fragment, container, false);
        findView(view);
        rvGetAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        showAllCases();
        return view;
    }

    private void showAllCases() {
        if (Common.networkConnected(getActivity())) {
            String url = Common.URL + "/CaseServlet";
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            List<myCase> myCases = null;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "getAll");
            String jsonOut = jsonObject.toString();
            caseGetAllTask = new MyTask(url, jsonOut);
            try {
                String jsonIn = caseGetAllTask.execute().get();
                Log.d(TAG, jsonIn);
                Type listType = new TypeToken<List<myCase>>() {
                }.getType();
                myCases = gson.fromJson(jsonIn, listType);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            if (myCases == null || myCases.isEmpty()) {
                Common.showToast(getActivity(), R.string.msg_NoCases);
            } else {
                rvGetAll.setAdapter(new getAllCaseAdapter(myCases, getContext()));
            }
        } else {
            Common.showToast(getActivity(), R.string.msg_NoNetwork);
        }
    }


    private class getAllCaseAdapter extends RecyclerView.Adapter<getAllCaseAdapter.MyViewHolder> {
        private LayoutInflater layoutInflater;
        private List<myCase> myCases;

        public getAllCaseAdapter(List<myCase> myCases, Context context) {
            layoutInflater = LayoutInflater.from(context);
            this.myCases = myCases;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView case_cotent,case_pay_min,case_date,case_skill,case_location,case_name_id;
            public MyViewHolder(View itemView) {
                super(itemView);
                case_name_id = itemView.findViewById(R.id.case_name_id);
                case_cotent = itemView.findViewById(R.id.case_content);
                case_pay_min = itemView.findViewById(R.id.case_pay_min);
                case_date = itemView.findViewById(R.id.case_date);
                case_skill = itemView.findViewById(R.id.case_skill);
                case_location = itemView.findViewById(R.id.case_location);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.case_item_cardview, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
            final myCase myCase = myCases.get(position);
            Date date = myCase.getRecruit_start();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String dateStr = dateFormat.format(date);
            myViewHolder.case_name_id.setText(myCase.getName());
            myViewHolder.case_cotent.setText(myCase.getDescription());
            myViewHolder.case_pay_min.setText(String.valueOf(myCase.getBudget()));
            myViewHolder.case_date.setText(dateStr);
            myViewHolder.case_skill.setText(myCase.getSkill());
            myViewHolder.case_location.setText(myCase.getLocation());
        }

        @Override
        public int getItemCount() {
            return myCases.size();
        }

    }

    private void findView(View view) {
         rvGetAll = view.findViewById(R.id.rvGetAll);

    }

} // end
