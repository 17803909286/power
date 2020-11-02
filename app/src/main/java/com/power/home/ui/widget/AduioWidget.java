package com.power.home.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.common.util.ACache;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.Music;
import com.power.home.ui.activity.MainActivity;

/**
 * Created by ZHP on 2020/5/15 0015.
 */
public class AduioWidget extends AppWidgetProvider {
    private boolean mStop = true;
    public static final String CLICK_ACTION = "com.power.home.action.CLICK"; // 点击事件的广播ACTION

    /**
     * 每次窗口小部件被更新都调用一次该方法
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        pushUpdate(context, AppWidgetManager.getInstance(context), listMusic.get(MediaService.mPosition).getTitle(), true);
    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     */
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
//        pushUpdate(context, AppWidgetManager.getInstance(context), listMusic.get(MediaService.mPosition).getTitle(), true);
    }

    /**
     * 当最后一个该窗口小部件删除时调用该方法
     */
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasCategory(Intent.CATEGORY_ALTERNATIVE)) {
            int buttonId = intent.getIntExtra("id", 0);
            Music music = (Music) ACache.get(UIUtil.getContext()).getAsObject(Constant.floatStatus);
            if (null == music) {
                music = (Music) intent.getSerializableExtra("music");
            }
            switch (buttonId) {
                case R.id.iv_widget_play:
                    if (music.isAudio() && music.isCanPlay()) {
                        EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_FLOAT_AUDIO_PLAY_PAUSE, music));
                    } else {
                        //跳转播放单集
                        /*ARouter.getInstance().build("/android/video")
                                .withString(Constant.type, "2")
                                .withString(Constant.id, music.getId())
                                .withSerializable(Constant.seek, music)
                                .withString(Constant.from, Constant.from)
                                .navigation();*/
                    }
                    break;
                case R.id.iv_widget_close:
                    EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_PAUSE, music));
                    EventBusUtils.sendEvent(new EventBusEvent<Object>(EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE, EventBusUtils.EventCode.SERVICE_NOTIFICATION_CLOSE));
                    break;
            }

        }
        super.onReceive(context, intent);
    }

}
