package com.power.home.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.power.home.R;

/**
 * Created by ZHP on 2018/12/26/0026.
 */

public class PasswordFrameView extends View {

    private static final String TAG = "passwordframeView";

    private int width;//设置高

    private int height;//设置高

    private int radios ;

    private int radiosStart;

    private int radiosZero;

    private boolean isPassword;

    private Paint mPathPaint;

    private Paint mPaintCircle;







    public PasswordFrameView(Context context) {

        this(context,null);

    }



    public PasswordFrameView(Context context, AttributeSet attrs) {

        this(context, attrs,0);

    }



    public PasswordFrameView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);



        //初始化绘制路径的画笔

        mPathPaint = new Paint();

        mPathPaint.setAntiAlias(true);

        mPathPaint.setColor(getResources().getColor(R.color.colorBlack999999));

        mPathPaint.setStyle(Paint.Style.STROKE);



        mPaintCircle = new Paint();

        mPaintCircle.setAntiAlias(true);

        mPaintCircle.setColor(Color.BLACK);

        mPaintCircle.setStyle(Paint.Style.FILL);

    }



    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);

        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高



        radiosZero = 0;

        radiosStart = ((height>width)? height:width)/10;

        radios = radiosZero;

    }



    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (isPassword){

            radios = radiosStart;

        }else {

            radios = radiosZero;

        }

        canvas.drawRect(0,0,width,height,mPathPaint);

        canvas.drawCircle(width/2,height/2,radios,mPaintCircle);

    }



    private void invalidateView(boolean isPassword){

        this.isPassword = isPassword;

        invalidate();

    }



    /**

     * 改变小点的状态

     * @param isPassword

     */

    public void invalidatePassword(boolean isPassword){

        invalidateView(isPassword);

    }
}
