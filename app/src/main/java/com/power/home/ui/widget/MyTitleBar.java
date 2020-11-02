package com.power.home.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.power.home.R;


/**
 * 二级页面中用到的顶部titleBar
 */
public class MyTitleBar extends RelativeLayout {

    protected RelativeLayout leftLayout;
    protected ImageView leftImage;
    protected TextView leftText;
    protected RelativeLayout rightLayout;
    protected ImageView rightImage;
    protected ImageView rightImage2;
    protected TextView rightText;
    protected TextView titleView;
    protected RelativeLayout titleLayout;



    public MyTitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyTitleBar(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.mytitlebar_layout, this);
        leftLayout = (RelativeLayout) findViewById(R.id.left_layout);
        leftImage = (ImageView) findViewById(R.id.left_image);
        leftText = (TextView) findViewById(R.id.left_text);
        rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
        rightImage = (ImageView) findViewById(R.id.right_image);
        rightImage2 = (ImageView) findViewById(R.id.right_image2);
        rightText = (TextView) findViewById(R.id.right_text);
        titleView = (TextView) findViewById(R.id.title);
        titleLayout = (RelativeLayout) findViewById(R.id.root);


        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
            String title = ta.getString(R.styleable.MyTitleBar_MytitleBarTitle);
            titleView.setText(title);
            int textcolor = ta.getColor(R.styleable.MyTitleBar_MytitleBarTitleColor, Color.parseColor("#333333"));
            titleView.setTextColor(textcolor);

            Drawable leftDrawable = ta.getDrawable(R.styleable.MyTitleBar_MytitleBarLeftImage);
            if (null != leftDrawable) {
                leftImage.setImageDrawable(leftDrawable);
            }
            String lefttext = ta.getString(R.styleable.MyTitleBar_MytitleBarLeftText);
            leftText.setText(lefttext);

            Drawable rightDrawable = ta.getDrawable(R.styleable.MyTitleBar_MytitleBarRightImage);
            if (null != rightDrawable) {
                rightImage.setImageDrawable(rightDrawable);
            }
            //
            Drawable rightDrawable2 = ta.getDrawable(R.styleable.MyTitleBar_MytitleBarRightImage2);
            if (null != rightDrawable2) {
                rightImage2.setImageDrawable(rightDrawable2);
            }

            String righttext = ta.getString(R.styleable.MyTitleBar_MytitleBarRightText);
            rightText.setText(righttext);
            int righttextcolor = ta.getColor(R.styleable.MyTitleBar_MytitleBarRightTextColor, Color.parseColor("#999999"));
            rightText.setTextColor(righttextcolor);
            Drawable background = ta.getDrawable(R.styleable.MyTitleBar_MytitleBarBackground);
            if (null != background) {
                titleLayout.setBackgroundDrawable(background);
            }

            ta.recycle();
        }
    }

    public void setLeftImageResource(int resId) {
        leftImage.setImageResource(resId);
    }

    public void setRightImageResource(int resId) {
        rightImage.setImageResource(resId);
    }

    public void setLeftTextResource(String text) {
        leftText.setText(text);
    }

    public void setRightTextResource(String text) {
        rightText.setText(text);
    }

    public void setLeftLayoutClickListener(OnClickListener listener) {
        leftLayout.setOnClickListener(listener);
    }

    public void setRightLayoutClickListener(OnClickListener listener) {
        rightLayout.setOnClickListener(listener);
    }

    public void setTitleClickListener(OnClickListener listener) {
        titleView.setOnClickListener(listener);
    }

    public void setLeftLayoutVisibility(int visibility) {
        leftLayout.setVisibility(visibility);
    }

    public void setRightLayoutVisibility(int visibility) {
        rightLayout.setVisibility(visibility);
    }

    public void setTitle(CharSequence title) {
        titleView.setText(title);
    }

    public void setTitleColor(int color) {
        titleView.setTextColor(color);
    }

    public void setBackgroundColor(int color) {
        titleLayout.setBackgroundColor(color);
    }

    public RelativeLayout getLeftLayout() {
        return leftLayout;
    }

    public RelativeLayout getRightLayout() {
        return rightLayout;
    }

    public ImageView getRightImage2() {
        return rightImage2;
    }

    public void setRightImage2(ImageView rightImage2) {
        this.rightImage2 = rightImage2;
    }

    /**
     * 外部调用，回调leftLayout的点击监听
     */
    public void performLeftClick() {
        leftLayout.performClick();
    }

    /**
     * 外部调用，回调rightLayout的点击监听
     */
    public void performRightClick() {
        rightLayout.performClick();
    }

    /**
     * 外部调用,右边第二个图片的点击监听  还没测试好不好用
     */
    public void performRightClick2() {
        rightImage2.performClick();

    }

}
