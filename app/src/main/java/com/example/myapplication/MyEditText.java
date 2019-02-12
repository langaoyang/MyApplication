package com.example.myapplication;

import android.content.Context;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;


public class MyEditText extends EditText implements TextWatcher {
    private static final String TAG = "MyEditText";
    private final Context context;
    private boolean isFromUser = true;  //手动 true ,setText false
    public MyEditText(Context context) {
        super(context);
        this.context = context;
        initEt();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initEt();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initEt();
    }

    private void initEt() {
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.e(TAG, "beforeTextChanged: " + SystemClock.currentThreadTimeMillis() );
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.e(TAG, "onTextChanged: "   + SystemClock.currentThreadTimeMillis());
        if (myTextChangerListener != null) {
            myTextChangerListener.onTextChanged(s, isFromUser);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.e(TAG, "afterTextChanged: " + SystemClock.currentThreadTimeMillis());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        isFromUser = false;
        super.setText(text, type);
        isFromUser = true;
    }


    public  interface  MyTextChangerListener {

        void onTextChanged(CharSequence s, boolean isFromUser);

    }


    MyTextChangerListener myTextChangerListener;

    public void setMyTextChangerListener(MyTextChangerListener myTextChangerListener) {
        this.myTextChangerListener = myTextChangerListener;
    }
}
