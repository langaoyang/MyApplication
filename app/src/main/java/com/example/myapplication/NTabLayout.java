package com.example.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

public class NTabLayout extends LinearLayout {

    public NTabLayout(Context context) {
        this(context,null);
    }

    public NTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void addTabs(List<String> titles) {

    }

    private void init() {


    }
}
