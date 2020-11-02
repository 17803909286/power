package com.power.home.ui.widget;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.power.home.ui.activity.BaseActivity;
import com.power.home.ui.activity.CoursePlayerActivity;

import butterknife.Unbinder;

/**
 * Created by zhp on 2020-03-14
 */
public class BaseLazyFragment extends Fragment {

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean hasLoadedOnceSuccess;
    private View contentView;

    protected Unbinder mUnbinder;

    private GestureDetector gestureDetector;
    private BaseActivity.MyOnTouchListener myOnTouchListener;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = onCreateView(inflater, savedInstanceState);
            isPrepared = true;
            lazyLoad();
        }
        //因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent != null) {
            parent.removeView(contentView);
        }

        BaseActivity activity = (BaseActivity) getActivity();
        gestureDetector = new GestureDetector(activity, new MyGestureListener());
        myOnTouchListener = new BaseActivity.MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                if (getActivity().getClass().getSimpleName().equals(CoursePlayerActivity.class.getSimpleName())) {
                    return true;
                } else {
                    gestureDetector.onTouchEvent(ev);
                    return false;
                }
            }
        };
        assert activity != null;
        activity.registerMyOnTouchListener(myOnTouchListener);
        return contentView;
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    private void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        onLazyLoad();
    }


    protected boolean hasAllowLoad() {
        return isPrepared || isVisible;
    }

    /**
     * 不可见
     */
    protected void onInvisible() {


    }

    public void setHasLoadedSuccess() {
        this.hasLoadedOnceSuccess = true;
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected void onLazyLoad() {
    }

    /**
     * 内容view
     *
     * @author jiaBF
     * @date 2015年8月24日
     */
    protected View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseActivity activity = (BaseActivity) getActivity();
        assert activity != null;
        activity.unregisterMyOnTouchListener(myOnTouchListener);
        if (mUnbinder != Unbinder.EMPTY) {
            if (mUnbinder != null) {
                mUnbinder.unbind();
            }
        }
    }
}
