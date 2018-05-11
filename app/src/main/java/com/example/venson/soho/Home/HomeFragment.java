package com.example.venson.soho.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.venson.soho.Case.CaseFragment;
import com.example.venson.soho.Home.Case_Member_Tab.HomeCaseFragment;
import com.example.venson.soho.Home.Case_Member_Tab.HomeMemberFragment;
import com.example.venson.soho.Home.CategoryTab.DesignFragment;
import com.example.venson.soho.Home.CategoryTab.GetAllFragment;
import com.example.venson.soho.Home.CategoryTab.MediaFragment;
import com.example.venson.soho.Home.CategoryTab.NetWorkFragment;
import com.example.venson.soho.Home.CategoryTab.SalesFragment;
import com.example.venson.soho.Home.CategoryTab.ServiceFragment;
import com.example.venson.soho.Home.CategoryTab.TranslationFragment;
import com.example.venson.soho.R;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeCaseFragment";
    private Toolbar toolbar;
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
//        rvCase.setLayoutManager(new LinearLayoutManager(getActivity()));
        getCag_tablayout();
        getCase_mamber_tabLayout();
        initContent();
        return view;
    }

    public TabLayout getCag_tablayout() {
        cag_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            Fragment fragment;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragment = new GetAllFragment();
                        switchFragment(fragment);
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
            Fragment fragment;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new HomeCaseFragment();
                        switchFragment(fragment);
                        return;
                    case 1:
                        fragment = new HomeMemberFragment();
                        switchFragment(fragment);
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
        return case_mamber_tabLayout;
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
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
        cag_tablayout = view.findViewById(R.id.cag_tablayout);
        case_mamber_tabLayout = view.findViewById(R.id.case_mamber_tabLayout);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.case_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void initContent() {
        Fragment fragment = new GetAllFragment();
        switchFragment(fragment);
    }

} //end
