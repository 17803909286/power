package com.power.home.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.power.home.R;

/**
 * Created by zhp on 2019/9/5
 */
public class CornersWebview extends WebView {


    private float corners = 0;
    private int vWidth;
    private int vHeight;
    private Paint paint1;
    private Paint paint2;

    private int scrollX, scrollY = 0;

    private float[] radiusArray = {0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f};


    public CornersWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CornersWebview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint1 = new Paint();
        paint1.setColor(Color.WHITE);
        paint1.setAntiAlias(true);
        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.cornersWebView); // 读取xml styleable，attrs是xml属性的集合

        corners = a.getDimension(R.styleable.cornersWebView_corners, getResources().getDimensionPixelSize(R.dimen.DIMEN_10DP));

        //设置四个角的圆角半径
        radiusArray[0] = corners;
        radiusArray[1] = corners;
        radiusArray[2] = corners;
        radiusArray[3] = corners;
        radiusArray[4] = corners;
        radiusArray[5] = corners;
        radiusArray[6] = corners;
        radiusArray[7] = corners;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        vWidth = getMeasuredWidth();
        vHeight = getMeasuredHeight();
    }


    @Override
    public void onDraw(Canvas canvas) {
        scrollX = this.getScrollX();
        scrollY = this.getScrollY();
        Path path = new Path();
        RectF rectF = new RectF(scrollX, scrollY, scrollX + vWidth, scrollY + vHeight);
        path.addRoundRect(rectF,radiusArray, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }


}
