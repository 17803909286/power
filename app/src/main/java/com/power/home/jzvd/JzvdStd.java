package com.power.home.jzvd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.power.home.R;
import com.power.home.ui.adapter.AntholigyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nathen
 * On 2016/04/18 16:15
 */
public class JzvdStd extends Jzvd {

    public static long LAST_GET_BATTERYLEVEL_TIME = 0;
    public static int LAST_GET_BATTERYLEVEL_PERCENT = 70;
    protected static Timer DISMISS_CONTROL_VIEW_TIMER;

    public ImageView backButton;
    public ProgressBar bottomProgressBar, loadingProgressBar;
    public TextView titleTextView;
    public ImageView thumbImageView;
    public ImageView tinyBackImageView;
    public LinearLayout batteryTimeLayout;
    public ImageView batteryLevel;
    public TextView videoCurrentTime;
    public TextView replayTextView;
    public TextView clarity;
    public PopupWindow clarityPopWindow;
    public TextView mRetryBtn;
    public LinearLayout mRetryLayout;
    protected DismissControlViewTimerTask mDismissControlViewTimerTask;
    protected Dialog mProgressDialog;
    protected ProgressBar mDialogProgressBar;
    protected TextView mDialogSeekTime;
    protected TextView mDialogTotalTime;
    protected ImageView mDialogIcon;
    protected Dialog mVolumeDialog;
    protected ProgressBar mDialogVolumeProgressBar;
    protected TextView mDialogVolumeTextView;
    protected ImageView mDialogVolumeImageView;
    protected Dialog mBrightnessDialog;
    protected ProgressBar mDialogBrightnessProgressBar;
    protected TextView mDialogBrightnessTextView;
    public TextView tv_speed;
    public ImageView mIvShare;
    private boolean mIsWifi;
    private ArrayList<String> mSpeeds;
    private PopupWindow speedPopWindow;
    private TextView antholigy;
    public RecyclerView recycle_antholigy;
    public AntholigyAdapter antholigyAdapter;
    private LinearLayout antholigyLayout;
    public PopupWindow antholigyPopWindow;


    public JzvdStd(Context context) {
        super(context);
    }

    public JzvdStd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        batteryTimeLayout = findViewById(R.id.battery_time_layout);
        bottomProgressBar = findViewById(R.id.bottom_progress);
        titleTextView = findViewById(R.id.title);
        backButton = findViewById(R.id.back);
        thumbImageView = findViewById(R.id.thumb);
        loadingProgressBar = findViewById(R.id.loading);
        tinyBackImageView = findViewById(R.id.back_tiny);
        batteryLevel = findViewById(R.id.battery_level);
        videoCurrentTime = findViewById(R.id.video_current_time);
        replayTextView = findViewById(R.id.replay_text);
        clarity = findViewById(R.id.clarity);
        mRetryBtn = findViewById(R.id.retry_btn);
        mRetryLayout = findViewById(R.id.retry_layout);
        mIvShare = findViewById(R.id.iv_share);
        tv_speed = findViewById(R.id.tv_speed);
        antholigy = findViewById(R.id.antholigy);

        thumbImageView.setOnClickListener(this);
        backButton.setOnClickListener(this);
        tinyBackImageView.setOnClickListener(this);
        clarity.setOnClickListener(this);
        mRetryBtn.setOnClickListener(this);
        tv_speed.setOnClickListener(this);
        antholigy.setOnClickListener(this);
        mSpeeds = new ArrayList();
        //0.75倍、1倍、2.0倍、3.0倍、4.0倍
        mSpeeds.add("x0.75");
        mSpeeds.add("x1.0");
        mSpeeds.add("x2.0");
        mSpeeds.add("x3.0");

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        antholigyLayout = (LinearLayout) inflater.inflate(R.layout.jz_layout_antholigy, null);
        recycle_antholigy = antholigyLayout.findViewById(R.id.recycle_antholigy);
        LinearLayoutManager albumManager = new LinearLayoutManager(context);
        recycle_antholigy.setLayoutManager(albumManager);

        antholigyAdapter = new AntholigyAdapter(R.layout.item_jzvd_antholigy, null);
        recycle_antholigy.setAdapter(antholigyAdapter);

    }

    public void setUp(JZDataSource jzDataSource, int screen, Class mediaInterfaceClass) {
        if ((System.currentTimeMillis() - gobakFullscreenTime) < 200) {
            return;
        }

        if ((System.currentTimeMillis() - gotoFullscreenTime) < 200) {
            return;
        }


        super.setUp(jzDataSource, screen, mediaInterfaceClass);
        titleTextView.setText(jzDataSource.title);
        setScreen(screen);
    }

//    public void changeStartButtonSize(int size) {
//        ViewGroup.LayoutParams lp = startButton.getLayoutParams();
//        lp.height = size;
//        lp.width = size;
//        lp = loadingProgressBar.getLayoutParams();
//        lp.height = size;
//        lp.width = size;
//    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_std;
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        changeUiToNormal();
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        changeUiToPreparing();
    }

    public void onStatePreparingPlaying() {
        super.onStatePreparingPlaying();
        changeUIToPreparingPlaying();
    }

    public void onStatePreparingChangeUrl() {
        super.onStatePreparingChangeUrl();
        changeUIToPreparingChangeUrl();
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        changeUiToPlayingClear();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
        changeUiToPauseShow();
        cancelDismissControlViewTimer();
    }

    @Override
    public void onStateError() {
        super.onStateError();
        changeUiToError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        changeUiToComplete();
        cancelDismissControlViewTimer();
        bottomProgressBar.setProgress(100);
    }

    @Override
    public void startVideo() {
        super.startVideo();
        registerWifiListener(getApplicationContext());
    }

    //doublClick 这两个全局变量只在ontouch中使用，就近放置便于阅读
    private long lastClickTime = 0;
    private long doubleTime = 200;
    private ArrayDeque<Runnable> delayTask = new ArrayDeque<>();

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        if (id == R.id.surface_container) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    if (mChangePosition) {
                        long duration = getDuration();
                        int progress = (int) (mSeekTimePosition * 100 / (duration == 0 ? 1 : duration));
                        bottomProgressBar.setProgress(progress);
                    }

                    //加上延时是为了判断点击是否是双击之一，双击不执行这个逻辑
                    Runnable task = () -> {
                        if (!mChangePosition && !mChangeVolume) {
                            onClickUiToggle();
                        }
                    };
                    v.postDelayed(task, doubleTime + 20);
                    delayTask.add(task);
                    while (delayTask.size() > 2) {
                        delayTask.pollFirst();
                    }

                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - lastClickTime < doubleTime) {
                        for (Runnable taskItem : delayTask) {
                            v.removeCallbacks(taskItem);
                        }
                        if (state == STATE_PLAYING || state == STATE_PAUSE) {
                            Log.d(TAG, "doublClick [" + this.hashCode() + "] ");
                            startButton.performClick();
                        }
                    }
                    lastClickTime = currentTimeMillis;
                    break;
            }
        } else if (id == R.id.bottom_seek_progress) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    cancelDismissControlViewTimer();
                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    break;
            }
        }
        return super.onTouch(v, event);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.thumb) {
            if (jzDataSource == null || jzDataSource.urlsMap.isEmpty() || jzDataSource.getCurrentUrl() == null) {
                Toast.makeText(getContext(), getResources().getString(R.string.no_url), Toast.LENGTH_SHORT).show();
                return;
            }
            if (state == STATE_NORMAL) {
                if (!jzDataSource.getCurrentUrl().toString().startsWith("file") &&
                        !jzDataSource.getCurrentUrl().toString().startsWith("/") &&
                        !JZUtils.isWifiConnected(getContext()) && !WIFI_TIP_DIALOG_SHOWED) {
                    showWifiDialog();
                    return;
                }
                startVideo();
            } else if (state == STATE_AUTO_COMPLETE) {
                onClickUiToggle();
            }
        } else if (i == R.id.surface_container) {
            startDismissControlViewTimer();
        } else if (i == R.id.back) {
            backPress();
        } else if (i == R.id.back_tiny) {
            clearFloatScreen();
        } else if (i == R.id.antholigy) {
            if (antholigyPopWindow != null) {
                antholigyPopWindow.dismiss();
            }
            antholigyPopWindow = new PopupWindow(getContext());
            antholigyPopWindow.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
            antholigyPopWindow.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
            antholigyPopWindow.setBackgroundDrawable(new BitmapDrawable());
            antholigyPopWindow.setFocusable(true);
            antholigyPopWindow.setClippingEnabled(false);
            antholigyPopWindow.setContentView(antholigyLayout);
            antholigyPopWindow.showAtLocation(antholigyLayout, Gravity.RIGHT, 0, 0);
            recycle_antholigy.scrollToPosition(antholigyAdapter.getSelectPosition());
            cancelDismissControlViewTimer();
        } else if (i == R.id.clarity) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.jz_layout_clarity, null);
            LinearLayout layout = relativeLayout.findViewById(R.id.video_quality_wrapper_area);
            layout.removeAllViews();
            OnClickListener mQualityListener = v1 -> {
                int index = (int) v1.getTag();
                if (index == jzDataSource.currentUrlIndex) {
                    startDismissControlViewTimer();
                } else {
                    this.seekToInAdvance = getCurrentPositionWhenPlaying();
                    jzDataSource.currentUrlIndex = index;
                    onStatePreparingChangeUrl();

                    clarity.setText(jzDataSource.getCurrentKey().toString());
                    for (int j = 0; j < layout.getChildCount(); j++) {//设置点击之后的颜色
                        if (j == jzDataSource.currentUrlIndex) {
                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#0D7EF9"));
                        } else {
                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#ffffff"));
                        }
                    }
                }


                if (clarityPopWindow != null) {
                    clarityPopWindow.dismiss();
                }
            };
            for (int j = 0; j < jzDataSource.urlsMap.size(); j++) {
                String key = jzDataSource.getKeyFromDataSource(j);
                TextView clarityItem = (TextView) View.inflate(getContext(), R.layout.jz_layout_clarity_item, null);
                clarityItem.setText(key);
                clarityItem.setTag(j);
                layout.addView(clarityItem, j);
                clarityItem.setOnClickListener(mQualityListener);
                if (j == jzDataSource.currentUrlIndex) {
                    clarityItem.setTextColor(Color.parseColor("#0D7EF9"));
                }
            }

            clarityPopWindow = new PopupWindow(getContext());
            clarityPopWindow.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
            clarityPopWindow.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
            clarityPopWindow.setBackgroundDrawable(new BitmapDrawable());
            clarityPopWindow.setFocusable(true);
            clarityPopWindow.setClippingEnabled(false);
            clarityPopWindow.setContentView(relativeLayout);
            clarityPopWindow.showAtLocation(relativeLayout, Gravity.RIGHT, 0, 0);
            cancelDismissControlViewTimer();
        } else if (i == R.id.tv_speed) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.jz_layout_clarity, null);
            LinearLayout layout = relativeLayout.findViewById(R.id.video_quality_wrapper_area);
            layout.removeAllViews();
            OnClickListener mSpeedListener = v1 -> {
                int index = (int) v1.getTag();
                if (index == jzDataSource.currentSpeedIndex) {
                    startDismissControlViewTimer();
                } else {
                    jzDataSource.currentSpeedIndex = index;
                    mediaInterface.setSpeed(getSpeed(index));
                    tv_speed.setText(mSpeeds.get(index));
                    for (int j = 0; j < layout.getChildCount(); j++) {//设置点击之后的颜色
                        if (j == jzDataSource.currentSpeedIndex) {
                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#0D7EF9"));
                        } else {
                            ((TextView) layout.getChildAt(j)).setTextColor(Color.parseColor("#ffffff"));
                        }
                    }
                }

                if (speedPopWindow != null) {
                    speedPopWindow.dismiss();
                }
            };
            for (int j = 0; j < mSpeeds.size(); j++) {
                TextView speedItem = (TextView) View.inflate(getContext(), R.layout.jz_layout_clarity_item, null);
                speedItem.setText(mSpeeds.get(j));
                speedItem.setTag(j);
                layout.addView(speedItem, j);
                speedItem.setOnClickListener(mSpeedListener);
                if (j == jzDataSource.currentSpeedIndex) {
                    speedItem.setTextColor(Color.parseColor("#0D7EF9"));
                }
            }

            speedPopWindow = new PopupWindow(getContext());
            speedPopWindow.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
            speedPopWindow.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
            speedPopWindow.setBackgroundDrawable(new BitmapDrawable());
            speedPopWindow.setFocusable(true);
            speedPopWindow.setClippingEnabled(false);
            speedPopWindow.setContentView(relativeLayout);
            speedPopWindow.showAtLocation(relativeLayout, Gravity.RIGHT, 0, 0);
            cancelDismissControlViewTimer();
        } else if (i == R.id.retry_btn) {
            if (jzDataSource.urlsMap.isEmpty() || jzDataSource.getCurrentUrl() == null) {
                Toast.makeText(getContext(), getResources().getString(R.string.no_url), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!jzDataSource.getCurrentUrl().toString().startsWith("file") && !
                    jzDataSource.getCurrentUrl().toString().startsWith("/") &&
                    !JZUtils.isWifiConnected(getContext()) && !WIFI_TIP_DIALOG_SHOWED) {
                showWifiDialog();
                return;
            }
            tv_speed.setText("倍速");
            jzDataSource.currentSpeedIndex = 1;
            addTextureView();
            onStatePreparing();
        }
    }

    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setScreenNormal() {
        super.setScreenNormal();
        fullscreenButton.setImageResource(R.drawable.jz_enlarge);
//        backButton.setVisibility(View.GONE);
        titleTextView.setVisibility(GONE);
        mIvShare.setVisibility(VISIBLE);
        tinyBackImageView.setVisibility(View.INVISIBLE);
//        changeStartButtonSize((int) getResources().getDimension(R.dimen.jz_start_button_w_h_normal));
        batteryTimeLayout.setVisibility(View.GONE);
        clarity.setVisibility(View.GONE);
        tv_speed.setVisibility(View.GONE);
        antholigy.setVisibility(View.GONE);
        if (clarityPopWindow != null) {
            clarityPopWindow.dismiss();
        }
        if (antholigyPopWindow != null) {
            antholigyPopWindow.dismiss();
        }
        if (speedPopWindow!=null){
            speedPopWindow.dismiss();
        }
    }

    @Override
    public void setScreenFullscreen() {
        super.setScreenFullscreen();
        //进入全屏之后要保证原来的播放状态和ui状态不变，改变个别的ui
        fullscreenButton.setImageResource(R.drawable.jz_shrink);
        //backButton.setVisibility(View.VISIBLE);
        titleTextView.setVisibility(VISIBLE);
        mIvShare.setVisibility(VISIBLE);
        tinyBackImageView.setVisibility(View.INVISIBLE);
        batteryTimeLayout.setVisibility(View.GONE);
        if (jzDataSource.urlsMap.size() == 1) {
            clarity.setVisibility(GONE);
        } else {
            clarity.setText(jzDataSource.getCurrentKey().toString());
            clarity.setVisibility(View.VISIBLE);
        }
        tv_speed.setVisibility(View.VISIBLE);
        antholigy.setVisibility(View.VISIBLE);

//        changeStartButtonSize((int) getResources().getDimension(R.dimen.jz_start_button_w_h_fullscreen));
        setSystemTimeAndBattery();
    }

    @Override
    public void setScreenTiny() {
        super.setScreenTiny();
        tinyBackImageView.setVisibility(View.VISIBLE);
        setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
        batteryTimeLayout.setVisibility(View.GONE);
        clarity.setVisibility(View.GONE);
        tv_speed.setVisibility(View.GONE);
        antholigy.setVisibility(View.GONE);
    }

    @Override
    public void showWifiDialog() {
        super.showWifiDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getResources().getString(R.string.tips_not_wifi));
        builder.setPositiveButton(getResources().getString(R.string.tips_not_wifi_confirm), (dialog, which) -> {
            dialog.dismiss();
            WIFI_TIP_DIALOG_SHOWED = true;
            if (state == STATE_PAUSE) {
                startButton.performClick();
            } else {
                startVideo();
            }

        });
        builder.setNegativeButton(getResources().getString(R.string.tips_not_wifi_cancel), (dialog, which) -> {
            dialog.dismiss();
            releaseAllVideos();
            clearFloatScreen();
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                releaseAllVideos();
                clearFloatScreen();
            }
        });

        builder.create().show();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        cancelDismissControlViewTimer();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        startDismissControlViewTimer();
    }

    public void onClickUiToggle() {//这是事件
        if (bottomContainer.getVisibility() != View.VISIBLE) {
            setSystemTimeAndBattery();
            clarity.setText(jzDataSource.getCurrentKey().toString());
        }
        if (state == STATE_PREPARING) {
            changeUiToPreparing();
            if (bottomContainer.getVisibility() == View.VISIBLE) {
            } else {
                setSystemTimeAndBattery();
            }
        } else if (state == STATE_PLAYING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
            } else {
                changeUiToPlayingShow();
            }
        } else if (state == STATE_PAUSE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            } else {
                changeUiToPauseShow();
            }
        }
    }

    public void setSystemTimeAndBattery() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        videoCurrentTime.setText(dateFormater.format(date));
        if ((System.currentTimeMillis() - LAST_GET_BATTERYLEVEL_TIME) > 30000) {
            LAST_GET_BATTERYLEVEL_TIME = System.currentTimeMillis();
            getContext().registerReceiver(
                    battertReceiver,
                    new IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            );
        } else {
            setBatteryLevel();
        }
    }

    public void setBatteryLevel() {
        int percent = LAST_GET_BATTERYLEVEL_PERCENT;
        if (percent < 15) {
            batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_10);
        } else if (percent >= 15 && percent < 40) {
            batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_30);
        } else if (percent >= 40 && percent < 60) {
            batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_50);
        } else if (percent >= 60 && percent < 80) {
            batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_70);
        } else if (percent >= 80 && percent < 95) {
            batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_90);
        } else if (percent >= 95 && percent <= 100) {
            batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_100);
        }
    }

    public void onCLickUiToggleToClear() {
        if (state == STATE_PREPARING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPreparing();
            } else {
            }
        } else if (state == STATE_PLAYING) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
            } else {
            }
        } else if (state == STATE_PAUSE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            } else {
            }
        } else if (state == STATE_AUTO_COMPLETE) {
            if (bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToComplete();
            } else {
            }
        }
    }


    @Override
    public void onProgress(int progress, long position, long duration) {
        super.onProgress(progress, position, duration);
        if (progress != 0) bottomProgressBar.setProgress(progress);
    }

    @Override
    public void setBufferProgress(int bufferProgress) {
        super.setBufferProgress(bufferProgress);
        if (bufferProgress != 0) bottomProgressBar.setSecondaryProgress(bufferProgress);
    }

    @Override
    public void resetProgressAndTime() {
        super.resetProgressAndTime();
        bottomProgressBar.setProgress(0);
        bottomProgressBar.setSecondaryProgress(0);
    }

    public void changeUiToNormal() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }
    }

    public void changeUiToPreparing() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.VISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }
    }

    public void changeUIToPreparingPlaying() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }
    }

    public void changeUIToPreparingChangeUrl() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }
    }

    public void changeUiToPlayingShow() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }

    }

    public void changeUiToPlayingClear() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case SCREEN_TINY:
                break;
        }

    }

    public void changeUiToPauseShow() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.VISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }
    }

    public void changeUiToPauseClear() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                break;
            case SCREEN_TINY:
                break;
        }

    }

    public void changeUiToComplete() {
        switch (screen) {
            case SCREEN_NORMAL:
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }

    }

    public void changeUiToError() {
        switch (screen) {
            case SCREEN_NORMAL:
                setAllControlsVisiblity(View.INVISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_FULLSCREEN:
                setAllControlsVisiblity(View.VISIBLE, View.INVISIBLE, View.VISIBLE,
                        View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                updateStartImage();
                break;
            case SCREEN_TINY:
                break;
        }

    }

    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro,
                                        int thumbImg, int bottomPro, int retryLayout, int audioVideo) {
        topContainer.setVisibility(topCon);
        bottomContainer.setVisibility(bottomCon);
        startButton.setVisibility(startBtn);
        loadingProgressBar.setVisibility(loadingPro);
        thumbImageView.setVisibility(thumbImg);
        bottomProgressBar.setVisibility(bottomPro);
        mRetryLayout.setVisibility(retryLayout);
    }

    public void updateStartImage() {
        if (state == STATE_PLAYING) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.drawable.jz_click_pause_selector);
            replayTextView.setVisibility(GONE);
        } else if (state == STATE_ERROR) {
            startButton.setVisibility(INVISIBLE);
            replayTextView.setVisibility(GONE);
        } else if (state == STATE_AUTO_COMPLETE) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.drawable.jz_click_replay_selector);
            replayTextView.setVisibility(VISIBLE);
        } else {
            startButton.setImageResource(R.drawable.jz_click_play_selector);
            replayTextView.setVisibility(GONE);
        }
    }

    @Override
    public void showProgressDialog(float deltaX, String seekTime, long seekTimePosition, String totalTime, long totalTimeDuration) {
        super.showProgressDialog(deltaX, seekTime, seekTimePosition, totalTime, totalTimeDuration);
        if (mProgressDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(R.layout.jz_dialog_progress, null);
            mDialogProgressBar = localView.findViewById(R.id.duration_progressbar);
            mDialogSeekTime = localView.findViewById(R.id.tv_current);
            mDialogTotalTime = localView.findViewById(R.id.tv_duration);
            mDialogIcon = localView.findViewById(R.id.duration_image_tip);
            mProgressDialog = createDialogWithView(localView);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }

        mDialogSeekTime.setText(seekTime);
        mDialogTotalTime.setText(" / " + totalTime);
        mDialogProgressBar.setProgress(totalTimeDuration <= 0 ? 0 : (int) (seekTimePosition * 100 / totalTimeDuration));
        if (deltaX > 0) {
            mDialogIcon.setBackgroundResource(R.drawable.jz_forward_icon);
        } else {
            mDialogIcon.setBackgroundResource(R.drawable.jz_backward_icon);
        }
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showVolumeDialog(float deltaY, int volumePercent) {
        super.showVolumeDialog(deltaY, volumePercent);
        if (mVolumeDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(R.layout.jz_dialog_volume, null);
            mDialogVolumeImageView = localView.findViewById(R.id.volume_image_tip);
            mDialogVolumeTextView = localView.findViewById(R.id.tv_volume);
            mDialogVolumeProgressBar = localView.findViewById(R.id.volume_progressbar);
            mVolumeDialog = createDialogWithView(localView);
        }
        if (!mVolumeDialog.isShowing()) {
            mVolumeDialog.show();
        }
        if (volumePercent <= 0) {
            mDialogVolumeImageView.setBackgroundResource(R.drawable.jz_close_volume);
        } else {
            mDialogVolumeImageView.setBackgroundResource(R.drawable.jz_add_volume);
        }
        if (volumePercent > 100) {
            volumePercent = 100;
        } else if (volumePercent < 0) {
            volumePercent = 0;
        }
        mDialogVolumeTextView.setText(volumePercent + "%");
        mDialogVolumeProgressBar.setProgress(volumePercent);
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissVolumeDialog() {
        super.dismissVolumeDialog();
        if (mVolumeDialog != null) {
            mVolumeDialog.dismiss();
        }
    }

    @Override
    public void showBrightnessDialog(int brightnessPercent) {
        super.showBrightnessDialog(brightnessPercent);
        if (mBrightnessDialog == null) {
            View localView = LayoutInflater.from(getContext()).inflate(R.layout.jz_dialog_brightness, null);
            mDialogBrightnessTextView = localView.findViewById(R.id.tv_brightness);
            mDialogBrightnessProgressBar = localView.findViewById(R.id.brightness_progressbar);
            mBrightnessDialog = createDialogWithView(localView);
        }
        if (!mBrightnessDialog.isShowing()) {
            mBrightnessDialog.show();
        }
        if (brightnessPercent > 100) {
            brightnessPercent = 100;
        } else if (brightnessPercent < 0) {
            brightnessPercent = 0;
        }
        mDialogBrightnessTextView.setText(brightnessPercent + "%");
        mDialogBrightnessProgressBar.setProgress(brightnessPercent);
        onCLickUiToggleToClear();
    }

    @Override
    public void dismissBrightnessDialog() {
        super.dismissBrightnessDialog();
        if (mBrightnessDialog != null) {
            mBrightnessDialog.dismiss();
        }
    }

    public Dialog createDialogWithView(View localView) {
        Dialog dialog = new Dialog(getContext(), R.style.jz_style_dialog_progress);
        dialog.setContentView(localView);
        Window window = dialog.getWindow();
        window.addFlags(Window.FEATURE_ACTION_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.gravity = Gravity.CENTER;
        window.setAttributes(localLayoutParams);
        return dialog;
    }

    public void startDismissControlViewTimer() {
        cancelDismissControlViewTimer();
        DISMISS_CONTROL_VIEW_TIMER = new Timer();
        mDismissControlViewTimerTask = new DismissControlViewTimerTask();
        DISMISS_CONTROL_VIEW_TIMER.schedule(mDismissControlViewTimerTask, 4000);
    }

    public void cancelDismissControlViewTimer() {
        if (DISMISS_CONTROL_VIEW_TIMER != null) {
            DISMISS_CONTROL_VIEW_TIMER.cancel();
        }
        if (mDismissControlViewTimerTask != null) {
            mDismissControlViewTimerTask.cancel();
        }

    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        cancelDismissControlViewTimer();
    }

    @Override
    public void reset() {
        super.reset();
        cancelDismissControlViewTimer();
        if (clarityPopWindow != null) {
            clarityPopWindow.dismiss();
        }
        if (antholigyPopWindow!=null) {
            antholigyPopWindow.dismiss();
        }
        if (speedPopWindow!=null) {
            speedPopWindow.dismiss();
        }
        unregisterWifiListener(getApplicationContext());
    }

    public void dissmissControlView() {
        if (state != STATE_NORMAL
                && state != STATE_ERROR
                && state != STATE_AUTO_COMPLETE) {
            post(() -> {
                bottomContainer.setVisibility(View.INVISIBLE);
                topContainer.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.INVISIBLE);
                if (clarityPopWindow != null) {
                    clarityPopWindow.dismiss();
                }
                if (antholigyPopWindow!=null){
                    antholigyPopWindow.dismiss();
                }
                if (speedPopWindow!=null) {
                    speedPopWindow.dismiss();
                }
                if (screen != SCREEN_TINY) {
                    bottomProgressBar.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public class DismissControlViewTimerTask extends TimerTask {

        @Override
        public void run() {
            dissmissControlView();
        }
    }

    private BroadcastReceiver battertReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                int percent = level * 100 / scale;
                LAST_GET_BATTERYLEVEL_PERCENT = percent;
                setBatteryLevel();
                try {
                    getContext().unregisterReceiver(battertReceiver);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void registerWifiListener(Context context) {
        if (context == null) return;
        mIsWifi = JZUtils.isWifiConnected(context);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(wifiReceiver, intentFilter);
    }

    private void unregisterWifiListener(Context context) {
        if (context == null) return;
        context.unregisterReceiver(wifiReceiver);
    }

    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                boolean isWifi = JZUtils.isWifiConnected(context);
                if (mIsWifi == isWifi) return;
                mIsWifi = isWifi;
                if (!mIsWifi && !WIFI_TIP_DIALOG_SHOWED && state == STATE_PLAYING) {
                    startButton.performClick();
                    showWifiDialog();
                }
            }
        }
    };

    private float getSpeed(int position) {
        float speed = 1f;
        switch (position) {
            case 0:
                speed = 0.75f;
                break;
            case 1:
                speed = 1f;
                break;
            case 2:
                speed = 2f;
                break;
            case 3:
                speed = 3f;
                break;
        }
        return speed;
    }

}
