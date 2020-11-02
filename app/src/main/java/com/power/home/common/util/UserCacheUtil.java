package com.power.home.common.util;

import com.power.home.common.Constant;
import com.power.home.common.bus.EventBusEvent;
import com.power.home.common.bus.EventBusUtils;
import com.power.home.data.bean.UserInfoChildBean;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ZHP on 2018/12/28/0028.
 */

public class UserCacheUtil {

    public static void saveInfo(UserInfoChildBean userInfoChildBean) {

        JPushInterface.setAlias(UIUtil.getContext(), Constant.alias, userInfoChildBean.getUserId());
        SharePreferencesUtils.saveUserId(userInfoChildBean.getUserId());
        SharePreferencesUtils.savePhone(userInfoChildBean.getPhone());
        SharePreferencesUtils.saveUserName(userInfoChildBean.getUsername());
        SharePreferencesUtils.saveNickName(userInfoChildBean.getNickName());
        SharePreferencesUtils.saveAvatar(userInfoChildBean.getAvatar());
        SharePreferencesUtils.saveIsEmba(userInfoChildBean.isEmba());
        SharePreferencesUtils.saveEmbaIsExpired(userInfoChildBean.isEmbaIsExpired());
        SharePreferencesUtils.saveGrowthIsExpired(userInfoChildBean.isGrowthIsExpired());
        ACache.get(UIUtil.getContext()).put(Constant.user,userInfoChildBean);
    }


    public static boolean isLogin() {
        String token = SharePreferencesUtils.getToken();
        return (StringUtil.isNotNullString(token));
    }


    public static void loginOut() {
        JPushInterface.deleteAlias(UIUtil.getContext(), Constant.alias);
        SharePreferencesUtils.clearUserParts();
        SharePreferencesUtils.clearFloatFlag();
        ACache.get(UIUtil.getContext()).clear();
    }


}
