package com.power.home.ui.widget.floatview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.MusicList;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.Music;
import com.power.home.jzvd.JZUtils;
import com.power.home.ui.service.MediaService;


/**
 * Created by ZHP on 2020/5/15 0015.悬浮窗
 */
public class EnFloatingView extends FloatingMagnetView implements View.OnClickListener {

    private final ImageView mIcon;

    public RelativeLayout rlServiceAudio;

    public TextView tv_float_title, tv_float_progress;

    public ImageView iv_play, iv_audio_close;

    public boolean isPlaying = false;
    private Music music;

    public EnFloatingView(@NonNull Context context) {
        this(context, R.layout.media_float_view);
    }

    public EnFloatingView(@NonNull Context context, @LayoutRes int resource) {
        super(context, null);
        inflate(context, resource, this);
        rlServiceAudio = findViewById(R.id.rl_service_audio);
        tv_float_title = findViewById(R.id.tv_float_title);
        tv_float_progress = findViewById(R.id.tv_float_progress);
        iv_play = findViewById(R.id.iv_play);
        mIcon = findViewById(R.id.iv_audio);
        iv_audio_close = findViewById(R.id.iv_audio_close);

        rlServiceAudio.setOnClickListener(this);
        iv_play.setOnClickListener(this);
        iv_audio_close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play:
                if (music.isAudio() && music.isCanPlay()) {
                    EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_FLOAT_AUDIO_PLAY_PAUSE, music));
                    playPause();
                } else {
                    //跳转播放单集
                    ARouter.getInstance().build("/android/video")
                            .withString(Constant.type, "2")
                            .withString(Constant.id, music.getId())
                            .withSerializable(Constant.seek, music)
                            .withString(Constant.from, Constant.from)
                            .navigation();
                }
                break;
            case R.id.iv_audio_close:
                SharePreferencesUtils.clearFloatFlag();
                FloatingView.get().getView().setVisibility(GONE);
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PAUSE, music));
                EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE, EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE));
//                UIUtil.getContext().stopService(new Intent(UIUtil.getContext(), MediaService.class));
                break;
            case R.id.rl_service_audio:
                //跳转播放单集
                ARouter.getInstance().build("/android/video")
                        .withString(Constant.type, "2")
                        .withString(Constant.id, music.getId())
                        .withSerializable(Constant.seek, music)
                        .withString(Constant.from, Constant.from)
                        .navigation();
                break;
        }
    }

    private void playPause() {
        if (isPlaying) {
            iv_play.setImageDrawable(UIUtil.getDrawable(R.drawable.icon_audio_pause));
            isPlaying = false;
            iv_audio_close.setVisibility(View.GONE);
            music.setPlaying(true);
        } else {
            iv_play.setImageDrawable(UIUtil.getDrawable(R.drawable.icon_audio_play));
            isPlaying = true;
            iv_audio_close.setVisibility(View.VISIBLE);
            music.setPlaying(false);
        }
    }

    public void setPic(String url) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.icon_place_holder_64_64)
                .error(R.drawable.icon_place_holder_64_64)
                .fallback(R.drawable.icon_place_holder_64_64);
        Glide.with(UIUtil.getContext()).load(url).apply(requestOptions).into(mIcon);
    }

    /**
     * 展示信息
     *
     * @param music
     */
    public void setMusic(Music music) {
        this.music = music;
        setTitle(music.getTitle());
        setTime(JZUtils.stringForTime(music.getCurrentPosition()) + "/" + JZUtils.stringForTime(music.getDuration()));
        setPic(music.getImage());
    }

    public void setTitle(String title) {
        tv_float_title.setText(title);
    }

    public void setTime(String time) {
        tv_float_progress.setText(time);
    }

    /**
     * 播放完成展示播放键
     */
    public void playFinish() {
        setShowPlay(true);
    }

    /**
     * 自动播放下一集展示暂停键
     */
    public void setNextVideo(Music music) {
        setShowPlay(true);
        setMusic(music);
        this.music = music;
    }

    /**
     * widget外部控制展示暂停 显示
     *
     * @param showPlay
     */
    public void setShowPlay(boolean showPlay) {
        if (showPlay) {
            iv_play.setImageDrawable(UIUtil.getDrawable(R.drawable.icon_audio_play));
            isPlaying = false;
            iv_audio_close.setVisibility(View.VISIBLE);
        } else {
            iv_play.setImageDrawable(UIUtil.getDrawable(R.drawable.icon_audio_pause));
            isPlaying = true;
            iv_audio_close.setVisibility(View.GONE);
        }
    }
}
