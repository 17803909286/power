package com.power.home.ui.widget.floatview;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.core.view.ViewCompat;

import com.power.home.R;
import com.power.home.ui.widget.floatview.utils.EnContext;

import java.lang.ref.WeakReference;

/**
 * Created by ZHP on 2020/5/15 0015.悬浮窗管理器
 */
public class FloatingView implements IFloatingView {

    private FloatingMagnetView mEnFloatingView;
    private static volatile FloatingView mInstance;
    private WeakReference<FrameLayout> mContainer;
    @LayoutRes
    private int mLayoutId = R.layout.media_float_view;

    private String url, title, time;

    private ViewGroup.LayoutParams mLayoutParams = getParams();

    private FloatingView() {
    }

    public static FloatingView get() {
        if (mInstance == null) {
            synchronized (FloatingView.class) {
                if (mInstance == null) {
                    mInstance = new FloatingView();
                }
            }
        }
        return mInstance;
    }

    @Override
    public FloatingView remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mEnFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(mEnFloatingView) && getContainer() != null) {
                    getContainer().removeView(mEnFloatingView);
                }
                mEnFloatingView = null;
            }
        });
        return this;
    }

    private void ensureFloatingView() {
        synchronized (this) {
            if (mEnFloatingView != null) {
                return;
            }
            EnFloatingView enFloatingView = new EnFloatingView(EnContext.get(), mLayoutId);
            mEnFloatingView = enFloatingView;
            enFloatingView.setLayoutParams(mLayoutParams);
            enFloatingView.setPic(url);
            enFloatingView.setTitle(title);
            enFloatingView.setTime(time);
            addViewToWindow(enFloatingView);
        }
    }

    @Override
    public FloatingView add() {
        ensureFloatingView();
        return this;
    }

    @Override
    public FloatingView attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    @Override
    public FloatingView attach(FrameLayout container) {
        if (container == null || mEnFloatingView == null) {
            mContainer = new WeakReference<>(container);
            return this;
        }
        if (mEnFloatingView.getParent() == container) {
            return this;
        }
        if (getContainer() != null && mEnFloatingView.getParent() == getContainer()) {
            getContainer().removeView(mEnFloatingView);
        }
        mContainer = new WeakReference<>(container);
        container.addView(mEnFloatingView);
        return this;
    }

    @Override
    public FloatingView detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override
    public FloatingView detach(FrameLayout container) {
        if (mEnFloatingView != null && container != null && ViewCompat.isAttachedToWindow(mEnFloatingView)) {
            container.removeView(mEnFloatingView);
        }
        if (getContainer() == container) {
            mContainer = null;
        }
        return this;
    }

    @Override
    public FloatingMagnetView getView() {
        return mEnFloatingView;
    }

    /**
     * 设置专辑图片
     *
     * @param url
     * @return
     */
    @Override
    public FloatingView setPic(String url) {
        this.url = url;
        ensureFloatingView();
        return this;
    }

    /**
     * 设置播放按钮是否
     *
     * @param resId
     * @return
     */
    @Override
    public FloatingView playPause(int resId) {
        return this;
    }

    /**
     * 设置课程标题
     *
     * @param title
     * @return
     */
    @Override
    public FloatingView setTitle(String title) {
        this.title = title;
        ensureFloatingView();
        return this;
    }

    /**
     * 设置播放进度xx:xx
     *
     * @param time
     * @return
     */
    @Override
    public FloatingView setTime(String time) {
        this.time = time;
        ensureFloatingView();
        return this;
    }

    @Override
    public FloatingView customView(FloatingMagnetView viewGroup) {
        mEnFloatingView = viewGroup;
        return this;
    }

    @Override
    public FloatingView customView(@LayoutRes int resource) {
        mLayoutId = resource;
        return this;
    }

    @Override
    public FloatingView layoutParams(ViewGroup.LayoutParams params) {
        mLayoutParams = params;
        if (mEnFloatingView != null) {
            mEnFloatingView.setLayoutParams(params);
        }
        return this;
    }

    @Override
    public FloatingView listener(MagnetViewListener magnetViewListener) {
        if (mEnFloatingView != null) {
            mEnFloatingView.setMagnetViewListener(magnetViewListener);
        }
        return this;
    }

    private void addViewToWindow(final View view) {
        if (getContainer() == null) {
            return;
        }
        getContainer().addView(view);
    }

    private FrameLayout getContainer() {
        if (mContainer == null) {
            return null;
        }
        return mContainer.get();
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.BOTTOM;
//        params.gravity = Gravity.BOTTOM | Gravity.START;
//        params.setMargins(13, params.topMargin, params.rightMargin, 500);
        return params;
    }

    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}