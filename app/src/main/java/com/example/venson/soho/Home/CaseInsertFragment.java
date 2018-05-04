package com.example.venson.soho.Home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venson.soho.Common;
import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.example.venson.soho.myCase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CaseInsertFragment extends Fragment {
    private final static String TAG = "CaseInsertFragment";
    private Spinner citySpinner;
    private TextView tvToolbar_title, detail_tvUser, add_tvCategory;
    private Button add_btCagetory;
    private EditText add_case, add_content,add_expire, add_budget, add_skill;
    private ImageButton add_done_id, add_cancel_id;
    private Calendar calendar = Calendar.getInstance();
    String [] listItems;
    boolean [] checkedItems;
    ArrayList<Integer> categoryItems = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_case_layout, container, false);
        findViews(view);
        setHasOptionsMenu(true);
        // Spinner
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

        // dialog view
        listItems = getResources().getStringArray(R.array.category);
        checkedItems = new boolean[listItems.length];
        add_btCagetory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("選擇類型");
                // 多重選單
                builder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, final int position, boolean isChecked) {
                        if (isChecked) {
                            categoryItems.add(position);
                        } else {
                            categoryItems.remove(Integer.valueOf(position));
                            }

                        }
                });
                builder.setCancelable(false);
                builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for(int i = 0 ; i < categoryItems.size(); i++) {
                            item = item + listItems[categoryItems.get(i)];
                                if(i != categoryItems.size() - 1) {
                                    item = item + " , ";
                                }
                        }
                        add_tvCategory.setText(item);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        // toolbar done and cancel
        add_done_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date expire = null;
                try {
                    expire = sdf.parse(String.valueOf(add_expire.getText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String checkCategory = add_tvCategory.getText().toString();
                String city = citySpinner.getSelectedItem().toString();
                String caseTitle = add_case.getText().toString().trim();
                String content = add_content.getText().toString().trim();
                String skill = add_skill.getText().toString().trim();
                int budget = Integer.parseInt(add_budget.getText().toString().trim());
                if (Common.networkConnected(getActivity())) {
                    String url = Common.URL + "/CaseServlet";
                    myCase myCase = new myCase(0, budget, caseTitle, skill, city, content ,expire, checkCategory);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("action", "caseInsert");
                    jsonObject.addProperty("case", gson.toJson(myCase));
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
        add_content = view.findViewById(R.id.add_content);
        add_expire = view.findViewById(R.id.add_expire);
        add_skill = view.findViewById(R.id.add_skill);
        add_budget = view.findViewById(R.id.add_budget);
        add_btCagetory = view.findViewById(R.id.add_btCagetory);
        add_tvCategory = view.findViewById(R.id.add_tvCagetory);
    }

}
