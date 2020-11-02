package com.power.home.interfaces;

import android.view.View;

/**
 * Created by zhangpeng on 2017/9/22.
 */

public abstract class OnMultiClickListener implements View.OnClickListener{
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;

    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View v) {
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            onMultiClick(v);
        }
    }
}
