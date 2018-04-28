package com.example.venson.soho.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venson.soho.Common;
import com.example.venson.soho.Home.CategoryTab.GetAllFragment;
import com.example.venson.soho.Home.CategoryTab.TranslationFragment;
import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeCaseFragment";
    private MyTask caseGetAllTask, caseDeleteTask;
    private Toolbar toolbar;
    private RecyclerView rvCase;
    private TabLayout cag_tablayout,case_mamber_tabLayout;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home_layout, container, false);
        findView(view);
        setHasOptionsMenu(true);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        showAllCases();
        getCag_tablayout();
        getCase_mamber_tabLayout();
        rvCase.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public TabLayout getCag_tablayout() {
        cag_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return cag_tablayout;
    }

    public TabLayout getCase_mamber_tabLayout() {
        case_mamber_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return case_mamber_tabLayout;
    }

    
    private void showAllCases() {
        if (Common.networkConnected(getActivity())) {
            String url = Common.URL + "/CaseServlet";
            List<Case> cases = null;
            Gson gson = new GsonBuilder().setDateFormat("yyy-MM-dd HH:mm:ss").create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "getAll");
            String jsonOut = jsonObject.toString();
            caseGetAllTask = new MyTask(url, jsonOut);
            try {
                String jsonIn = caseGetAllTask.execute().get();
                Log.d(TAG, jsonIn);
                Type listType = new TypeToken<List<Case>>() {
                }.getType();
                cases = new Gson().fromJson(jsonIn, listType);
                gson.fromJson(jsonObject, listType);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            if (cases == null || cases.isEmpty()) {
                Common.showToast(getActivity(), R.string.msg_NoCases);
            } else {
                rvCase.setAdapter(new HomeCaseAdapter(cases, getContext()));

            }
        } else {
            Common.showToast(getActivity(), R.string.msg_NoNetwork);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.toolbar_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.toolbar_search:
                return true;

            case R.id.toolbar_item_add:
                Fragment fragment = new AddCaseFragment();
                getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                return true;

            case R.id.toolbar_item_filter:
                return true;

            default:
                break;
        }
        return false;
    }

    private void findView(View view) {
        toolbar = view.findViewById(R.id.tool_bar);
        rvCase = view.findViewById(R.id.rvCase);
        cag_tablayout = view.findViewById(R.id.cag_tablayout);
        case_mamber_tabLayout = view.findViewById(R.id.case_mamber_tabLayout);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
    }


    private class HomeCaseAdapter extends RecyclerView.Adapter<HomeCaseAdapter.MyViewHoler> {
        private LayoutInflater layoutInflater;
        private List<Case> cases;

        HomeCaseAdapter(List<Case> cases, Context context) {
            layoutInflater = LayoutInflater.from(context);
            this.cases = cases;
        }
        class MyViewHoler extends RecyclerView.ViewHolder {
            TextView case_cotent,case_pay_min,case_date,case_skill,case_location,case_name_id;
            MyViewHoler(View itemView) {
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
        public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.case_item_cardview, parent, false);
            return new MyViewHoler(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHoler myViewHoler, int position) {
         final Case myCase = cases.get(position);
         myViewHoler.case_name_id.setText(myCase.getName());
         myViewHoler.case_cotent.setText(myCase.getDescription());
         myViewHoler.case_pay_min.setText((int) myCase.getBudget());
         myViewHoler.case_date.setText((CharSequence) myCase.getRecruit_start());
//         myViewHoler.case_skill.setText();
//         myViewHoler.case_location.setText();
        }

        @Override
        public int getItemCount() {
            return cases.size();
        }
    }


} //end
