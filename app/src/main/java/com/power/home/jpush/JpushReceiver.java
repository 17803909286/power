package com.power.home.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.power.home.common.Constant;
import com.power.home.common.util.JsonUtils;
import com.power.home.common.util.LogUtil;
import com.power.home.common.util.StringUtil;
import com.power.home.data.bean.ForwardAddressBean;
import com.google.gson.Gson;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class JpushReceiver extends BroadcastReceiver {
    private Context mContext;
    private static final String TAG = "JIGUANG";
    public static String regId;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Bundle bundle = intent.getExtras();
        String msg = bundle.getString(JPushInterface.EXTRA_EXTRA);
        try {
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                LogUtil.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                LogUtil.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息(内容为): " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                LogUtil.d(TAG, "[MyReceiver] 接收到推送下来的通知");

                String extra_json = bundle.getString(JPushInterface.EXTRA_EXTRA);
                LogUtil.d(TAG, "[MyReceiver] 接收到推送下来的通知附加字段" + extra_json);

            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                jump(bundle.getString(JPushInterface.EXTRA_EXTRA));
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                LogUtil.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                LogUtil.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                LogUtil.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void jump(String msg) {
        if (StringUtil.isNotNullString(msg) && msg.contains("router")) {
            ForwardAddressBean forwardAddressBean = new Gson().fromJson(msg, ForwardAddressBean.class);
            ARouter.getInstance().build("/android/" + forwardAddressBean.getRouter())
                    .withString(Constant.type, forwardAddressBean.getType())
                    .withString(Constant.id, forwardAddressBean.getId())
                    .withString(Constant.data,forwardAddressBean.getWebUrl())
                    .navigation();
        }
    }

}
