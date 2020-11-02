package com.power.home.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.power.home.App;
import com.power.home.R;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.presenter.BasePresenter;
import com.power.home.ui.activity.BaseActivity;
import com.power.home.ui.activity.CoursePlayerActivity;
import com.power.home.ui.activity.LoginActivity;
import com.power.home.ui.widget.BaseView;
import com.power.home.ui.widget.MyGestureListener;
import com.power.home.ui.widget.TouchInterceptLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ZHP on 2017/6/24.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {


    private Unbinder mUnbinder;

    protected App mApplication;

    private FrameLayout mRootView;

    private FrameLayout mViewContent;

    private LottieAnimationView loading;

    private TouchInterceptLayout mProgress;
    private boolean showLoading = true;

    private GestureDetector gestureDetector;


    @Inject
    protected
    T mPresenter;
    private BaseActivity.MyOnTouchListener myOnTouchListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = (FrameLayout) inflater.inflate(R.layout.base_progress, container, false);
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mProgress = (TouchInterceptLayout) mRootView.findViewById(R.id.progress);
        loading = mRootView.findViewById(R.id.loading);
        loading.setImageAssetsFolder("images");
        loading.setAnimation("progress.json");
        loading.loop(true);
        BaseActivity activity = (BaseActivity) getActivity();
        gestureDetector = new GestureDetector(activity, new MyGestureListener());
        myOnTouchListener = new BaseActivity.MyOnTouchListener() {
            @Override
            public boolean onTouch(MotionEvent ev) {
                if (activity.getClass().getSimpleName().equals(CoursePlayerActivity.class.getSimpleName())) {
                    return true;
                } else {
                    gestureDetector.onTouchEvent(ev);
                    return false;
                }
            }
        };
        assert activity != null;
        activity.registerMyOnTouchListener(myOnTouchListener);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (App) getActivity().getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());
        setRealContentView();
        getExtras();
        init();
        setListener();
    }


    private void setRealContentView() {

        View realContentView = LayoutInflater.from(getActivity()).inflate(setLayout(), mViewContent, true);
        mUnbinder = ButterKnife.bind(this, realContentView);
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.register(this);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        /*
        当Fragment超过3个时，包括3个，这种情况下使用Butterknife注解有时候会出现空指针。
        原因如下：在onCreateView里面进行绑定后，如果连续滑动，ViewPager会移除Fragment
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }*/
        BaseActivity activity = (BaseActivity) getActivity();
        assert activity != null;
        activity.unregisterMyOnTouchListener(myOnTouchListener);
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.unregister(this);
        }
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {

        if (showLoading) {
            mProgress.setVisibility(View.VISIBLE);
            loading.playAnimation();
        }

    }

    public void showLoading(boolean showLoading) {
        this.showLoading = showLoading;
        if (showLoading) {
            mProgress.setVisibility(View.VISIBLE);
            mViewContent.setEnabled(false);
            loading.playAnimation();
        } else {
            mProgress.setVisibility(View.GONE);
            mViewContent.setEnabled(true);
            loading.cancelAnimation();
        }
    }

    @Override
    public void showError(String msg) {
        loading.cancelAnimation();
        mProgress.setVisibility(View.GONE);
        UIUtil.showToastSafe(msg);
    }

    @Override
    public void dismissLoading() {
        loading.cancelAnimation();
        mProgress.setVisibility(View.GONE);
    }

    public abstract int setLayout();

    protected abstract void getExtras();

    public abstract void init();

    protected abstract void setListener();


    public abstract void setupAcitivtyComponent(AppComponent appComponent);

    public void paddingTop(View view) {
        if (null != view && null != getActivity()) {
            StatusBarUtil.setBarPaddingTop(getActivity(), view);
            StatusBarUtil.setLightStatusBar(getActivity(), true);
        }
    }

    public void goLogin() {
        Intent intent = new Intent(UIUtil.getContext(), LoginActivity.class);
        startActivity(intent);
    }

}
