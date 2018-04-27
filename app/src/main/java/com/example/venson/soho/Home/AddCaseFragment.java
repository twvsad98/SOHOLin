package com.example.venson.soho.Home;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venson.soho.R;



public class AddCaseFragment extends Fragment {
    private Spinner spinner;
    private Toolbar toolbar;
    private TextView tvToolbar_title;
    private EditText add_case,add_cagetory,add_content,add_release,add_expire;
    private ImageButton add_done_id, add_cancel_id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_case_layout, container, false);
        findViews(view);
        // Spinner
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.city, android.R.layout.simple_spinner_item );
        spinner.setAdapter(cityAdapter);
        setHasOptionsMenu(true);
        tvToolbar_title.setText(R.string.add_case_title);

        // toolbar done and cancel
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        add_done_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        add_cancel_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        return view;
    }

    private void findViews(View view) {
        spinner = view.findViewById(R.id.spinner_id);
        toolbar = view.findViewById(R.id.tool_bar);
        tvToolbar_title = view.findViewById(R.id.tvTool_bar_title);
        add_done_id = view.findViewById(R.id.add_done_id);
        add_cancel_id = view.findViewById(R.id.add_cancel_id);
        add_case = view.findViewById(R.id.add_case);
        add_cagetory = view.findViewById(R.id.add_category);
        add_content = view.findViewById(R.id.add_content);
        add_release = view.findViewById(R.id.add_release);
        add_expire = view.findViewById(R.id.add_expire);
    }


}
