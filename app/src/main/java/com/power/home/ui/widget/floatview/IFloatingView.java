package com.power.home.ui.widget.floatview;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;

/**
 * Created by ZHP on 2020/5/15 0015.
 */

public interface IFloatingView {

    FloatingView remove();

    FloatingView add();

    FloatingView attach(Activity activity);

    FloatingView attach(FrameLayout container);

    FloatingView detach(Activity activity);

    FloatingView detach(FrameLayout container);

    FloatingMagnetView getView();

    FloatingView setPic(String url);

    FloatingView playPause(@DrawableRes int resId);

    FloatingView setTitle(String title);

    FloatingView setTime(String time);

    FloatingView customView(FloatingMagnetView viewGroup);

    FloatingView customView(@LayoutRes int resource);

    FloatingView layoutParams(ViewGroup.LayoutParams params);

    FloatingView listener(MagnetViewListener magnetViewListener);

}
