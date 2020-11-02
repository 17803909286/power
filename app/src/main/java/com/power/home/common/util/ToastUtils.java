package com.power.home.common.util;

import android.content.Context;
import android.widget.Toast;

import com.power.home.App;

public class ToastUtils {

    public static void show(Context context, String msg) {
        longShow(context, msg);
    }

    public static void longShow(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void shortShow(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(String msg) {
        longShow(App.getApplication(), msg);
    }

    public static void longShow(String msg) {
        Toast.makeText(App.getApplication(), msg, Toast.LENGTH_LONG).show();
    }

    public static void shortShow(String msg) {
        Toast.makeText(App.getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

}
