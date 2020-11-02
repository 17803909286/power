package com.power.home.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;
import com.power.home.R;

/**
 * Created by ZP on 2018/8/17.
 * 设置view的Dialog
 */
public class MaterialViewDialog extends BaseDialog {
    /**
     * container
     */
    private LinearLayout ll_container;
    /***
     * title
     */
    private TextView tv_title;
    /**
     * contentView
     */
    private View contentView;
    /**
     * btn container
     */
    private LinearLayout ll_btns;
    /**
     * left btn
     */
    private TextView tv_btn_left;
    /**
     * right btn
     */
    private TextView tv_btn_right;
    /**
     * title content(标题)
     */
    private String title = "温馨提示";
    /**
     * title textcolor(标题颜色)
     */
    private int titleTextColor = Color.parseColor("#DE000000");
    /**
     * title textsize(标题字体大小,单位sp)
     */
    private float titleTextSize_SP = 18f;
    /**
     * enable title show(是否显示标题)
     */
    private boolean hasTitle = true;
    /**
     * btn textcolor(按钮字体颜色)
     */
    private int leftBtnTextColor = Color.parseColor("#383838");
    private int rightBtnTextColor = Color.parseColor("#468ED0");

    /**
     * btn textsize(按钮字体大小)
     */
    private float leftBtnTextSize_SP = 16f;
    private float rightBtnTextSize_SP = 16f;
    /**
     * btn press color(按钮点击颜色)
     */
    private int btnPressColor = Color.parseColor("#E3E3E3");// #85D3EF,#ffcccccc,#E3E3E3
    /**
     * left btn text(左按钮内容)
     */
    private String btnLeftText = "";
    /**
     * right btn text(右按钮内容)
     */
    private String btnRightText = "";
    /**
     * corner radius,dp(圆角程度,单位dp)
     */
    private float cornerRadius_DP = 3;
    /**
     * background color(背景颜色)
     */
    private int bgColor = Color.parseColor("#ffffff");
    private Drawable bgDrawable = null;
    /**
     * left btn click listener(左按钮接口)
     */
    private OnBtnClickL onBtnLeftClickL;
    /**
     * right btn click listener(右按钮接口)
     */
    private OnBtnClickL onBtnRightClickL;


    public MaterialViewDialog(Context context) {
        super(context);
        widthScale(0.8f);
    }


    @Override
    public View onCreateView() {
        widthScale(mWidthScale);
        ll_container = new LinearLayout(getContext());
        ll_container.setOrientation(LinearLayout.VERTICAL);

        /** title */
        tv_title = new TextView(getContext());
        tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_title);

        /** contentView */
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        contentView.setLayoutParams(layoutParams);
        ll_container.addView(contentView);

        /** btns */
        ll_btns = new LinearLayout(getContext());
        ll_btns.setOrientation(LinearLayout.HORIZONTAL);

        ll_btns.setGravity(Gravity.RIGHT);
        tv_btn_left = new TextView(getContext());
        tv_btn_left.setGravity(Gravity.CENTER);
        tv_btn_left.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
        ll_btns.addView(tv_btn_left);

        tv_btn_right = new TextView(getContext());
        tv_btn_right.setGravity(Gravity.CENTER);
        tv_btn_right.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
        ll_btns.addView(tv_btn_right);

        ll_container.addView(ll_btns);

        return ll_container;
    }

    @Override
    public void setUiBeforShow() {
        float radius = dp2px(cornerRadius_DP);

        /** title */
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        if (hasTitle) {
            tv_title.setPadding(dp2px(20), dp2px(10), dp2px(20), dp2px(0));
        }

        tv_title.setVisibility(hasTitle ? View.VISIBLE : View.GONE);
        tv_title.setText(TextUtils.isEmpty(title) ? "温馨提示" : title);
        tv_title.setTextColor(titleTextColor);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize_SP);

        /** btns */
        ll_btns.setPadding(dp2px(20), hasTitle ? dp2px(10) : dp2px(5), dp2px(10), dp2px(10));

        if (!TextUtils.isEmpty(btnLeftText)) {
            tv_btn_left.setText(btnLeftText);
            tv_btn_left.setTextColor(leftBtnTextColor);
            tv_btn_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftBtnTextSize_SP);
            tv_btn_left.setBackgroundDrawable(CornerUtils.btnSelector(0, bgColor, btnPressColor, 0));
            tv_btn_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBtnLeftClickL != null) {
                        onBtnLeftClickL.onBtnClick();
                    }
                }
            });
        } else {
            tv_btn_left.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(btnRightText)) {
            tv_btn_right.setText(btnRightText);
            tv_btn_right.setTextColor(rightBtnTextColor);
            tv_btn_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightBtnTextSize_SP);
            tv_btn_right.setBackgroundDrawable(CornerUtils.btnSelector(0, bgColor, btnPressColor, 1));
            tv_btn_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBtnRightClickL != null) {
                        onBtnRightClickL.onBtnClick();
                    }
                }
            });
        } else {
            tv_btn_right.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(btnLeftText) && TextUtils.isEmpty(btnRightText)) {
            ll_btns.setVisibility(View.GONE);
        }

        /**set background color and corner radius */
        if (null != bgDrawable) {
            ll_container.setBackgroundDrawable(bgDrawable);
        } else {
            if (0 != bgColor) {
                ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(bgColor, radius));
            }
        }
    }

    @Override
    public void show() {
        Window window = getWindow();
        window.setWindowAnimations(R.style.dialogAnim);
        super.show();
    }

    // --->属性设置

    /**
     * set title text(设置标题内容)
     *
     * @param title
     * @return MaterialViewDialog
     */
    public MaterialViewDialog title(String title) {
        this.title = title;
        return this;
    }

    /**
     * set title textcolor(设置标题字体颜色)
     *
     * @param titleTextColor
     * @return MaterialViewDialog
     */
    public MaterialViewDialog titleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    /**
     * set title textsize(设置标题字体大小)
     *
     * @param titleTextSize_SP
     * @return MaterialViewDialog
     */
    public MaterialViewDialog titleTextSize(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
        return this;
    }

    /**
     * enable title show(设置标题是否显示)
     *
     * @param hasTitle
     * @return MaterialViewDialog
     */
    public MaterialViewDialog hasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
        return this;
    }

    /**
     * set contentView
     * param content
     * return MaterialViewDialog
     */
    public MaterialViewDialog contentView(View contentView) {
        this.contentView = contentView;
        return this;
    }

    /**
     * set btn text(设置按钮文字内容)
     *
     * @return MaterialViewDialog
     */
    public MaterialViewDialog btnText(String... btnText) {
        int len = btnText.length;
        if (len > 1) {
            this.btnLeftText = btnText[0];
            this.btnRightText = btnText[1];
        } else {
            this.btnLeftText = btnText[0];
        }
        return this;
    }

    /**
     * set btn textcolor(设置按钮字体颜色)
     *
     * @param btnTextColor
     * @return MaterialViewDialog
     */
    public MaterialViewDialog btnTextColor(int... btnTextColor) {
        if (btnTextColor.length > 1) {
            this.leftBtnTextColor = btnTextColor[0];
            this.rightBtnTextColor = btnTextColor[1];
        } else {
            this.leftBtnTextColor = btnTextColor[0];
        }
        return this;
    }

    /**
     * set btn textsize(设置字体大小,单位sp)
     *
     * @param btnTextSize_SP
     * @return MaterialViewDialog
     */
    public MaterialViewDialog btnTextSize(float... btnTextSize_SP) {
        if (btnTextSize_SP.length > 1) {
            this.leftBtnTextSize_SP = btnTextSize_SP[0];
            this.rightBtnTextSize_SP = btnTextSize_SP[1];
        } else {
            this.leftBtnTextSize_SP = btnTextSize_SP[0];
        }
        return this;
    }

    /**
     * set btn press color(设置按钮点击颜色)
     *
     * @param btnPressColor
     * @return MaterialViewDialog
     */
    public MaterialViewDialog btnPressColor(int btnPressColor) {
        this.btnPressColor = btnPressColor;
        return this;
    }

    /**
     * set leftbtn click listener(设置左侧按钮监听事件)
     *
     * @param onBtnLeftClick
     */
    public MaterialViewDialog setOnBtnClick(OnBtnClickL... onBtnLeftClick) {
        if (onBtnLeftClick.length > 1) {
            this.onBtnLeftClickL = onBtnLeftClick[0];
            this.onBtnRightClickL = onBtnLeftClick[1];
        } else {
            this.onBtnLeftClickL = onBtnLeftClick[0];
        }
        return this;
    }

    /**
     * set corner radius (设置圆角程度)
     *
     * @param radius
     * @return MaterialViewDialog
     */
    public MaterialViewDialog radius(float radius) {
        this.cornerRadius_DP = radius;
        return this;
    }

    /**
     * set backgroud color(设置背景色)
     *
     * @param bgColor
     * @return MaterialViewDialog
     */
    public MaterialViewDialog bgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    /**
     * set backgroud color(设置背景图)
     *
     * @param bgDrawable
     * @return MaterialViewDialog
     */
    public MaterialViewDialog bgDrawable(Drawable bgDrawable) {
        this.bgDrawable = bgDrawable;
        return this;
    }

}
