package com.power.home.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.power.home.R;

public class LoginPopup {

    private PopupWindow mPopupWindow;
    private Context mContext;


    private LayoutInflater inflate;

    private TextView tv_title;//标题
    private Button button_creat_commit_sure;//标题




    public LoginPopup(Context context) {
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

//        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        mPopupWindow.setClippingEnabled(false);
        ViewGroup popView = (ViewGroup)inflate.inflate(R.layout.item_popup_login, null);


        tv_title = popView.findViewById(R.id.tv_title);
        button_creat_commit_sure = popView.findViewById(R.id.button_creat_commit_sure);
        tv_title.setText("你的账号已在其他设备登录");

        mPopupWindow.setContentView(popView);
        mPopupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new PopDismissListener());





    }
    public void setSure(View.OnClickListener listener) {
        button_creat_commit_sure.setOnClickListener(listener);
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
