package com.power.home.common.util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import androidx.annotation.Dimension;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.power.home.App;
import com.power.home.common.Constant;
import com.power.home.ui.widget.ToastView;

import java.util.List;

/**
 * Created by ZHP on 2017/6/24.
 */
public class UIUtil {

    public static Context getContext() {
        return App.getApplication();
    }

    public static Thread getMainThread() {
        return App.getMainThread();
    }

    private static long getMainThreadId() {
        return App.getMainThreadId();
    }

    public static int dip2px(String value) {
        if (value.contains("dip")) {
            String dip = value.replace("dip", "");
            float dp = Float.parseFloat(dip);
            return dip2px((int) dp);
        }
        return -1;
    }


    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }


    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    private static Handler getHandler() {
        return App.getMainThreadHandler();
    }


    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }


    private static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }


    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }


    public static Resources getResources() {
        return getContext().getResources();
    }


    public static String getString(int resId) {
        if (App.mForegroundActivity == null) {
            return getContext().getResources().getString(resId);
        } else {
            return App.mForegroundActivity.getResources().getString(resId);
        }
    }


    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }


    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(getContext(), resId);
    }


    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }


    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    private static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    public static void startActivity(Intent intent) {

        Activity activity = App.getmForegroundActivity();
        if (activity != null) {
            activity.startActivity(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }


    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }


    public static void showToastSafe(final String str) {
        if (isRunInMainThread()) {
            showToast(str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str);
                }
            });
        }
    }


    public static void showLongToastSafe(final String str) {
        if (isRunInMainThread()) {
            showLongToast(str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showLongToast(str);
                }
            });
        }
    }

    private static void showToast(String str) {
        if (App.getApplication() != null) {
            ToastView toastView = new ToastView();
            toastView.showCenter(str, UIUtil.getContext());
        }
    }

    private static void showLongToast(String str) {
        if (App.getApplication() != null) {
            ToastView toastView = new ToastView();
            toastView.showCenter(str, UIUtil.getContext());
        }
    }


    public static int getWidth(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredWidth();
    }


    public static int getHeight(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredHeight();
    }

    public static String getVersionName() {

        String versionName = null;
        try {
            PackageManager packageManager = getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (Exception e) {

        }

        return versionName;
    }

    public static int getVersionCode() {

        int versionCode = 0;
        try {
            PackageManager packageManager = getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception e) {

        }

        return versionCode;
    }


    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    public static boolean checkPermission() {

        String permission = ACache.get(UIUtil.getContext()).getAsString(Constant.permission);
        if (StringUtil.isNotNullString(permission) && permission.equals(Constant.permission)) {
            UIUtil.showToastSafe("请先在设置中获取权限");
            return false;
        } else {
            return true;
        }
    }

    public static String getPhone(String phone) {

        return StringUtil.isNotNullString(phone) ?
                phone.substring(0, 3) + "****" + phone.substring(7) :
                "";
    }


    public static int screenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.widthPixels;
        }
        return 1080;
    }


    public static void setEditTextHintWithSize(EditText editText, String hintText, @Dimension int size) {
        if (!TextUtils.isEmpty(hintText)) {
            SpannableString ss = new SpannableString(hintText);
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            editText.setHint(new SpannedString(ss));
        }
    }


    public static boolean isProessRunning(Context context, String proessName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();

        for (ActivityManager.RunningAppProcessInfo info : lists) {
            if (info.processName.contains(proessName)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isServiceRunning(Context context, String serviceName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> lists = am.getRunningServices(30);

        for (ActivityManager.RunningServiceInfo info : lists) {
            if (info.service.getClassName().contains(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static void goCall(String phone, Activity activity) {
        if (StringUtil.isNullString(phone)) {
            return;
        }
        if (ContextCompat.checkSelfPermission(UIUtil.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + phone);
            intent.setData(data);
            startActivity(intent);
        }
    }

    public static void goSMS(String phone, Activity activity) {
        if (StringUtil.isNullString(phone)) {
            return;
        }
        if (ContextCompat.checkSelfPermission(UIUtil.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
            startActivity(intent);
        }
    }

    public static String getMetaDataValue(String key) {
        try {
            ApplicationInfo info = getContext().getPackageManager().getApplicationInfo(
                    getContext().getPackageName(), PackageManager.GET_META_DATA);
            return info.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static TranslateAnimation getShowAnimation() {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 2.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(300);
        return mShowAction;
    }

    public static TranslateAnimation getHideAnimation() {
        TranslateAnimation mCloseAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 2.0f);
        mCloseAction.setDuration(300);
        return mCloseAction;
    }
}
