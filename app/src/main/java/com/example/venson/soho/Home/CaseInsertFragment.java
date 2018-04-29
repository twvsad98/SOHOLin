package com.example.venson.soho.Home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.venson.soho.Common;
import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.example.venson.soho.myCase;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CaseInsertFragment extends Fragment {
    private final static String TAG = "CaseInsertFragment";
    private Spinner spinner;
    private TextView tvToolbar_title;
    private EditText add_case,add_cagetory,add_content,add_release,add_expire,add_budget,add_skill;
    private ImageButton add_done_id, add_cancel_id;
    private Calendar calendar = Calendar.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_case_layout, container, false);
        findViews(view);
        // Spinner
        final ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.city, android.R.layout.simple_spinner_item );
        spinner.setAdapter(cityAdapter);
        setHasOptionsMenu(true);
        tvToolbar_title.setText(R.string.add_case_title);
        add_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), datepicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        add_expire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), datepicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // toolbar done and cancel
        add_done_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date release = null;
                try {
                    release = sdf.parse(String.valueOf(add_release.getText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Date expire = null;
                try {
                    expire = sdf.parse(String.valueOf(add_expire.getText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String caseTitle = add_case.getText().toString().trim();
                String cagetory = add_cagetory.getText().toString().trim();
                String content = add_content.getText().toString().trim();
                String skill = add_skill.getText().toString().trim();

                int budget = Integer.parseInt(add_budget.getText().toString().trim());
                String city = spinner.getSelectedItem().toString();
                if (Common.networkConnected(getActivity())) {
                    String url = Common.URL + "/CaseServlet";
                    myCase myCase = new myCase(0,budget,caseTitle,skill,city,content,release,expire,cagetory);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("action", "caseInsert");
                    jsonObject.addProperty("case", new Gson().toJson(myCase));
                    int count = 0;
                    try {
                        String result = new MyTask(url, jsonObject.toString()).execute().get();
                        count = Integer.valueOf(result);
                    } catch (Exception e) {
                        Log.e(TAG, e.toString());
                    }
                    if (count == 0) {
                        Common.showToast(getActivity(), R.string.msg_InsertFail);
                    } else {
                        Common.showToast(getActivity(), R.string.msg_InsertSuccess);
                    }
                } else {
                    Common.showToast(getActivity(), R.string.msg_NoNetwork);
                }
            }
        });
        add_cancel_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }


    DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String dateFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                add_release.setText(sdf.format(calendar.getTime()));
                add_expire.setText(sdf.format(calendar.getTime()));
        }
    };

    private void findViews(View view) {
        spinner = view.findViewById(R.id.spinner_id);
        tvToolbar_title = view.findViewById(R.id.tvTool_bar_title);
        add_done_id = view.findViewById(R.id.add_done_id);
        add_cancel_id = view.findViewById(R.id.add_cancel_id);
        add_case = view.findViewById(R.id.add_case);
        add_cagetory = view.findViewById(R.id.add_category);
        add_content = view.findViewById(R.id.add_content);
        add_release = view.findViewById(R.id.add_release);
        add_expire = view.findViewById(R.id.add_expire);
        add_skill = view.findViewById(R.id.add_skill);
        add_budget = view.findViewById(R.id.add_budget);
    }


}
