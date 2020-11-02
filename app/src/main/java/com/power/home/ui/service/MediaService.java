package com.power.home.ui.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.FloatMusicUtil;
import com.power.home.common.util.LogUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.Music;
import com.power.home.ui.activity.MainActivity;
import com.power.home.ui.widget.AduioWidget;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by ZHP on 2020/5/15 0015.
 */
public class MediaService extends Service {

    private static final String TAG = "MediaService";

    //初始化MediaPlayer
    public static MediaPlayer mMediaPlayer;

    public static final int UPDATE_SHOW_PLAY = 1, UPDATE_SHOW_PAUSE = 2;
    private String notificationChannelID = "1";
    private RemoteViews remoteView;
    private Notification notification;
    private int notifyId = 1;
    private Disposable subscribe;
    private Music music;
    private WifiManager.WifiLock wifiLock;
    private int seek;

    public MediaService() {
    }

    @Override
    public void onCreate() {
        EventBusUtils.register(this);
        mMediaPlayer = new MediaPlayer();//This is only done once, used to prepare the player.
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null != intent) {
            music = (Music) intent.getSerializableExtra(Constant.service_data);
            if (null != music) {
                iniMediaPlayerFile();
                initNotificationBar();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 添加file文件到MediaPlayer对象并且准备播放音频
     */
    private void iniMediaPlayerFile() {
        try {
            //此处的两个方法需要捕获IO异常

            // 设置设备进入锁状态模式-可在后台播放或者缓冲音乐-CPU一直工作
            mMediaPlayer.setWakeMode(this, PowerManager.PARTIAL_WAKE_LOCK);
            // 如果你使用wifi播放流媒体，你还需要持有wifi锁
            wifiLock = ((WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE)).createWifiLock(WifiManager.WIFI_MODE_FULL, "wifilock");
            wifiLock.acquire();
            //让MediaPlayer对象准备
            mMediaPlayer.setLooping(false);//是否循环播放

            //设置音频文件到MediaPlayer对象中
            if (StringUtil.isNotNullString(music.getUrl())) {
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(music.getUrl());
                mMediaPlayer.prepare();//网络视频，异步
            }

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (null != subscribe) {
                        subscribe.dispose();
                        subscribe = null;
                    }
                    EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_PLAY_FINISH, music.getPosition()));
                }
            });

        } catch (IOException e) {
            Log.d(TAG, "设置资源，准备阶段出错");
            e.printStackTrace();
        }
    }

    public void playOrPause() {
        if (null == mMediaPlayer) {
            return;
        }
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            FloatMusicUtil.saveStatus(music);
            postState(UPDATE_SHOW_PLAY, true);
            Log.i(TAG, "Play stop");
        } else {
            showNotification();
            mMediaPlayer.start();
            mMediaPlayer.seekTo(seek);
            postState(UPDATE_SHOW_PAUSE, false);
            Log.i(TAG, "Play start");
        }
    }

    public void play() {
        if (null == mMediaPlayer && !music.isCanPlay()) {
            return;
        }
        mMediaPlayer.start();
        mMediaPlayer.seekTo(seek);
        postState(UPDATE_SHOW_PAUSE, false);
        Log.i(TAG, "Play start");
    }

    public void pause() {
        if (null == mMediaPlayer) {
            return;
        }
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            FloatMusicUtil.saveStatus(music);
            postState(UPDATE_SHOW_PLAY, true);
            Log.i(TAG, "Play stop");
        }
    }


    private void postState(int state, boolean showPlay) {

        updateNotification(showPlay);
        switch (state) {
            case MediaService.UPDATE_SHOW_PLAY:
                //暂停时 停止推送
                if (null != subscribe) {
                    subscribe.dispose();
                    subscribe = null;
                }
                break;
            case MediaService.UPDATE_SHOW_PAUSE:
                sendMessage();
                break;
        }

    }

    private void sendMessage() {
        if (null != subscribe) {
            subscribe.dispose();
            subscribe = null;
        }
        subscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (null != music) {
                            music.setDuration(mMediaPlayer.getDuration());
                            music.setCurrentPosition(mMediaPlayer.getCurrentPosition());
                            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_SECOND, music));
                            Log.i(TAG, "Play post");
                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(EventBusEvent date) {
        if (date != null) {

            switch (date.getCode()) {
                case EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_NOTIFICATION_CLOSE");
                    hideNotification();
                    break;
                case EventBusUtils.EventCode.SERVICE_NOTIFICATION_OPEN:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_NOTIFICATION_OPEN");
                    showNotification();
                    break;
                case EventBusUtils.EventCode.SERVICE_SEEK:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_SEEK");
                    pause();
                    seek = (int) date.getData();
                    play();
                    break;
                case EventBusUtils.EventCode.SERVICE_PLAY:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_PLAY");
                    music = (Music) date.getData();
                    seek = music.getCurrentPosition();
                    play();
                    break;
                case EventBusUtils.EventCode.SERVICE_PAUSE:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_PAUSE");
                    music = (Music) date.getData();
                    seek = music.getCurrentPosition();
                    pause();
                    break;
                case EventBusUtils.EventCode.SERVICE_STOP:
                    /*if (null != mMediaPlayer) {
                        mMediaPlayer.reset();
                        mMediaPlayer.stop();
                        mMediaPlayer.release();
                        try {
                            mMediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }*/
                    break;
                case EventBusUtils.EventCode.SERVICE_FLOAT_AUDIO_PLAY_PAUSE:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_FLOAT_AUDIO_PLAY_PAUSE");
                    music = (Music) date.getData();
                    seek = music.getCurrentPosition();
                    playOrPause();
                    break;
                case EventBusUtils.EventCode.SERVICE_NEXT:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_NEXT");
                    music = (Music) date.getData();
                    seek = 0;
                    nextMusic();
                    break;
                case EventBusUtils.EventCode.SERVICE_NEXT_PREPARE:
                    LogUtil.e("MMMMMMMMMMMMMMMMMM", "=======SERVICE_NEXT_PREPARE");
                    music = (Music) date.getData();
                    seek = 0;
                    music.setCurrentPosition(0);
                    prepareNextMusic();
                    break;
            }
        }
    }

    private void nextMusic() {
        try {
            // 切歌之前先重置，释放掉之前的资源
            mMediaPlayer.reset();
            // 设置播放源
            mMediaPlayer.setDataSource(music.getUrl());
            // 开始播放前的准备工作，加载多媒体资源，获取相关信息
            mMediaPlayer.prepare();
            // 开始播放
            play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareNextMusic() {
        try {
            // 切歌之前先重置，释放掉之前的资源
            mMediaPlayer.reset();
            // 设置播放源
            mMediaPlayer.setDataSource(music.getUrl());
            // 开始播放前的准备工作，加载多媒体资源，获取相关信息
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    private void initNotificationBar() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, notificationChannelID);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = UIUtil.getString(R.string.app_name);
            String description = UIUtil.getString(R.string.app_name);
            /**
             * Oreo不用Priority了，用importance
             * IMPORTANCE_NONE 关闭通知
             * IMPORTANCE_MIN 开启通知，不会弹出，但没有提示音，状态栏中无显示
             * IMPORTANCE_LOW 开启通知，不会弹出，不发出提示音，状态栏中显示
             * IMPORTANCE_DEFAULT 开启通知，不会弹出，发出提示音，状态栏中显示
             * IMPORTANCE_HIGH 开启通知，会弹出，发出提示音，状态栏中显示
             */
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(notificationChannelID, name, importance);
            mChannel.setDescription(description);
            mChannel.setLightColor(Color.RED);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            mNotificationManager.createNotificationChannel(mChannel);
        }
        Intent intent = new Intent(this, MainActivity.class);

        /*intent.putExtra(Constant.type, "2");
        intent.putExtra(Constant.id, music.getId());
        intent.putExtra(Constant.seek, music);
        intent.putExtra(Constant.from, Constant.from);
        intent.putExtra(Constant.notify, Constant.notify);*/

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        remoteView = new RemoteViews(getPackageName(), R.layout.widget_audio);
        updateNotifyTitle();
        remoteView.setOnClickPendingIntent(R.id.iv_widget_play, getPendingIntent(this, R.id.iv_widget_play));
        remoteView.setOnClickPendingIntent(R.id.iv_widget_close, getPendingIntent(this, R.id.iv_widget_close));
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            remoteView.setImageViewResource(R.id.iv_widget_play, R.drawable.icon_widget_pause);
        } else {
            remoteView.setImageViewResource(R.id.iv_widget_play, R.drawable.icon_widget_play);
        }
        mBuilder.setContentIntent(pendingIntent)
                .setContent(remoteView)
                .setWhen(System.currentTimeMillis())
                .setOngoing(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
//                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setChannelId(notificationChannelID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        notification = mBuilder.build();
        //如果设置了FLAG_ONGOING_EVENT 或者 FLAG_NO_CLEAR 是不能点击清除的，还会导致不会显示悬浮通知和锁屏通知。所以，我们不设置或者设置为FLAG_AUTO_CANCEL就可以了
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
        showNotification();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, notification);
            if (!music.isAudio()) {
                stopForeground(true);
            }
        } else {
            if (!music.isAudio()) {
                mNotificationManager.cancel(notifyId);
            }
        }
    }

    private void updateNotification(Boolean showPlay) {
        updateNotifyTitle();
        //设定按钮图片
        if (showPlay) {
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_SHOW_PLAY, music));
            remoteView.setImageViewResource(R.id.iv_widget_play, R.drawable.icon_widget_play);
        } else {
            EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.APP_REFREASH_FLOAT_AUDIO_SHOW_PAUSE, music));
            remoteView.setImageViewResource(R.id.iv_widget_play, R.drawable.icon_widget_pause);
        }
        notification.contentView = remoteView;
        showNotification();
    }

    private void updateNotifyTitle() {
        if (null == remoteView) {
            return;
        }
        String title = music.getTitle();
        remoteView.setTextViewText(R.id.tv_widget_title, title);
        Glide.with(UIUtil.getContext()).asBitmap().load(music.getImage()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                remoteView.setImageViewBitmap(R.id.iv_widget_audio, resource);
            }
        });
    }

    private PendingIntent getPendingIntent(Context context, int buttonId) {
        Intent intent = new Intent();
        intent.setClass(context, AduioWidget.class);
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
//        intent.addCategory(Intent.WIDGET_CATEGORY_KEYGUARD);
        intent.setData(Uri.parse("" + buttonId));
        intent.putExtra("music", music);
        intent.putExtra("id", buttonId);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return pi;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
        if (null != subscribe) {
            subscribe.dispose();
            subscribe = null;
        }
        if (null != mMediaPlayer && mMediaPlayer.isPlaying()) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        if (null != wifiLock)
            wifiLock.release();
        hideNotification();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(notifyId, notification);
        } else {
            if (null != notification) {
                NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                manager.notify(notifyId, notification);
            }
        }
    }

    public void hideNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true);
        } else {
            if (null != notification) {
                NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
        }
    }
}
