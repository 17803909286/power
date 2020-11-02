package com.power.home.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.power.home.R;

public class UpdataVersionPopup {

    private PopupWindow mPopupWindow;
    private Context mContext;


    private LayoutInflater inflate;
    public TextView tv_describe;//升级描述
    private TextView tv_updata;//立即升级

    public ImageView iv_close;//关闭


    public UpdataVersionPopup(Context context) {
        this.mContext = context;

        inflate = LayoutInflater.from(context);
    }

    public void dismissPop(){
        if (mPopupWindow!=null){
            mPopupWindow.dismiss();
        }
    }

    public void showPopWindow(boolean isForced) {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        backgroundAlpha(0.5f);
        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(!isForced);

        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        mPopupWindow.setClippingEnabled(false);
        ViewGroup popView = (ViewGroup)inflate.inflate(R.layout.item_popup_updata_version, null);


        tv_describe = popView.findViewById(R.id.tv_describe);
        tv_updata = popView.findViewById(R.id.tv_updata);
        iv_close = popView.findViewById(R.id.iv_close);


        mPopupWindow.setContentView(popView);
        mPopupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new PopDismissListener());

        if (isForced) {
            iv_close.setVisibility(View.GONE);
        } else {
            iv_close.setVisibility(View.VISIBLE);
        }

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPop();
            }
        });



    }
    public void setUpdata(View.OnClickListener listener) {
        tv_updata.setOnClickListener(listener);
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
            backgroundAlpha(1f);
        }
    }

}
