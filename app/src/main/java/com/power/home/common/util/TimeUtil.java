package com.power.home.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangpeng on 2017/11/20.
 */

public class TimeUtil {


    public static String dateFormat_hour1 = "HH:mm";
    public static String dateFormat_hour2 = "HH:mm:ss";
    public static String dateFormat_minutes = "mm:ss";

    public static String dateFormat_month = "MM-dd";



    public static String dateToString(long time) {

        return dateToString(time, "yyyy-MM-dd HH:mm:ss");

    }




    public static String dateToString(long time, String format) {

        Date date = new Date(time);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(date);

    }


    public static String getYear() {
        Calendar cd = Calendar.getInstance();
        return String.valueOf(cd.get(Calendar.YEAR));
    }

    public static String getMonth() {
        Calendar cd = Calendar.getInstance();
        return String.valueOf(cd.get(Calendar.MONTH) + 1);
    }


    public static String getDay() {
        Calendar cd = Calendar.getInstance();
        return String.valueOf(cd.get(Calendar.DATE));
    }


    public static String getYear(long time) {
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(time);
        return String.valueOf(cd.get(Calendar.YEAR));
    }


    public static String getMonth(long time) {
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(time);
        return String.valueOf(cd.get(Calendar.MONTH) + 1);
    }


    public static String getDay(long time) {
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(time);
        return String.valueOf(cd.get(Calendar.DATE));
    }

}
