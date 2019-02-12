package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyTabLayout extends FrameLayout {

    private final Context mContext;
    private int mHeight;
    private float mSelectedTextSize;
    private float mUnSelectedTextSize;
    private List<String> mTitles;
    private int mLastPosition; // 上一次点击的位置
    private int mCurrentTab; // 上一次点击的位置
    private int mTextSelectColor;
    private int mTextUnselectColor;
    private float mTabWidth;
    private LinearLayout mTabsContainer;

    public MyTabLayout(Context context) {
        this(context, null);
    }

    public MyTabLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setFillViewport(true);//设置滚动视图是否可以伸缩其内容以填充视口
        setWillNotDraw(false);//重写onDraw方法,需要调用这个方法来清除flag
        setClipChildren(false);
        setClipToPadding(false);
        this.mContext = context;
        mTabsContainer = new LinearLayout(context);
        addView(mTabsContainer);


        obtainAttributes(context, attrs);
        //get layout_height
        String height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");

        if (height.equals(ViewGroup.LayoutParams.MATCH_PARENT + "")) {
        } else if (height.equals(ViewGroup.LayoutParams.WRAP_CONTENT + "")) {
        } else {
            int[] systemAttrs = {android.R.attr.layout_height};
            TypedArray a = context.obtainStyledAttributes(attrs, systemAttrs);
            mHeight = a.getDimensionPixelSize(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            a.recycle();
        }

    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.my_tab_layout);
        mSelectedTextSize = ta.getDimension(R.styleable.my_tab_layout_lan_selected_textSize, dp2px(14));
        mUnSelectedTextSize = ta.getDimension(R.styleable.my_tab_layout_lan_unSelected_textSize, dp2px(13));
        mTextSelectColor = ta.getColor(R.styleable.my_tab_layout_lan_Selected_textColor, Color.parseColor("#ffffff"));
        mTextUnselectColor = ta.getColor(R.styleable.my_tab_layout_lan_unSelected_textColor, Color.parseColor("#AAffffff"));
        mTabWidth = ta.getDimension(R.styleable.my_tab_layout_lan_tab_width, dp2px(-1));
        ta.recycle();
    }


    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    public void setTitles(List<String> titles) {

        if (titles == null) {
            throw new IllegalArgumentException("titles cannot be null");
        }
        if (mTabWidth < 0) {
            throw new IllegalArgumentException("tabWidth attr is Empty");
        }

        this.mTitles = titles;
        notifyDataSetChanged();
        updateTabStyles();

    }

    private void notifyDataSetChanged() {
        mTabsContainer.removeAllViews();
        View tabView;

        for (int i = 0; i < mTitles.size(); i++) {
            tabView = View.inflate(mContext, R.layout.my_tab_layout, null);
            String pageTitle = mTitles.get(i);
            addTab(i, pageTitle, tabView);
        }
    }

    private void addTab(final int position, String pageTitle, final View tabView) {


        ImageView ivMyTab = tabView.findViewById(R.id.iv_my_tab);
        TextView tvMyTab = tabView.findViewById(R.id.tv_my_tab);
        tvMyTab.setText(pageTitle);
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = indexOfChild(tabView);

                if (position == 0 || position == mTitles.size() - 1) {
                    return;
                }
                if (position != -1) {
                    if (mLastPosition != position) {
                        scrollToPosition(position);
                        mLastPosition = position;
                        if (mListener != null) {
                            mListener.onTabSelect(position);
                        }
                    } else {
                        if (mListener != null) {
                            mListener.onTabReselect(position);
                        }
                    }
                }
            }
        });
        LayoutParams lp_tab = new LayoutParams((int) mTabWidth, FrameLayout.LayoutParams.MATCH_PARENT);
        mTabsContainer.addView(tabView, position, lp_tab);
    }

    private void updateTabStyles() {
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
//            v.setPadding((int) mTabPadding, v.getPaddingTop(), (int) mTabPadding, v.getPaddingBottom());
            TextView tv_tab_title = v.findViewById(R.id.tv_my_tab);
            ImageView iv_my_tab = v.findViewById(R.id.iv_my_tab);
            tv_tab_title.setTextColor(i == mCurrentTab ? mTextSelectColor : mTextUnselectColor);
            if (mCurrentTab == i) {
                tv_tab_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSelectedTextSize);
                iv_my_tab.setVisibility(VISIBLE);
            } else {
                tv_tab_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, mUnSelectedTextSize);
                iv_my_tab.setVisibility(GONE);
            }

//                tv_tab_title.setPadding((int) mTabPadding, 0, (int) mTabPadding, 0);
//                if (mTextAllCaps) {
//                    tv_tab_title.setText(tv_tab_title.getText().toString().toUpperCase());
//                }

//                if (mTextBold == TEXT_BOLD_BOTH) {
//                    tv_tab_title.getPaint().setFakeBoldText(true);
//                } else if (mTextBold == TEXT_BOLD_NONE) {
//                    tv_tab_title.getPaint().setFakeBoldText(false);
//                }


        }
    }


    private void scrollToPosition(int position) {
        int width = mTabsContainer.getChildAt(position).getWidth();
        int offset = (mLastPosition - position) * width;
        scrollBy(offset,0);

    }




    public interface OnTabSelectListener {
        void onTabSelect(int position);

        void onTabReselect(int position);
    }

    private OnTabSelectListener mListener;
}
