package com.power.home.common.util;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.power.home.App;
import com.power.home.di.component.DaggerLogComponent;
import com.power.home.di.module.LogModule;
import com.power.home.presenter.LogPresenter;
import com.power.home.presenter.contract.LogContract;
import com.power.home.ui.activity.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

/**
 * @description  用于捕获系统异常
 *  	权限：<uses-permission android:name = "android.permission.GET_TASKS"/>
 *  		<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
			<uses-permission android:name="android.permission.READER_EXTERNAL_STORAGE" />
 * @auther jiaBF
 * @update 2014-4-24
 * @version V1.0
 */
public class AppCrashHandlerUtil implements Thread.UncaughtExceptionHandler,LogContract.LogView{
    public static final String TAG = "CrashHandler";
    public String path = "/CrashHandler";
    public String fileName = "exception.txt";
    private static AppCrashHandlerUtil INSTANCE = new AppCrashHandlerUtil();
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context ctx;

    @Inject
    LogPresenter logPresenter;
    
    private AppCrashHandlerUtil() {
        DaggerLogComponent.builder()
                .appComponent(App.getApplication().getAppComponent())
                .logModule(new LogModule(this))
                .build()
                .inject(this);
    }

    public static AppCrashHandlerUtil getInstance() {
        return INSTANCE;
    }

    public void init(Context ctx) {
    	this.ctx = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
//        	restart();
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }


    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }


        FileOutputStream foos = null;
        try {
            StringBuilder sb = new StringBuilder();
            String date = DateUtil.currentFormatDate(DateUtil.DATE_TO_STRING_DETAIAL_PATTERN3);
            sb.append("--------date:" +date);
            printStackTrace(sb, ex);
            if(App.LogCrashFile){
                File fileDir = new File(Environment.getExternalStorageDirectory() +"/"+ctx.getPackageName()+ path);
                if (!fileDir.exists())
                    fileDir.mkdirs();
                File exceptionFile = new File(fileDir, date + fileName);
                if (!exceptionFile.exists()) {
                    try {
                        exceptionFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                foos = new FileOutputStream(exceptionFile, true);
                foos.write(sb.toString().getBytes());
                foos.flush();
            }

            logPresenter.addLog(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != foos){
                    foos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void printStackTrace(StringBuilder sb, Throwable ex) {
        if (sb == null)
            sb = new StringBuilder();
        StackTraceElement[] trace = ex.getStackTrace();
        synchronized (sb) {
            sb.append(ex.toString() + "--------");
            for (int i = 0; i < trace.length; i++) {
                sb.append("at" + trace[i] + "--------");
            }
            Throwable ourCause = ex.getCause();
            if (ourCause != null)
                printStackTraceAsCause(sb, ourCause, trace);
        }
    }

    private void printStackTraceAsCause(StringBuilder sb, Throwable ex,
                                        StackTraceElement[] causedTrace) {
        // assert Thread.holdsLock(s);
        // Compute number of frames in common between this and caused
        if (sb == null)
            sb = new StringBuilder();
        StackTraceElement[] trace = ex.getStackTrace();
        int m = trace.length - 1, n = causedTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
            m--;
            n--;
        }
        int framesInCommon = trace.length - 1 - m;
        sb.append("Caused by: ");
        sb.append(ex + "--------");
        for (int i = 0; i <= m; i++) {
            sb.append("at" + trace[i] + "--------");
        }
        if (framesInCommon != 0) {
            sb.append(" ..." + framesInCommon + " more --------");
        }

        Throwable ourCause = ex.getCause();
        if (ourCause != null)
            printStackTraceAsCause(sb, ourCause, trace);
    }
    

	private void restart()
	{
        Intent intent = new Intent();
        intent.setClassName(ctx.getApplicationContext(), MainActivity.class.getName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent restartIntent = PendingIntent.getActivity(
       		 ctx.getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager mgr = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                restartIntent);
	}



    public static String getActivityName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String activityName = am.getRunningTasks(1).get(0).topActivity.getClassName();
        return activityName;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void logSuccess() {

    }
}
