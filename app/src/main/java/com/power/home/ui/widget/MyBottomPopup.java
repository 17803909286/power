package com.power.home.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.ui.adapter.MyPopupBottomAdapter;

import java.util.List;

public class MyBottomPopup {

    private PopupWindow mPopupWindow;
    private Context mContext;
    private ListView mLv;
    private TextView tv_cancle;
    private LayoutInflater inflate;
    private List<String> list;

    public MyBottomPopup(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
        inflate = LayoutInflater.from(context);
    }

    public void dismissPop() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    public void showPopWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        backgroundAlpha(0.5f);
        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        ViewGroup popView = (ViewGroup) inflate.inflate(R.layout.item_popwind_listview, null);

        mLv = (ListView) popView.findViewById(R.id.pop_listview);
        tv_cancle = ((TextView) popView.findViewById(R.id.tv_cancle));
        mPopupWindow.setContentView(popView);
        mPopupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new PopDismissListener());

        MyPopupBottomAdapter adapter = new MyPopupBottomAdapter(mContext, list);
        mLv.setAdapter(adapter);


        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }

    public void setLvClickListener(AdapterView.OnItemClickListener listener) {
        mLv.setOnItemClickListener(listener);
    }

    /**
     * 设置弹出Popupwindow以外区域的透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        lp.dimAmount = 1.0f;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);

    }

    /**
     * PopupWindow 消失之后设置背景透明度为正常
     */
    private class PopDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
//            backgroundAlpha(1f);
            WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
            lp.alpha = 1f; //0.0-1.0
            lp.dimAmount = 0.0f;
            ((Activity) mContext).getWindow().setAttributes(lp);
            ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }

}
