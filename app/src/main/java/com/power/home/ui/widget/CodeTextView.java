package com.power.home.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.power.home.R;

/**
 * Created by ZHP on 2018/12/26/0026.
 */

@SuppressLint("AppCompatCustomView")
public class CodeTextView extends TextView {

    private static final String TAG = "codeTextView";

    private int width;//设置高

    private int height;//设置高

    private Paint mPathPaint;


    public CodeTextView(Context context) {

        this(context, null);

    }


    public CodeTextView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);

    }


    public CodeTextView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);


        //初始化绘制路径的画笔

        mPathPaint = new Paint();

        mPathPaint.setAntiAlias(true);

        mPathPaint.setColor(getResources().getColor(R.color.colorBlack999999));

        mPathPaint.setStyle(Paint.Style.STROKE);

    }


    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);

        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高

    }


    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        canvas.drawRect(0, 0, width, height, mPathPaint);

    }

}
