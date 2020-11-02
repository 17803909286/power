package com.power.home.ui.widget;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by ZHP on 2020/4/14 0014.
 */
public class ScalePageTransformer implements ViewPager.PageTransformer {
    //最小状态时，Size缩小为90%
    private static final float MIN_SCALE = 0.9F;

    @Override
    public void transformPage(View view, float position) {
        float scale = Math.max(MIN_SCALE, 1 - Math.abs(position));
        view.setPivotY(view.getHeight() * 0.7F);
        if (position < -1.0f) {
            view.setScaleY(MIN_SCALE);
        } else if (position <= 0.0f) {
            view.setScaleY(scale);
        } else if (position <= 1.0f) {
            view.setScaleY(scale);
        } else {
            view.setScaleY(MIN_SCALE);
        }
    }
}
