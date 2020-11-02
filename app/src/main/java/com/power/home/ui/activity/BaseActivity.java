package com.power.home.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.power.home.App;
import com.power.home.R;
import com.power.home.common.bus.BindEventBus;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.StatusBarUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.di.component.AppComponent;
import com.power.home.presenter.BasePresenter;
import com.power.home.ui.widget.BaseView;
import com.power.home.ui.widget.MyGestureListener;
import com.power.home.ui.widget.MyTitleBar;
import com.power.home.ui.widget.TouchInterceptLayout;
import com.power.home.ui.widget.floatview.FloatingView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by ZHP on 2017/6/24.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {


    private Unbinder mUnbinder;

    protected App mApplication;

    private FrameLayout mRootView;

    private FrameLayout mViewContent;

    private TouchInterceptLayout mProgress;

    private LottieAnimationView loading;

    private boolean showLoading = true;
    protected boolean onResume = true;
    private int isAudio = 1;//1音频 0视频
    public static final int PLUGIN_VALID = 0;
    public static final int PLUGIN_NOT_INSTALLED = -1;
    public static final int PLUGIN_NEED_UPGRADE = 2;
    @Inject
    public T mPresenter;
    private GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutView());
        this.mApplication = (App) getApplication();
        mUnbinder = ButterKnife.bind(this);
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.register(this);
        }
        setupAcitivtyComponent(mApplication.getAppComponent());
        //状态栏透明
//        StatusBarUtil.transparencyBar2(this);
//        StatusBarUtil.setLightStatusBar(this, true);
        getExtras();
        if (init()) {
            StatusBarUtil.transparencyBar2(this);
            //StatusBarUtil.setLightStatusBar(this, true);
            StatusBarUtil.setStatusTextColor(false, this);
        } else {
            StatusBarUtil.setBarDarkMode(this, true);
        }
        setListener();

        gestureDetector = new GestureDetector(this, new MyGestureListener());
    }

    private View setLayoutView() {

        mRootView = (FrameLayout) View.inflate(this, R.layout.base_progress, null);
        mViewContent = (FrameLayout) mRootView.findViewById(R.id.view_content);
        mProgress = (TouchInterceptLayout) mRootView.findViewById(R.id.progress);
        loading = mRootView.findViewById(R.id.loading);
        loading.setImageAssetsFolder("images");
        loading.setAnimation("progress.json");
        loading.loop(true);
        setRealContentView();
        return mRootView;
    }

    private void setRealContentView() {

        View realContentView = LayoutInflater.from(this).inflate(setLayout(), mViewContent, true);
        mUnbinder = ButterKnife.bind(this, realContentView);
    }

    /**
     * 申请权限
     *
     * @param consumer 权限结果回调
     */
    protected void requestAllPermission(Consumer<Boolean> consumer) {
        // 检查权限
        RxPermissions.getInstance(this)
                .request(CAMERA,
                        READ_PHONE_STATE,
                        WRITE_EXTERNAL_STORAGE)
                .subscribe(consumer);
    }


    /**
     * 申请SD卡写入权限
     *
     * @param consumer 权限结果回调
     */
    public void requestPhonePermissionStatic(Consumer<Boolean> consumer) {
        // 检查权限
        RxPermissions.getInstance(this)
                .request(READ_PHONE_STATE)
                .subscribe(consumer);

    }

    /**
     * 申请SD卡写入权限
     *
     * @param consumer 权限结果回调
     */
    public void requestSdWritePermissionStatic(Consumer<Boolean> consumer) {
        // 检查权限
        RxPermissions.getInstance(this)
                .request(WRITE_EXTERNAL_STORAGE)
                .subscribe(consumer);

    }

    /**
     * 申请SD卡写入权限
     *
     * @param consumer 权限结果回调
     */
    public void requestCAMERAPermissionStatic(Consumer<Boolean> consumer) {
        // 检查权限
        RxPermissions.getInstance(this)
                .request(CAMERA)
                .subscribe(consumer);

    }


    /**
     * write_setting权限申请，必须这么写
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        if (!Settings.System.canWrite(this)) {
            UIUtil.showToastSafe(getString(R.string.permission_tip));
            Uri selfPackageUri = Uri.parse("package:"
                    + getPackageName());
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                    selfPackageUri);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBusUtils.unregister(this);
        }
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    public abstract int setLayout();

    public abstract void getExtras();

    /**
     * true    -->沉浸状态,深色模式
     * false   -->非沉浸状态,深色模式
     */
    public abstract boolean init();

    public abstract void setListener();

    @Override
    public void showLoading() {
        if (showLoading) {
            mProgress.setVisibility(View.VISIBLE);
            mViewContent.setEnabled(false);
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
            loading.cancelAnimation();
            mProgress.setVisibility(View.GONE);
            mViewContent.setEnabled(true);
        }
    }

    public View getEmptyView(int id, String tip) {
        View view = RelativeLayout.inflate(this, R.layout.empty_layout, null);
        TextView textView = view.findViewById(R.id.tv_dataEmpty);
        textView.setText(tip);
        ImageView imageView = view.findViewById(R.id.img_dataEmpty);
        imageView.setImageDrawable(getDrawable(id));
        return view;
    }

    @Override
    public void showError(String msg) {
        loading.cancelAnimation();
        mProgress.setVisibility(View.GONE);
        mViewContent.setEnabled(true);
        UIUtil.showToastSafe(msg);
    }

    @Override
    public void dismissLoading() {
        loading.cancelAnimation();
        mProgress.setVisibility(View.GONE);
        mViewContent.setEnabled(true);
    }

    public abstract void setupAcitivtyComponent(AppComponent appComponent);

    public void paddingTop(View view) {
        if (null != view) {
            StatusBarUtil.setBarPaddingTop(this, view);
        }
    }

    public void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void backListener(MyTitleBar titleBar) {
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //手势使用=======================================================================================
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (getShowActivity()) {
            gestureDetector.onTouchEvent(event);
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }

    public interface MyOnTouchListener {
        public boolean onTouch(MotionEvent ev);
    }

    private ArrayList<MyOnTouchListener> onTouchListeners = new ArrayList<MyOnTouchListener>(10);

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyOnTouchListener listener : onTouchListeners) {
            if (listener != null) {
                listener.onTouch(ev);
            }
        }
        if (getShowActivity()) {
            gestureDetector.onTouchEvent(ev); //让GestureDetector响应触碰事件
        }
        super.dispatchTouchEvent(ev); //让Activity响应触碰事件
        return false;
//        return super.dispatchTouchEvent(ev);
    }

    private boolean getShowActivity() {
        if (getClass().getSimpleName().equals(GuideActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(SplashActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(WebViewActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(LoginActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(VerificationCodeLoginActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(BindPhoneActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(BindPhoneTwoActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(CoursePlayerActivity.class.getSimpleName())||
                getClass().getSimpleName().equals(ShareActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(InviteFriendActivity.class.getSimpleName()) ||
                getClass().getSimpleName().equals(CourseDetailsActivity.class.getSimpleName())) {
            return false;
        } else {
            return true;
        }
    }

    public void registerMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.add(myOnTouchListener);
    }

    public void unregisterMyOnTouchListener(MyOnTouchListener myOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener);
    }
}
