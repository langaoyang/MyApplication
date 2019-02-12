package com.example.myapplication;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class ViewUtils {

    public static void showView(View view) {

        if (view.getVisibility() == View.VISIBLE) {
            return;
        }


        TranslateAnimation showAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f);
        showAnim.setDuration(500);
        view.startAnimation(showAnim);
        view.setVisibility(View.VISIBLE);
    }

    public static void hideView(View view) {

        if (view.getVisibility() == View.GONE) {
            return;
        }


        TranslateAnimation hideAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        hideAnim.setDuration(500);
        view.startAnimation(hideAnim);
        view.setVisibility(View.GONE);
    }


}
