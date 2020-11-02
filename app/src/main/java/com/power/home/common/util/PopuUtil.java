package com.power.home.common.util;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.power.home.R;
import com.power.home.ui.widget.MyPopupWindow;


/**
 * Created by ZHP on 2017/7/4.
 */

public class PopuUtil {


    private View viewLayout;


    public MyPopupWindow initAtLocationPopuWrapOutFalse(Activity activity, int layout) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        viewLayout = View.inflate(activity, layout, null);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(UIUtil.getColor(R.color.colorBlackCC000000)));
        myPopupWindow.setOutsideTouchable(false);
        myPopupWindow.setClippingEnabled(false);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAtLocation(viewLayout, Gravity.CENTER, 0, 0);
        myPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                activity.finish();
            }
        });
        return myPopupWindow;
    }


    public MyPopupWindow initAtLocationPopuWrap(Activity activity, int layout) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        viewLayout = View.inflate(activity, layout, null);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAtLocation(viewLayout, Gravity.CENTER, 0, 0);
        return myPopupWindow;
    }


    public MyPopupWindow initAtBottomPopup(Activity activity, int layout) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        viewLayout = View.inflate(activity, layout, null);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAtLocation(viewLayout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        return myPopupWindow;
    }


    public MyPopupWindow initDropDownPopu(Activity activity, int layout, View view) {
        viewLayout = View.inflate(activity, layout, null);
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        myPopupWindow.showAsDropDown(view);
        return myPopupWindow;
    }


    public MyPopupWindow initDropUpPopu(Activity activity, int layout, View view, int gravity) {
        viewLayout = View.inflate(activity, layout, null);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        myPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);
        viewLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int measuredWidth = myPopupWindow.getContentView().getMeasuredWidth();
        int measuredHeight = myPopupWindow.getContentView().getMeasuredHeight();
        myPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                (location[0] + view.getWidth() / 2) - measuredWidth / 2, location[1] - measuredHeight);
        return myPopupWindow;
    }

    public MyPopupWindow initAtLocationPopuWrapUp(Activity activity, int layout, View view, int width, int height) {
        MyPopupWindow myPopupWindow = new MyPopupWindow(activity);
        viewLayout = View.inflate(activity, layout, null);
        viewLayout.measure(makeDropDownMeasureSpec(myPopupWindow.getWidth()),
                makeDropDownMeasureSpec(myPopupWindow.getHeight()));
        myPopupWindow.setWidth(UIUtil.dip2px(width));
        myPopupWindow.setHeight(UIUtil.dip2px(height));
        myPopupWindow.setContentView(viewLayout);
        myPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        myPopupWindow.setOutsideTouchable(true);
        myPopupWindow.setFocusable(true);

        int offsetY = -myPopupWindow.getContentView().getMeasuredHeight();

        myPopupWindow.showAtLocation(view, Gravity.CENTER, 0, offsetY);
        return myPopupWindow;
    }

    @SuppressWarnings("ResourceType")
    private static int makeDropDownMeasureSpec(int measureSpec) {
        int mode;
        if (measureSpec == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mode = View.MeasureSpec.UNSPECIFIED;
        } else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), mode);
    }

    public View getViewLayout() {
        return viewLayout;
    }
}
