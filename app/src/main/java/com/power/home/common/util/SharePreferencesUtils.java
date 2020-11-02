package com.power.home.common.util;

import com.blankj.utilcode.util.SPUtils;
import com.power.home.common.Constant;

/**
 * 封装的SP 轻量级存储
 */

public class SharePreferencesUtils {
    public static void saveToken(String token) {
        SPUtils.getInstance(Constant.userParts).put("token", token);
    }

    public static String getToken() {
        return SPUtils.getInstance(Constant.userParts).getString("token");
    }

    public static void setUploadToken(String token){
        SPUtils.getInstance(Constant.userParts).put("uploadToken",token);
    }
    public static void setUploadImageBaseUrl(String imageUrl){
        SPUtils.getInstance(Constant.userParts).put("uploadImageBaseUrl",imageUrl);
    }
    public static String getUploadBaseUrl(){
        return SPUtils.getInstance(Constant.userParts).getString("uploadImageBaseUrl");
    }
    public static String getUploadToken(){
      return   SPUtils.getInstance(Constant.userParts).getString("uploadToken");
    }
    public static void saveIsGrowth(boolean isGrowth) {
        SPUtils.getInstance(Constant.userParts).put("isGrowth", isGrowth);
    }

    public static boolean getIsGrowth() {
        return SPUtils.getInstance(Constant.userParts).getBoolean("isGrowth", false);
    }

    public static void saveGrowthIsExpired(boolean growthIsExpired) {
        SPUtils.getInstance(Constant.userParts).put("growthIsExpired", growthIsExpired);
    }

    public static boolean getGrowthIsExpired() {
        return SPUtils.getInstance(Constant.userParts).getBoolean("growthIsExpired", false);
    }

    public static void saveIsEmba(boolean isEmba) {
        SPUtils.getInstance(Constant.userParts).put("isEmba", isEmba);
    }

    public static boolean getIsEmba() {
        return SPUtils.getInstance(Constant.userParts).getBoolean("isEmba", false);
    }

    public static void saveEmbaIsExpired(boolean embaIsExpired) {
        SPUtils.getInstance(Constant.userParts).put("embaIsExpired", embaIsExpired);
    }

    public static boolean getEmbaIsExpired() {
        return SPUtils.getInstance(Constant.userParts).getBoolean("embaIsExpired", false);
    }

    public static void saveSearchKey(String searchKey) {
        SPUtils.getInstance(Constant.userParts).put("searchKey", searchKey);
    }

    public static String getSearchKey() {
        return SPUtils.getInstance(Constant.userParts).getString("searchKey");
    }

    public static void saveProductName(String productName) {
        SPUtils.getInstance(Constant.userParts).put("productName", productName);
    }

    public static String getProductName() {
        return SPUtils.getInstance(Constant.userParts).getString("productName");
    }

    public static void saveUserId(String userId) {
        SPUtils.getInstance(Constant.userParts).put("userId", userId);
    }

    public static String getUserId() {
        return SPUtils.getInstance(Constant.userParts).getString("userId");
    }

    public static void savePhone(String phone) {
        SPUtils.getInstance(Constant.userParts).put("phone", phone);
    }

    public static String getPhone() {
        return SPUtils.getInstance(Constant.userParts).getString("phone");
    }

    public static void saveUserName(String userName) {
        SPUtils.getInstance(Constant.userParts).put("userName", userName);
    }

    public static String getUserName() {
        return SPUtils.getInstance(Constant.userParts).getString("userName");
    }

    public static void saveNickName(String nickName) {
        SPUtils.getInstance(Constant.userParts).put("nickName", nickName);
    }

    public static String getNickName() {
        return SPUtils.getInstance(Constant.userParts).getString("nickName");
    }

    public static void saveAvatar(String avatar) {
        SPUtils.getInstance(Constant.userParts).put("avatar", StringUtil.isNotNullString(avatar) ? avatar : "");
    }

    public static String getAvatar() {
        return SPUtils.getInstance(Constant.userParts).getString("avatar");
    }

    public static void saveFirst(boolean firstIn) {
        SPUtils.getInstance(Constant.firstIn).put(Constant.firstIn, firstIn);
    }

    public static boolean isFirst() {
        return SPUtils.getInstance(Constant.firstIn).getBoolean(Constant.firstIn);
    }

    public static void saveFloatStatus(String floatFlag) {
        SPUtils.getInstance(Constant.floatFlag).put("floatFlag", floatFlag);
    }

    public static String getFloatStatus() {
        return SPUtils.getInstance(Constant.floatFlag).getString("floatFlag");
    }

    public static void hideFloat(String hideFloat) {
        SPUtils.getInstance(Constant.hideFloat).put("hideFloat", hideFloat);
    }

    public static String getHideFloat() {
        return SPUtils.getInstance(Constant.hideFloat).getString("hideFloat");
    }

    public static void clearUserParts() {
        SPUtils.getInstance(Constant.userParts).clear();
    }

    public static void clearFloatFlag() {
        SPUtils.getInstance(Constant.floatFlag).clear();
    }


}
