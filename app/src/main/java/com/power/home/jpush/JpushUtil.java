package com.power.home.jpush;

import android.app.Notification;
import android.text.TextUtils;

import com.power.home.App;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.ACache;
import com.power.home.common.util.LogUtil;

import java.util.Set;
import java.util.TreeSet;

import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 *
 * Created by zhp on 2017/12/25.
 */
public class JpushUtil {
    public static void init(){
        JPushInterface.setDebugMode(false);
        JPushInterface.init(App.getApplication());
        JPushInterface.setStatisticsEnable(true);
        CustomPushNotificationBuilder pushNotificationBuilder = new CustomPushNotificationBuilder(
                App.getApplication(), R.layout.customer_notification_layout,
                R.id.icon, R.id.title, R.id.text);
        pushNotificationBuilder.statusBarDrawable = R.mipmap.ic_launcher;
        pushNotificationBuilder.layoutIconDrawable = R.mipmap.ic_launcher;
        pushNotificationBuilder.notificationFlags = Notification.FLAG_AUTO_CANCEL;
        pushNotificationBuilder.notificationDefaults = Notification.DEFAULT_SOUND;
        JPushInterface.setDefaultPushNotificationBuilder(pushNotificationBuilder);
        Set<String> set = new TreeSet<>();
        set.add(Constant.IS_RELEASE?"release":"debug");
        JPushInterface.setTags(App.getApplication(),
                set,
                new TagAliasCallback() {
                    @Override
                    public void gotResult(int code, String alias, Set<String> tags) {
                        switch (code) {
                            case 0:
                                LogUtil.i("jpush", "Set tag and tag success");
                                break;
                            case 6002:
                                LogUtil.i("jpush", "Failed to set tag and tags due to timeout. Try again after 60s.");
                                break;
                            default:
                                LogUtil.i("jpush", "tag Failed with errorCode = " + code);
                                break;
                        }
                    }
                });
    }
}
