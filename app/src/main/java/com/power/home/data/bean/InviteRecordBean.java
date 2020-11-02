package com.power.home.data.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhp on 2020-03-09
 */
public class InviteRecordBean extends BaseEntity{

    /**
     * nickName : null
     * phone : 18765313630
     * avatar : null
     * recCode : W9YT4L
     * vipOrderNum : 0
     * inviteRegister : 0
     */

    private String nickName;
    private String phone;
    private String avatar;
    private String recCode;
    private String vipOrderNum;
    private String inviteRegister;
    private String surplusQuota;
    /**
     * buyVipCount : 0
     * invitedRegistrationCount : 0
     * mobileNumber : 13522418685
     * userId : 10437
     * avatar : null
     */

    private String buyVipCount;
    private String invitedRegistrationCount;
    private String mobileNumber;
    private int userId;

    public String getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(String surplusQuota) {
        this.surplusQuota = surplusQuota;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public String getVipOrderNum() {
        return vipOrderNum;
    }

    public void setVipOrderNum(String vipOrderNum) {
        this.vipOrderNum = vipOrderNum;
    }

    public String getInviteRegister() {
        return inviteRegister;
    }

    public void setInviteRegister(String inviteRegister) {
        this.inviteRegister = inviteRegister;
    }

    public String getBuyVipCount() {
        return buyVipCount;
    }

    public void setBuyVipCount(String buyVipCount) {
        this.buyVipCount = buyVipCount;
    }

    public String getInvitedRegistrationCount() {
        return invitedRegistrationCount;
    }

    public void setInvitedRegistrationCount(String invitedRegistrationCount) {
        this.invitedRegistrationCount = invitedRegistrationCount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
