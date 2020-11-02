package com.power.home.data.bean;

/**
 * Created by ZHP on 2019/3/26/0026.
 */

public class LoginBean extends BaseEntity{


    /**
     * authLevel : LEVEL0
     * brokerId : 10003
     * fullName : null
     * mobile : 13369851234
     * nickName : null
     * token : eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJNeUMyQ1dlYiIsInN1YiI6IjQwNiIsImF1ZCI6IndlYiIsImlhdCI6MTU1MzY3Mjg2NCwiZXhwIjoxNTUzNjk0NDY0fQ.Itlz1vlTzPAUKRtB2cLW7yqYxtAm2iBUrVdNVCk_erJITEtaX70DDxjaS7eEV6DXn_VwIEbbmeebb_Ri02vWdg
     * uid : 406
     * userAccount : 13369851234
     */

    private String authLevel;
    private String brokerId;
    private String fullName;
    private String mobile;
    private String nickName;
    private String token;
    private String uid;
    private String userAccount;



    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
