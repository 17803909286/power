package com.power.home.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.power.home.R;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.jzvd.JzvdStd;


/**
 * 这里可以监听到视频播放的生命周期和播放状态
 * 所有关于视频的逻辑都应该写在这里
 * Created by Nathen on 2017/7/2.
 */
public class MyJzPlayer extends JzvdStd {

    public RelativeLayout rl_buy_album;
    public TextView tv_album_price;
    public TextView tv_album_hint;
    public TextView my_audio_video_change;
    public ImageView buyBack;
    public ImageView buyShare;
    public CircleImageView civHead;
    public TextView tvCourseName;
    public RelativeLayout rlAudioBg;
    public ImageView ivAudioStart;
    public LinearLayout llAudioController;
    public TextView tvAudioCurrent;
    public SeekBar seekProgress;
    public TextView tvAudioTotal;
    public ImageView ivAudioBack;
    public ImageView ivAudioShare;

    public MyJzPlayer(Context context) {
        super(context);
    }

    public MyJzPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
        initView();

    }
    @Override
    public int getLayoutId() {
        return R.layout.my_jz_player;
    }

    private void initView() {
        rl_buy_album = findViewById(R.id.rl_buy_album);
        tv_album_hint = findViewById(R.id.tv_album_hint);
        tv_album_price = findViewById(R.id.tv_album_price);
        buyBack = findViewById(R.id.iv_course_back);
        buyShare = findViewById(R.id.iv_course_share);

        my_audio_video_change = findViewById(R.id.my_audio_video_change);
        my_audio_video_change = findViewById(R.id.my_audio_video_change);
        civHead = findViewById(R.id.civ_head);

        tvCourseName = findViewById(R.id.tv_course_name);
        rlAudioBg = findViewById(R.id.rl_audio_bg);
        ivAudioStart = findViewById(R.id.iv_audio_start);
        llAudioController = findViewById(R.id.ll_audio_controller);
        tvAudioCurrent = findViewById(R.id.tv_audio_current);
        seekProgress = findViewById(R.id.seek_progress);
        tvAudioTotal = findViewById(R.id.tv_audio_total);
        ivAudioBack = findViewById(R.id.iv_audio_back);
        ivAudioShare = findViewById(R.id.iv_audio_share);
















        backButton.setOnClickListener(this);
        rl_buy_album.setOnClickListener(this);
        buyBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.back) {
            if (screen == SCREEN_NORMAL){
                ((Activity)getContext()).finish();
            }else if (screen == SCREEN_FULLSCREEN){
                backPress();
            }
        }
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.fullscreen) {
            Log.i(TAG, "onClick: fullscreen button");
        } else if (i == R.id.start) {
            Log.i(TAG, "onClick: start button");
        }
        if (v.getId() ==R.id.iv_course_back){
            ((Activity)getContext()).finish();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);
        int id = v.getId();
        if (id == R.id.surface_container) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    if (mChangePosition) {
                        Log.i(TAG, "Touch screen seek position");
                    }
                    if (mChangeVolume) {
                        Log.i(TAG, "Touch screen change volume");
                    }
                    break;
            }
        }

        return false;
    }



    @Override
    public void startVideo() {
        super.startVideo();
        Log.i(TAG, "startVideo");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        Log.i(TAG, "Seek position ");
    }

    @Override
    public void gotoScreenFullscreen() {
        super.gotoScreenFullscreen();
        Log.i(TAG, "goto Fullscreen");
    }

    @Override
    public void gotoScreenNormal() {
        super.gotoScreenNormal();
        Log.i(TAG, "quit Fullscreen");
    }

    @Override
    public void autoFullscreen(float x) {
        super.autoFullscreen(x);
        Log.i(TAG, "auto Fullscreen");
    }

    @Override
    public void onClickUiToggle() {
        super.onClickUiToggle();
        Log.i(TAG, "click blank");
    }

    //onState 代表了播放器引擎的回调，播放视频各个过程的状态的回调
    @Override
    public void onStateNormal() {
        super.onStateNormal();
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
    }

    @Override
    public void onStateError() {
        super.onStateError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        Log.i(TAG, "Auto complete");

    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.VIDEO_COMPLETE,"" ));
    }

    //changeUiTo 真能能修改ui的方法
    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
    }

    @Override
    public void changeUiToPreparing() {
        super.changeUiToPreparing();
    }

    @Override
    public void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
    }

    @Override
    public void changeUiToPlayingClear() {
        super.changeUiToPlayingClear();
    }

    @Override
    public void changeUiToPauseShow() {
        super.changeUiToPauseShow();
    }

    @Override
    public void changeUiToPauseClear() {
        super.changeUiToPauseClear();
    }

    @Override
    public void changeUiToComplete() {
        super.changeUiToComplete();
    }

    @Override
    public void changeUiToError() {
        super.changeUiToError();
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }

}
