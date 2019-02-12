package com.example.myapplication;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class SimpleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SimpleAdapter() {
        super(R.layout.item_simple);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_simple, item);
        helper.itemView.setBackgroundColor(helper.getAdapterPosition()%2 ==0 ?Color.parseColor("#f2f2f2"):Color.WHITE );
    }
}
