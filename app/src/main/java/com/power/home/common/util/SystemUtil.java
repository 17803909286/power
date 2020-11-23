package com.power.home.common.util;

import android.os.Build;

import java.util.Locale;

public class SystemUtil {

    public static String getSystemLanguage(){

        return Locale.getDefault().getLanguage();
    }

    public static Locale[] getSystemLanguageList(){

        return Locale.getAvailableLocales();
    }

    public static String getSysVersion(){
        return Build.VERSION.RELEASE;
    }

    public static String getSystemModel(){
        return Build.MODEL;
    }

    public static String getDeviceBrand(){
        return Build.BRAND;
    }
}
