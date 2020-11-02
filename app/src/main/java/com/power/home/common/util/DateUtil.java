package com.power.home.common.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZHP on 2017/7/25.
 */

public class DateUtil {


    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";


    public static String DATE_TO_STRING_DETAIAL_PATTERN3 = "yyyyMMddHHmmss";


    public static String DATE_TO_STRING_DETAIAL_PATTERN2 = "yyyy-MM-dd HH:mm";


    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

    public static String DATE_TO_STRING_SHORT_UNPATTERN = "yyyyMMdd";


    public static String DATE_TO_STRING_TIME_PATTERN = "HH:mm:ss";

    public static String DATE_TO_STRING_PATTERN = "HH:mm";


    private static SimpleDateFormat simpleDateFormat;


    public static String DateToString(Date source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }


    public static String timeStampToString(long source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source * 1000);
        return simpleDateFormat.format(date);
    }


    public static long dateToTimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime() / 1000;

    }

    public static long secretDateToTimeStamp(long timeStamp) {
        long time1 = timeStamp >> 17;
        long time2 = timeStamp & 0x1FFFF;
        long time = time1 * 60 * 60 * 24 + time2 - 60 * 60 * 8;

        return time;

    }


    public static Date stringToDate(String source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (ParseException e) {

        }
        return date;
    }


    public static String currentFormatDate(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }


    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00:00";
        } else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = "00:" + unitFotmat(minute) + ":" + unitFotmat(second);
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFotmat(hour) + ":" + unitFotmat(minute) + ":" + unitFotmat(second);
            }
        }
        return timeStr;
    }

    private static String unitFotmat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10) {
            retStr = "0" + Integer.toString(i);
        } else {
            retStr = "" + i;
        }
        return retStr;
    }


    public static List<String> getHistoryTime(int level) {

        List<String> dateParams = new ArrayList<>();

        long currentThreadTimeMillis = System.currentTimeMillis() / 1000;
        String today = DateUtil.timeStampToString(currentThreadTimeMillis, DateUtil.DATE_TO_STRING_SHORT_PATTERN);
        String todayStr = DateUtil.timeStampToString(currentThreadTimeMillis, DateUtil.DATE_TO_STRING_DETAIAL_PATTERN);
        switch (level) {
            case 0:
                String nineStr0 = today + " 9:00:00";
                dateParams.add(nineStr0);
                dateParams.add(todayStr);
                break;
            case 1:
                String nineStr = today + " 21:00:00";
                long nineTimeMillis = DateUtil.stringToDate(nineStr, DateUtil.DATE_TO_STRING_DETAIAL_PATTERN).getTime() / 1000;
                if (currentThreadTimeMillis > nineTimeMillis) {
                    dateParams.add(nineStr);
                    dateParams.add(todayStr);
                } else {
                    long yesNineTimeMillis = (nineTimeMillis - 24 * 60 * 60);
                    String yesNineStr = DateUtil.timeStampToString(yesNineTimeMillis, DateUtil.DATE_TO_STRING_DETAIAL_PATTERN);
                    dateParams.add(yesNineStr);
                    dateParams.add(todayStr);
                }
                break;
            case 2:
            case 3:
                String beginStr = DateUtil.timeStampToString(currentThreadTimeMillis - 24 * 60 * 60 * 3, DateUtil.DATE_TO_STRING_DETAIAL_PATTERN);
                dateParams.add(beginStr);
                dateParams.add(todayStr);
                break;
            case 4:
                String beginStr2 = DateUtil.timeStampToString(currentThreadTimeMillis - 24 * 60 * 60 * 10, DateUtil.DATE_TO_STRING_DETAIAL_PATTERN);
                dateParams.add(beginStr2);
                dateParams.add(todayStr);
                break;
            case 5:
                String beginStr3 = DateUtil.timeStampToString(currentThreadTimeMillis - 24 * 60 * 60 * 20, DateUtil.DATE_TO_STRING_DETAIAL_PATTERN);
                dateParams.add(beginStr3);
                dateParams.add(todayStr);
                break;
            case 6:
                String beginStr4 = DateUtil.timeStampToString(currentThreadTimeMillis - 24 * 60 * 60 * 60, DateUtil.DATE_TO_STRING_DETAIAL_PATTERN);
                dateParams.add(beginStr4);
                dateParams.add(todayStr);
                break;
        }

        return dateParams;
    }
}
