package com.power.home.ui.widget;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.power.home.common.util.DensityUtil;

/**
 * Created by XWL on 2020/3/20.
 * Description:
 */
public class ImageMeasure {
    public static void measure(Activity activity, View imageView){
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        // 这里是因为控件有边距，需要处理，没有的可以不用管这里
        int marWidth = DensityUtil.dip2px(activity,16);

        //  int mHeight = (int) ((dm.widthPixels-marWidth*2)/(340f/110f));
        int mHeight = (int) ((dm.widthPixels-marWidth*2)/3.09091);

        // 如果有时候宽度需要计算，也是类似于上面的公式了，就自己试了，毕竟设置宽度的需求较少。
        // int mWidth = dm.widthPixels;

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        // params.width = mWidth;
        params.height = mHeight;
        imageView.setLayoutParams(params);
    }
}
