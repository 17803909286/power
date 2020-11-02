package com.power.home.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.power.home.R;
import com.youth.banner.loader.ImageLoader;

import java.util.List;




public class BannerImageLoader extends ImageLoader {
    private Context context;
    private List<ColorInfo> colorList;
    private Palette palette;

    public BannerImageLoader(List<ColorInfo> colorList) {
        this.colorList = colorList;
    }

    @Override
    public void displayImage(Context context, final Object imgObj, ImageView imageView) {
        this.context = context;
        if (imgObj != null) {
            imageView.setPadding(DensityUtil.dip2px(context, 16), 0, DensityUtil.dip2px(context, 16), 0);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.icon_place_holder_343_114)
                    .error(R.drawable.icon_place_holder_343_114)
                    .fallback(R.drawable.icon_place_holder_343_114);
            Glide.with(context).asBitmap().load(imgObj.toString()).listener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    setColorList(resource, imgObj.toString());
                    return false;
                }
            }).apply(requestOptions).into(imageView);
        }
    }


    private void setColorList(Bitmap bitmap, String imgUrl) {
        if (colorList == null) {
            return;
        }
        palette = Palette.from(bitmap).generate();
        for (int i = 0; i < colorList.size(); i++) {
            if (colorList.get(i).getImgUrl().equals(imgUrl)) {// imgUrl作为识别标志
                if (palette.getVibrantSwatch() != null) {
                    colorList.get(i).setVibrantColor(palette.getVibrantSwatch().getRgb());
                }
                if (palette.getDarkVibrantSwatch() != null) {
                    colorList.get(i).setVibrantDarkColor(palette.getDarkVibrantSwatch().getRgb());
                }
                if (palette.getLightVibrantSwatch() != null) {
                    colorList.get(i).setVibrantLightColor(palette.getLightVibrantSwatch().getRgb());
                }
                if (palette.getMutedSwatch() != null) {
                    colorList.get(i).setMutedColor(palette.getMutedSwatch().getRgb());
                }
                if (palette.getDarkMutedSwatch() != null) {
                    colorList.get(i).setMutedDarkColor(palette.getDarkMutedSwatch().getRgb());
                }
                if (palette.getLightVibrantSwatch() != null) {
                    colorList.get(i).setMutedLightColor(palette.getLightVibrantSwatch().getRgb());
                }
            }
        }
    }




    public int getVibrantColor(int position) {
        return colorList.get(position).getVibrantColor();
    }

    public int getVibrantDarkColor(int position) {
        return colorList.get(position).getVibrantDarkColor();
    }

    public int getVibrantLightColor(int position) {
        return colorList.get(position).getVibrantLightColor();
    }

    public int getMutedColor(int position) {
        return colorList.get(position).getMutedColor();
    }

    public int getMutedDarkColor(int position) {
        return colorList.get(position).getMutedDarkColor();
    }

    public int getMutedLightColor(int position) {
        return colorList.get(position).getMutedLightColor();
    }
}
