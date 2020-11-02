package com.power.home;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.liulishuo.filedownloader.FileDownloader;
import com.power.home.common.Constant;
import com.power.home.common.MyActivityManager;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.ACache;
import com.power.home.common.util.AppCrashHandlerUtil;
import com.power.home.common.util.FloatMusicUtil;
import com.power.home.common.util.LocaleUtils;
import com.power.home.common.util.LogUtil;
import com.power.home.common.util.MusicList;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.Music;
import com.power.home.di.component.AppComponent;
import com.power.home.di.component.DaggerAppComponent;
import com.power.home.di.module.AppModule;
import com.power.home.di.module.HttpModule;
import com.power.home.interfaces.ForegroundCallbacks;
import com.power.home.jpush.JpushUtil;
import com.power.home.ui.activity.BindPhoneActivity;
import com.power.home.ui.activity.BindPhoneTwoActivity;
import com.power.home.ui.activity.CourseDetailsActivity;
import com.power.home.ui.activity.CoursePlayerActivity;
import com.power.home.ui.activity.GuideActivity;
import com.power.home.ui.activity.InviteFriendActivity;
import com.power.home.ui.activity.LoginActivity;
import com.power.home.ui.activity.ShareActivity;
import com.power.home.ui.activity.SplashActivity;
import com.power.home.ui.activity.VerificationCodeLoginActivity;
import com.power.home.ui.activity.WebViewActivity;
import com.power.home.ui.service.MediaService;
import com.power.home.ui.widget.floatview.EnFloatingView;
import com.power.home.ui.widget.floatview.FloatingView;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Locale;

/**
 * Created by zhangpeng on 2017/11/20.
 */

public class App extends MultiDexApplication {
    public static final int FORMAL_ENVIRONMENT = Constant.IS_RELEASE ? 3 : 1;
    public static boolean LogFlag;
    public static boolean LogCrashFile;

    private static App mContext;

    public static Activity mForegroundActivity;

    private static Handler mMainHandler;

    private static Thread mMainThread;

    private static int mMainThreadId;

    private static Looper mMainThreadLooper;

    private AppComponent mAppComponent;
    private IWXAPI api;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)

    @Override
    public void onCreate() {
        super.onCreate();
        FileDownloader.setupOnApplicationOnCreate(this);
        MultiDex.install(this);
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
        switch (FORMAL_ENVIRONMENT) {
            case 1:
            case 2:
                LogFlag = true;
                break;
            case 3:
                LogFlag = true;
                break;
        }

        mContext = (App) getApplicationContext();
        mMainHandler = new Handler();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
        mMainThreadLooper = getMainLooper();
        JpushUtil.init();
        Utils.init(this);
        ARouter.init(this);
        App.LogCrashFile = true;
        AppCrashHandlerUtil.getInstance().init(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        initAppStatusListener();
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, true);
        api.registerApp(Constant.APP_ID);
        JpushUtil.init();
        initFloatView();
        getStatus();
    }

    private void initFloatView() {
        EventBusUtils.register(this);
        FloatingView.get().add();
        if (StringUtil.isNotNullString(SharePreferencesUtils.getFloatStatus())) {
            FloatingView.get().getView().setVisibility(View.VISIBLE);
            LogUtil.e("FloatingView", "VISIBLE======init===========================");
            Music music = (Music) ACache.get(UIUtil.getContext()).getAsObject(Constant.floatStatus);
            if (null != music) {
                initAudioService(music);
                ((EnFloatingView) FloatingView.get().getView()).setMusic(music);
                ((EnFloatingView) FloatingView.get().getView()).setShowPlay(true);
            } else {
                FloatingView.get().getView().setVisibility(View.GONE);
            }
        } else {
            FloatingView.get().getView().setVisibility(View.GONE);
        }
    }

    private void getStatus() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                FloatingView.get().attach(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
                if (StringUtil.isNotNullString(SharePreferencesUtils.getFloatStatus())) {
                    if (getShowActivity(activity)) {
                        if (null != FloatingView.get().getView()) {
                            FloatingView.get().getView().setVisibility(View.VISIBLE);
                        }
                        LogUtil.e("FloatingView", "VISIBLE======resumed===========================");
                    } else {
                        if (null != FloatingView.get().getView()) {
                            FloatingView.get().getView().setVisibility(View.GONE);
                        }
                        LogUtil.e("FloatingView", "GONE======resumed===========================");
                    }
                } else {
                    if (null != FloatingView.get().getView()) {
                        FloatingView.get().getView().setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
                LogUtil.e("FloatingView", activity.getClass().getName() + "======Paused===========================");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                FloatingView.get().detach(activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initAppStatusListener() {
        ForegroundCallbacks.init(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {
                LogUtil.d("BACK", "应用回到前台调用重连方法");
            }

            @Override
            public void onBecameBackground() {

            }
        });
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale _UserLocale = LocaleUtils.getUserLocale(this);
        if (_UserLocale != null) {
            Locale.setDefault(_UserLocale);
            Configuration _Configuration = new Configuration(newConfig);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                _Configuration.setLocale(_UserLocale);
            } else {
                _Configuration.locale = _UserLocale;
            }
            getResources().updateConfiguration(_Configuration, getResources().getDisplayMetrics());
        }
    }


    public static App getApplication() {
        return mContext;
    }


    public static Handler getMainThreadHandler() {
        return mMainHandler;
    }


    public static Thread getMainThread() {
        return mMainThread;
    }


    public static int getMainThreadId() {
        return mMainThreadId;
    }


    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }


    public static Activity getmForegroundActivity() {
        return mForegroundActivity;
    }

    public static void setmForegroundActivity(Activity mForegroundActivity) {
        App.mForegroundActivity = mForegroundActivity;
    }

    private String getMetaDataValue(String key) {
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(
                    getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getApplicationContext().getSystemService(
                        Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                return new MaterialHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onTerminate() {
        EventBusUtils.unregister(this);
        super.onTerminate();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {
            switch (date.getCode()) {
                case EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_SHOW_PLAY:
                    Music musicBeanPlay = (Music) date.getData();
                    ((EnFloatingView) FloatingView.get().getView()).setMusic(musicBeanPlay);
                    ((EnFloatingView) FloatingView.get().getView()).setShowPlay(true);
                    break;
                case EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_SHOW_PAUSE:
                    Music musicBeanPause = (Music) date.getData();
                    ((EnFloatingView) FloatingView.get().getView()).setMusic(musicBeanPause);
                    ((EnFloatingView) FloatingView.get().getView()).setShowPlay(false);
                    break;
                case EventBusUtils.EventCode.APP_OPEN_FLOAT:
                    Music musicBean = (Music) date.getData();
                    ((EnFloatingView) FloatingView.get().getView()).setMusic(musicBean);
                    ((EnFloatingView) FloatingView.get().getView()).setShowPlay(!musicBean.isAudio() || !musicBean.isPlaying());
                    break;
                case EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_SECOND:
                    Music music = (Music) date.getData();
                    FloatMusicUtil.saveStatus(music);
                    ((EnFloatingView) FloatingView.get().getView()).setMusic(music);
                    break;
                case EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_PLAY_FINISH:
                    int position = (int) date.getData();
                    Music musicNext = MusicList.getNextMusic(position);
                    EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.VIDEO_COMPLETE, ""));
                    if (null != musicNext) {
                        FloatMusicUtil.saveStatus(musicNext);
                    } else {
                        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE, EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE));
                        Music nextVideo = MusicList.getNextVideo(position);
                        FloatMusicUtil.saveStatus(nextVideo);
                        ((EnFloatingView) FloatingView.get().getView()).playFinish();
                        ((EnFloatingView) FloatingView.get().getView()).setNextVideo(nextVideo);
                    }
                    break;

            }
        }
    }

    private void initAudioService(Music music) {
        Intent MediaServiceIntent = new Intent(UIUtil.getContext(), MediaService.class);
        MediaServiceIntent.putExtra(Constant.service_data, music);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(MediaServiceIntent);
        } else {
            startService(MediaServiceIntent);
        }
    }

    private boolean getShowActivity(Activity activity) {
        return !activity.getClass().getSimpleName().equals(GuideActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(SplashActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(WebViewActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(LoginActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(VerificationCodeLoginActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(BindPhoneActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(BindPhoneTwoActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(CoursePlayerActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(ShareActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(InviteFriendActivity.class.getSimpleName()) &&
                !activity.getClass().getSimpleName().equals(CourseDetailsActivity.class.getSimpleName());
    }
}
