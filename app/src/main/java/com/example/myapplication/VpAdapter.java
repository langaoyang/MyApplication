package com.example.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mData;


    public VpAdapter(Context context) {
        mContext = context;

    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_simple, null, false);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void addData(List<String> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }

        mData.addAll(data);
        notifyDataSetChanged();


    }

}
