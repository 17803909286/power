package com.power.home.ui.widget;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.power.home.common.util.LogUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.ui.widget.floatview.FloatingView;

/**
 * Created by ZHP on 2020/5/16 0016.
 */
public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {


    public MyGestureListener() {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (null != e1 && null != e2 && null != FloatingView.get().getView() &&
                StringUtil.isNotNullString(SharePreferencesUtils.getFloatStatus())) {
            float y1 = e1.getY();
            float y2 = e2.getY();
            int offsetY = (int) (y2 - y1);

            float x1 = e1.getX();
            float x2 = e2.getX();
            int offsetX = (int) (x2 - x1);
            //斜着滑 Y轴的滑动距离 大于 X轴的滑动距离 才生效
            if (Math.abs(offsetY) > Math.abs(offsetX)) {
                if (offsetY > 10) {
                    if (FloatingView.get().getView().getVisibility() == View.GONE) {
                        LogUtil.e("FloatingView","VISIBLE======滑动===========================");
                        FloatingView.get().getView().setVisibility(View.VISIBLE);
                        FloatingView.get().getView().startAnimation(UIUtil.getShowAnimation());
                    }
                } else {
                    if (FloatingView.get().getView().getVisibility() == View.VISIBLE) {
                        LogUtil.e("FloatingView","GONE======滑动===========================");
                        FloatingView.get().getView().setVisibility(View.GONE);
                        FloatingView.get().getView().startAnimation(UIUtil.getHideAnimation());
                    }
                }
            }
        }
        return false;
    }


}
