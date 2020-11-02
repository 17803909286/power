package com.power.home.data.bean;

/**
 * Created by zhp on 2019-12-09
 */
public class UserInfoBean extends BaseEntity {


    /**
     * user : {"userId":10006,"userAssetsId":null,"nickName":null,"username":"18765313630","phone":"18765313630","avatar":null,"recCode":"W9YT4L","recedCode":null,"isGrowth":false,"growthStartTime":null,"growthDueTime":null,"isEmba":true,"embaStartTime":null,"embaDueTime":"2020-03-06 17:07:27","isRefer":false,"isAgent":false,"enabled":true,"createTime":"2020-03-07 14:22:00"}
     * token : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODc2NTMxMzYzMCIsImlhdCI6MTU4MzU3Mjg5MCwiZXhwIjoxNTg0ODY4ODkwfQ.6psVEfqoPlrm5x8UeUiYIcYtPRPoFGc42c3F0joa6cpB6O5AuGCu-MpD6C8Y0MGoy93I18mLUSV-IDoxpkg6Ww
     */

    private UserInfoChildBean user;
    private String token;

    public UserInfoChildBean getUser() {
        return user;
    }

    public void setUser(UserInfoChildBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
