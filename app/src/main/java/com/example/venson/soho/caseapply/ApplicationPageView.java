package com.example.venson.soho.caseapply;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.venson.soho.R;

import java.util.ArrayList;
import java.util.List;

public class ApplicationPageView extends PageView {

    public ApplicationPageView(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.page_content, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText("Page one");
        addView(view);
    }
    @Override
    public void refresh() {
    }

}