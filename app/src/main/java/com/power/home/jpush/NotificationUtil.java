package com.power.home.jpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;

import androidx.core.app.NotificationCompat;
import android.widget.RemoteViews;

import com.power.home.R;
import com.power.home.ui.activity.SplashActivity;


/**
 * 推送Notification
 * Created by zhp on 2017/12/18.
 */

public class NotificationUtil {
    private int notification_id = 0;
    private Context context;
    private NotificationManager notificationManager;
    private Notification notification;
    private Intent updateIntent;
    private PendingIntent pendingIntent;
    public NotificationUtil(Context context){
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @SuppressWarnings("deprecation")
    public void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(((BitmapDrawable)context.getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap());
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.layout_jpush_notification_item);
        contentView.setTextViewText(R.id.tvTitle, context.getResources().getString(R.string.app_name));
        contentView.setTextViewText(R.id.tvDate, "");
        builder.setContent(contentView);
        builder.setTicker("推送");
        builder.setDefaults(Notification.DEFAULT_SOUND);//向通知添加声音
        notification = builder.build();
        updateIntent = new Intent();
        updateIntent = new Intent(context, SplashActivity.class);
        updateIntent.getPackage();
        updateIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(context, 0, updateIntent, 0);
        notification.contentIntent = pendingIntent;
        notification_id++;
        notificationManager.notify(notification_id, notification);
    }
}
