package com.power.home.common;


import com.baidu.mobstat.Build;
import com.power.home.BuildConfig;

/**
 * Created by ZHP on 2017/6/24.
 */

public class Constant {

    public static boolean IS_RELEASE = false;

//    public static String BASE_URL = IS_RELEASE ? "http://www.app.yshili.com" : "http://192.168.0.111:8181";
     public static String BASE_URL = BuildConfig.HOST_URL;

//    public static String BASE_URL = IS_RELEASE ? "http://192.168.0.117:8181" : "http://192.168.0.117:8181";
    public static final int alias = 0x111115;
    public static final int TIME_AUTO_POLL = 3 * 1000;
    public static final long ANIM_DELAYED_MILLIONS = 3 * 1000;
    public static final int TAB_HOME = 0;
    public static final int TAB_FIND = 1;
    public static final int TAB_MINE = 2;
    public static final String APP_ID = "wxd3e4a2ff39126f68";

    public static final String userParts = "userParts";
    public static final String permission = "close";
    public static final String id = "id";
    public static final String main = "page";
    public static final String web = "url";
    public static final String web_title = "web_title";
    public static final String firstIn = "first";
    public static final String position = "position";
    public static final String type = "type";
    public static final String data = "data";
    public static final String from = "from";
    public static final String title = "title";
    public static final String object = "object";
    public static final String user = "user";
    public static final String phone = "phone";
    public static final String money = "money";
    public static final String userId = "userId";
    public static final String commodity = "commodity";
    public static final String url = "url";
    public static final String inviteUrl = "inviteUrl";
    public static final String inviteFrontName = "inviteFrontName";
    public static final String inviteBehindName = "inviteBehindName";
    public static final String courseName = "courseName";
    public static final String floatFlag = "open";
    public static final String floatStatus = "floatStatus";
    public static final String seek = "seek";
    public static final String service_data = "service_data";
    public static final String hideFloat = "hideFloat";
    public static final String notify = "notify";
}