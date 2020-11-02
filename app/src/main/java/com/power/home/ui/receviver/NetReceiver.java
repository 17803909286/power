package com.power.home.ui.receviver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.power.home.common.util.LogUtil;
import com.power.home.common.util.UIUtil;

/**
 * Created by S on 2018/3/28.
 */

public class NetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {

            // 获取网络连接管理器
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) UIUtil.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取当前网络状态信息
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();

            if (info != null && info.isAvailable()) {
                LogUtil.d("ZZZZ","监听到可用网络切换,调用重连方法");
            }

        }
    }

}
