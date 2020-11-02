package com.power.home.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.MaterialDialog;
import com.power.home.R;

/**
 * Created by ZP on 2018/8/17.
 */

public class MaterialBgDialog extends MaterialDialog {
    public MaterialBgDialog(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();
        mBgColor = getContext().getResources().getColor(R.color.float_transparent);
        mBtnPressColor = mBgColor;
        /**set background color and corner radius */
        float radius = dp2px(mCornerRadius);
        mLlContainer.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.dialog_bg));
        mTvBtnLeft.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
        mTvBtnRight.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
        mTvBtnMiddle.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
    }

    public void setNoBg() {
        mLlContainer.setBackgroundColor(getContext().getResources().getColor(R.color.float_transparent));
    }
}
