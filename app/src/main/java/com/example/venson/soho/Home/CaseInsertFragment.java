package com.example.venson.soho.Home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venson.soho.Common;
import com.example.venson.soho.Company;
import com.example.venson.soho.MyCase;
import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CaseInsertFragment extends Fragment {
    private final static String TAG = "CaseInsertFragment";
    private Spinner citySpinner;
    private TextView tvToolbar_title, detail_tvUser, add_tvCategory, add_tvCompany;
    private Button add_btCagetory,add_btCompany;
    private EditText add_case, add_content,add_expire, add_skill, add_pay_min, add_pay_max, add_work_day;
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
        final ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
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
        //  輸入公司統編找地址
        add_btCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final EditText alert_etCompany = new EditText(getActivity());
                alert_etCompany.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setTitle("請輸入公司統編");
                alert_etCompany.setHint("8個號碼");
                builder.setView(alert_etCompany);
                builder.setCancelable(false);
                builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Gson gson = new Gson();
                        Company com = null;
                            String company = alert_etCompany.getText().toString().trim();
                            if (company.isEmpty() || company.length() <= 8) {
                               Common.showToast(getActivity(), "請輸入8個數字");

                            } else if (Common.networkConnected(getActivity())) {
                                    String url = Common.URL + "/SohoServlet";
                                    JsonObject jsonObject = new JsonObject();
                                    jsonObject.addProperty("action", "search");
                                    jsonObject.addProperty("company", company);
                                    String result = "";
                                    try {
                                        result = new MyTask(url, jsonObject.toString()).execute().get();
                                        com = gson.fromJson(result, Company.class);
                                    } catch (Exception e) {
                                        Log.e(TAG, e.toString());
                                    }
                                    if (result.isEmpty()) {
                                        Common.showToast(getActivity(), R.string.msg_InsertFail);
                                    } else {
                                        Common.showToast(getActivity(), R.string.msg_InsertSuccess);
                                        Geocoder geocoder = new Geocoder(getActivity(), Locale.TRADITIONAL_CHINESE);
                                        try {
                                            List<Address> addressList = geocoder.getFromLocation(com.getLatitude(), com.getLongitude(), 1);
                                            String returnAddress = addressList.get(0).getAddressLine(0);
                                            add_tvCompany.setText(returnAddress);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
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

                String city = citySpinner.getSelectedItem().toString();
                String caseName = add_case.getText().toString().trim();
                String content = add_content.getText().toString().trim();
                String skill = add_skill.getText().toString().trim();
                String workDay = add_work_day.getText().toString().trim();
                int payMin = Integer.parseInt(add_pay_min.getText().toString().trim());
                int payMax = Integer.parseInt(add_pay_max.getText().toString().trim());
                if (Common.networkConnected(getActivity())) {
                    String url = Common.URL + "/SohoServlet";
                    MyCase myCase = new MyCase(0,caseName, (java.sql.Date) expire,payMin,payMax,content,city);

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("action", "caseInsert");
                    jsonObject.addProperty("case", gson.toJson(myCase));
                    jsonObject.addProperty("caseTags", skill);
                    jsonObject.addProperty("user_id",1);
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
        add_pay_min = view.findViewById(R.id.add_pay_min);
        add_pay_max = view.findViewById(R.id.add_pay_max);
        add_btCompany = view.findViewById(R.id.add_btCompany);
        add_tvCompany = view.findViewById(R.id.add_tvCompany);
        add_work_day = view.findViewById(R.id.add_work_day);
        add_btCagetory = view.findViewById(R.id.add_btCagetory);
        add_tvCategory = view.findViewById(R.id.add_tvCagetory);

    }

}
