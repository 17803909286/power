package com.power.home.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.power.home.common.util.UIUtil;

import java.security.MessageDigest;

/**
 * Created by XWL on 2020/6/3.
 * Description:
 */
public  class GlideRoundTransform extends CenterCrop {

    private float radius = 10f;

    public GlideRoundTransform(Context context) {
        this( 10);
    }

    public GlideRoundTransform( int dp) {
        this.radius = UIUtil.dip2px( dp);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        //glide4.0+
        Bitmap transform = super.transform(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, transform);
        //glide3.0
        //return roundCrop(pool, toTransform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
