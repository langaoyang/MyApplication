package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyEditText et;
    private static final String TAG = "MainActivity";
    private View llContent;
    private TextView textView;
    private boolean isSelected;
    private RecyclerView recyclerView;
    private SimpleAdapter simpleAdapter;
    private PagerSnapHelper snapHelper;
    private int currentPosition;
    private ViewPager vp;
    private MyTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        method1();
        method2();




    }

    private void method2() {
        tabLayout = findViewById(R.id.tab_layout);
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            titles.add("position" + i);
        }
        tabLayout.setTitles(titles);
    }

   /* private void method1() {
        llContent = findViewById(R.id.ll_content);
        textView = findViewById(R.id.tv_selected);
        et = findViewById(R.id.airline_numb);
        vp = findViewById(R.id.viewPager);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (llContent.getVisibility() == View.VISIBLE) {
////                    ViewUtils.hideView(llContent);
//                    llContent.setVisibility(View.GONE);
//                }else
//                if (llContent.getVisibility() == View.GONE) {
////                    ViewUtils.showView(llContent);
//                    llContent.setVisibility(View.VISIBLE);
//                }

//                isSelected = !isSelected;
//                textView.setSelected(isSelected);
//                et.setText("jkdjfjakjdfkajd");
                Log.e(TAG, "onClick: " + currentPosition);
                recyclerView.scrollToPosition(currentPosition++);
            }
        });

//        et = findViewById(R.id.airline_numb);
////        et.setInputType(InputType.TYPE_NULL);
//        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                Log.e(TAG, "onFocusChange: " + hasFocus);
//            }
//        });
//        et.setMyTextChangerListener(new MyEditText.MyTextChangerListener() {
//            @Override
//            public void onTextChanged(CharSequence s, boolean isFromUser) {
//                Log.e(TAG, "s: " + s + "===============" + "isFromUser: " + isFromUser);
//            }
//        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });

        snapHelper = new PagerSnapHelper();
        recyclerView.setNestedScrollingEnabled(false);
        snapHelper.attachToRecyclerView(recyclerView);
        simpleAdapter = new SimpleAdapter();
        recyclerView.setAdapter(simpleAdapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("position  = " + i);
        }
        simpleAdapter.setNewData(data);


        simpleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        VpAdapter adapter = new VpAdapter(this);
        //设置Page间间距
        vp.setPageMargin(20);
        //设置缓存的页面数量
        vp.setOffscreenPageLimit(3);
        //设置自定义滑动动画
        //  viewPager.setPageTransformer(false, new GalleryTransformer());


//        vp.setOnTouchListener(new View.OnTouchListener() {
//            int flage = 0;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        flage = 0;
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        flage = 1;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        if (flage == 0) {
//                            int item = vp.getCurrentItem();
//                            if (item == 0) {
//                                Toast.makeText(MainActivity.this, "点击1", Toast.LENGTH_SHORT).show();
//                            } else if (item == 1) {
//                                Toast.makeText(MainActivity.this, "点击2", Toast.LENGTH_SHORT).show();
//                            } else if (item == 2) {
//                                Toast.makeText(MainActivity.this, "点击3", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        break;
//                }
//                return false;
//            }
//        });


        vp.setAdapter(adapter);
        adapter.addData(data);
    }*/
}
