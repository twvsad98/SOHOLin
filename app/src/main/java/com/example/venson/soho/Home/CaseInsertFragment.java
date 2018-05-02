package com.example.venson.soho.Home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private Spinner citySpinner, cagetorySpinner;
    private TextView tvToolbar_title, detail_tvUser;
    private EditText add_case, add_content,add_expire, add_budget, add_skill;
    private ImageButton add_done_id, add_cancel_id;
    private Calendar calendar = Calendar.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_case_layout, container, false);
        findViews(view);
        setHasOptionsMenu(true);
        // Spinner
        ArrayAdapter<CharSequence> cagetoryAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.cagetory, android.R.layout.simple_spinner_item);
        cagetorySpinner.setAdapter(cagetoryAdapter);
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.city, android.R.layout.simple_spinner_item);
        citySpinner.setAdapter(cityAdapter);

        setHasOptionsMenu(true);
        tvToolbar_title.setText(R.string.add_case_title);
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date expire = null;
                try {
                    expire = sdf.parse(String.valueOf(add_expire.getText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String cagetory = cagetorySpinner.getSelectedItem().toString();
                String city = citySpinner.getSelectedItem().toString();
                String caseTitle = add_case.getText().toString().trim();
                String content = add_content.getText().toString().trim();
                String skill = add_skill.getText().toString().trim();
                String name = detail_tvUser.getText().toString().trim();
                int budget = Integer.parseInt(add_budget.getText().toString().trim());
                if (Common.networkConnected(getActivity())) {
                    String url = Common.URL + "/CaseServlet";
                    myCase myCase = new myCase(0, budget, caseTitle, skill, city, content, null,expire, cagetory, name);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("action", "caseInsert");
                    jsonObject.addProperty("case", new Gson().toJson(myCase));
                    jsonObject.addProperty("user_id",4);
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
                getFragmentManager().popBackStack();
            }

        });

        add_cancel_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Canael",Toast.LENGTH_SHORT).show();
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
            add_expire.setText(sdf.format(calendar.getTime()));
        }
    };

    private void findViews(View view) {
        citySpinner = view.findViewById(R.id.spinner_city_id);
        tvToolbar_title = view.findViewById(R.id.tvTool_bar_title);
        add_done_id = view.findViewById(R.id.add_done_id);
        add_cancel_id = view.findViewById(R.id.add_cancel_id);
        add_case = view.findViewById(R.id.add_case);
        cagetorySpinner = view.findViewById(R.id.spinner_cagetory_id);
        add_content = view.findViewById(R.id.add_content);
        add_expire = view.findViewById(R.id.add_expire);
        add_skill = view.findViewById(R.id.add_skill);
        add_budget = view.findViewById(R.id.add_budget);
    }


}
