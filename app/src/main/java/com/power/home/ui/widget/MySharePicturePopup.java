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
import android.widget.PopupWindow;
import android.widget.TextView;

import com.power.home.R;


/**
 * Created by XWL on 2020/7/22.
 * Description:
 */
public class MySharePicturePopup {

    private PopupWindow mPopupWindow;
    private Context mContext;


    private LayoutInflater inflate;
    public TextView tvSharewx;//微信
    public TextView tvShareFriend;//朋友圈
    public TextView tv_share_poster;//生成海报



    public MySharePicturePopup(Context context) {
        this.mContext = context;

        inflate = LayoutInflater.from(context);
    }

    public void dismissPop(){
        if (mPopupWindow!=null){
            mPopupWindow.dismiss();
        }
    }

    public void showPopWindow( ) {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        backgroundAlpha(0.8f);
        mPopupWindow = new PopupWindow(mContext);
        mPopupWindow.setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);

        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
        mPopupWindow.setClippingEnabled(false);
        ViewGroup popView = (ViewGroup)inflate.inflate(R.layout.item_popwind_share, null);

        View rlContent = popView.findViewById(R.id.rlContent);


        tvSharewx = popView.findViewById(R.id.tv_share_wx);
        tvShareFriend = popView.findViewById(R.id.tv_share_friends);
        tv_share_poster = popView.findViewById(R.id.tv_share_poster);



        mPopupWindow.setContentView(popView);
        mPopupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new PopDismissListener());



        rlContent.setOnClickListener(new View.OnClickListener() {
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
            backgroundAlpha(1f);
        }
    }

}