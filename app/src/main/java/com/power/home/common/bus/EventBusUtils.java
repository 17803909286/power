package com.power.home.common.bus;

import com.power.home.BuildConfig;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by S on 2018/3/30.
 */

public class EventBusUtils {

    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }

    }

    public static void unregister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static void sendEvent(EventBusEvent event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(EventBusEvent event) {
        EventBus.getDefault().postSticky(event);
    }

    public static final class EventCode {
        public static final int VIDEO_BEAN = 0x111111;
        public static final int VIDEO_COMPLETE = 0x111112;
        public static final int WX_LOGIN_CODE = 0x111113;
        public static final int WITHDRAWL_SUCESS = 0x111114;
        public static final int NICK_SUCESS = 0x111115;
        public static final int SHARE_GROTH = 0x111116;
        public static final int SHARE_EMBA = 0x111117;
        public static final int SHARE_ALBUM = 0x111118;
        public static final int FINISH_Login = 0x111119;
        public static final int LOGIN_BUY_REFRESH = 0x111120;
        public static final int FINISH_PAY_WAY = 0x111121;
        public static final int FINISH_EDIT_SHARE = 0x111122;
        public static final int REFREASH_TEAM = 0x111123;
        public static final int APP_OPEN_FLOAT = 0x111124;
        public static final int APP_REFREASH_FLOAT_AUDIO_SECOND = 0x111125;
        public static final int SERVICE_FLOAT_AUDIO_PLAY_PAUSE = 0x111126;
        public static final int SERVICE_NEXT = 0x111127;
        public static final int APP_REFREASH_FLOAT_AUDIO_PLAY_FINISH = 0x111128;
        public static final int APP_REFREASH_FLOAT_AUDIO_SHOW_PLAY = 0x111129;
        public static final int APP_REFREASH_FLOAT_AUDIO_SHOW_PAUSE = 0x111130;
        public static final int SERVICE_NOTIFICATION_CLOSE = 0x111131;
        public static final int SERVICE_PLAY = 0x111132;
        public static final int SERVICE_PAUSE = 0x111133;
        public static final int SERVICE_SEEK = 0x111134;
        public static final int SERVICE_NOTIFICATION_OPEN = 0x111136;
        public static final int SERVICE_NEXT_PREPARE = 0x111137;
        public static final int SERVICE_STOP = 0x111138;
        public static final int CERTIFICATION_SAVE_REFRESH = 0x111139;
    }

}
