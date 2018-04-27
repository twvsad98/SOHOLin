package com.example.venson.soho.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import com.example.venson.soho.R;


public class HomeFragment extends Fragment {
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home_layout, container, false);
        findView(view);
        setHasOptionsMenu(true);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        return view;
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
    }


}
