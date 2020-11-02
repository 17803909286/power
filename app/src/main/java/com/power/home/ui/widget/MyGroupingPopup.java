package com.power.home.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.power.home.R;

public class MyGroupingPopup {

    private PopupWindow mPopupWindow;
    private Context mContext;


    private LayoutInflater inflate;
    public RecyclerView recycle_course;
    private View iv_title;


    public MyGroupingPopup(Context context) {
        this.mContext = context;

        inflate = LayoutInflater.from(context);
    }

    public void dismissPop(){

        if (mPopupWindow!=null){
            mPopupWindow.dismiss();
        }

    }

    public void showPopWindow(View view) {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }

        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
//        backgroundAlpha(0.5f);
        View popView = inflate.inflate(R.layout.item_popwind_grouping, null);
        iv_title = popView.findViewById(R.id.iv_title);
        mPopupWindow = new PopupWindow(popView,ViewGroup.LayoutParams.MATCH_PARENT,heightPixels*6/10);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1]-mPopupWindow.getHeight());


        recycle_course = popView.findViewById(R.id.recycle_course);


        mPopupWindow.setContentView(popView);

        mPopupWindow.setOnDismissListener(new PopDismissListener());
        iv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPop();
            }
        });

    }

    /**
     * 设置弹出Popupwindow以外区域的透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = ((Activity)mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity)mContext).getWindow().setAttributes(lp);
    }

    /**
     * PopupWindow 消失之后设置背景透明度为正常
     */
    private class PopDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
//            backgroundAlpha(1f);

        }
    }

}
