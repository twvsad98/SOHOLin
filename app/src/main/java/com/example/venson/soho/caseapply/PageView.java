package com.example.venson.soho.caseapply;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by rick on 2016/7/9.
 */

public abstract class PageView extends LinearLayout {
    public PageView(Context context) {
        super(context);
    }
    public abstract void refresh();
}
