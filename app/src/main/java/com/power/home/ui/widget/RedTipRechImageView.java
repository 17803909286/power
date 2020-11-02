package com.power.home.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.power.home.R;
import com.power.home.common.util.UIUtil;


/**
 * Created by zhp on 2019/5/27
 */
public class RedTipRechImageView extends androidx.appcompat.widget.AppCompatImageView {

    public static final int RED_TIP_INVISIBLE = 0;
    public static final int RED_TIP_VISIBLE = 1;
    public static final int RED_TIP_GONE = 2;
    private int tipVisibility = 0;

    public RedTipRechImageView(Context context) {
        super(context);
        init(null);
    }

    public RedTipRechImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RedTipRechImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.RedTipTextView);
            tipVisibility = array.getInt(R.styleable.RedTipTextView_redTipsVisibility, 0);
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tipVisibility == 1) {
            int width = getWidth();
            int paddingRight = UIUtil.dip2px(5);
            Paint paint = new Paint();

            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(width * 4 / 5, paddingRight, paddingRight / 5 * 4, paint);
            paint.reset();

            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(width * 4 / 5, paddingRight, paddingRight / 4 * 3, paint);
        }
    }

    public void setVisibility(int visibility) {
        tipVisibility = visibility;
        invalidate();
    }
}
