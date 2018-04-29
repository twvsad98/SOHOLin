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

import com.example.venson.soho.Home.CategoryTab.DesignFragment;
import com.example.venson.soho.Home.CategoryTab.MediaFragment;
import com.example.venson.soho.Home.CategoryTab.NetWorkFragment;
import com.example.venson.soho.Home.CategoryTab.SalesFragment;
import com.example.venson.soho.Home.CategoryTab.ServiceFragment;
import com.example.venson.soho.Home.CategoryTab.TranslationFragment;
import com.example.venson.soho.myCase;
import com.example.venson.soho.Common;
import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeRefreshLayout.setRefreshing(true);
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
        showAllCases();
        getCag_tablayout();
        getCase_mamber_tabLayout();
        rvCase.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public TabLayout getCag_tablayout() {
        cag_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            Fragment fragment;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        return;
                    case 1:
                        fragment = new TranslationFragment();
                        switchFragment(fragment);
                        Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
                        return;
                    case 2:
                        fragment = new DesignFragment();
                        Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
                        switchFragment(fragment);
                        return;
                    case 3:
                        fragment = new SalesFragment();
                        switchFragment(fragment);
                        Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
                        return;
                    case 4:
                        fragment = new MediaFragment();
                        switchFragment(fragment);
                        Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
                        return;
                    case 5:
                        fragment = new NetWorkFragment();
                        switchFragment(fragment);
                        Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
                        return;
                    case 6:
                        fragment = new ServiceFragment();
                        switchFragment(fragment);
                        Toast.makeText(getActivity(), tab.getText(), Toast.LENGTH_SHORT).show();
                        return;

                }
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
            List<myCase> myCases = null;
            Gson gson = new GsonBuilder().setDateFormat("yyy-MM-dd HH:mm:ss").create();
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
                rvCase.setAdapter(new HomeCaseAdapter(myCases, getContext()));

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
                Fragment fragment = new CaseInsertFragment();
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
        private List<myCase> myCases;

        HomeCaseAdapter(List<myCase> myCases, Context context) {
            layoutInflater = LayoutInflater.from(context);
            this.myCases = myCases;
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
         final myCase myCase = myCases.get(position);
         myViewHoler.case_name_id.setText(myCase.getName());
         myViewHoler.case_cotent.setText(myCase.getDescription());
         myViewHoler.case_pay_min.setText(String.valueOf(myCase.getBudget()));
         myViewHoler.case_date.setText(String.valueOf(myCase.getRecruit_start()));
         myViewHoler.case_skill.setText(myCase.getSkill());
         myViewHoler.case_location.setText(myCase.getLocation());
        }

        @Override
        public int getItemCount() {
            return myCases.size();
        }
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.case_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (caseGetAllTask != null) {
            caseGetAllTask.cancel(true);
        }
    }

} //end
