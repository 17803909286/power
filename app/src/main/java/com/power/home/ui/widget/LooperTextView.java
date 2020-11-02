package com.power.home.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.Constant;

import java.util.List;

/**
 * Created by ZHP on 2017/7/17.
 */

public class LooperTextView extends FrameLayout {

    private List<String> tipList;
    private List<SpannableString> tipListSpann;
    private List<Integer> ResIds;
    private int curTipIndex = 0;
    private long lastTimeMillis;

    /**
     * 动画持续时长
     */
    private static final int ANIM_DURATION = 1 * 1000;
    private static final String DEFAULT_TEXT_COLOR = "#666666";
    private static final int DEFAULT_TEXT_SIZE = 12;
    private TextView tv_tip_out, tv_tip_in;
    private Animation anim_out, anim_in;
    private Drawable drawable;
    private int current;

    public LooperTextView(Context context) {
        super(context);
        initTipFrame();
        initAnimation();
    }

    public LooperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTipFrame();
        initAnimation();
    }

    public LooperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTipFrame();
        initAnimation();
    }

    private void initTipFrame() {
//        drawable = loadDrawable(R.drawable.icon_notify);
        tv_tip_out = newTextView();
        tv_tip_in = newTextView();
        addView(tv_tip_in);
        addView(tv_tip_out);
    }

    private TextView newTextView() {
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(Color.parseColor(DEFAULT_TEXT_COLOR));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE);
        return textView;
    }

    /**
     * 将资源图片转换为Drawable对象
     *
     * @param ResId
     * @return
     */
    private Drawable loadDrawable(int ResId) {
        Drawable drawable = getResources().getDrawable(ResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    private void initAnimation() {
        anim_out = newAnimation(0, -1);
        anim_in = newAnimation(1, 0);
        anim_in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTipAndPlayAnimationWithCheck();
            }
        });
    }

    private Animation newAnimation(float fromYValue, float toYValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromYValue, Animation.RELATIVE_TO_SELF, toYValue);
        anim.setDuration(ANIM_DURATION);
        anim.setStartOffset(Constant.ANIM_DELAYED_MILLIONS);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    private void updateTipAndPlayAnimationWithCheck() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000) {
            return;
        }
        lastTimeMillis = System.currentTimeMillis();
        updateTipAndPlayAnimation();
    }

    private void updateTipAndPlayAnimation() {
        if (curTipIndex % 2 == 0) {
            updateTip(tv_tip_out);
            tv_tip_in.startAnimation(anim_out);
            tv_tip_out.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_in);
        } else {
            updateTip(tv_tip_in);
            tv_tip_out.startAnimation(anim_out);
            tv_tip_in.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_out);
        }
    }

    private void updateTip(TextView tipView) {
        if (getNextDraw() != 0) {
            SpannableString nextTipSpann = getNextTipSpann();
            drawable = loadDrawable(getNextDraw());
            tipView.setText(nextTipSpann);
        } else {
            String tip = getNextTip();
            tipView.setText(tip);
        }
        if (null != drawable) {
            tipView.setCompoundDrawables(drawable, null, null, null);
            tipView.setCompoundDrawablePadding(10);
        }
    }

    /**
     * 获取下一条消息
     *
     * @return
     */
    private String getNextTip() {
        if (isListEmpty(tipList)) return null;
        current = curTipIndex++ % tipList.size();
        return tipList.get(current);
    }

    private SpannableString getNextTipSpann() {
        if (isListEmpty(tipListSpann)) return null;
        current = curTipIndex++ % tipListSpann.size();
        return tipListSpann.get(current);
    }

    /**
     * 获取下一条draw
     *
     * @return
     */
    private int getNextDraw() {
        if (isListEmpty(ResIds)) return 0;
        return ResIds.get(current);
    }

    public static boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public void setTipList(List<String> tipList) {
        this.tipList = tipList;
        curTipIndex = 0;
        updateTip(tv_tip_out);
        updateTipAndPlayAnimation();
    }

    public void setTipList(List<SpannableString> tipListSpann, List<Integer> ResIds) {
        this.tipListSpann = tipListSpann;
        this.ResIds = ResIds;
        curTipIndex = 0;
        updateTip(tv_tip_out);
        updateTipAndPlayAnimation();
    }

}
