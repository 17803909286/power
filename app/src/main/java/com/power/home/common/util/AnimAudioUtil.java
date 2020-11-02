package com.power.home.common.util;

import android.media.AudioManager;
import android.media.SoundPool;




public class AnimAudioUtil {
    private  SoundPool soundPool;
    private  int  soundID = 0;
    private boolean loadFinish = false;
    private boolean startPlay = false;


    public AnimAudioUtil(int animAudioRes) {
        if(null == soundPool){
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
            soundID = soundPool.load(UIUtil.getContext(), animAudioRes, 1);
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                    loadFinish = true;
                    if(startPlay){
                        play();
                    }
                    startPlay = false;
                }
            });
        }
    }

    public void play() {
        if(!loadFinish){
            startPlay = true;
            return;
        }
        soundPool.play(
                soundID,
                0.1f,
                0.5f,
                1,
                0,
                1
        );
    }
    public void release(){
        if(null != soundPool){
            soundPool.release();
            soundPool = null;
        }
    }

    public void stop(){
        if(null != soundPool){
            soundPool.stop(soundID);
        }
    }
}
