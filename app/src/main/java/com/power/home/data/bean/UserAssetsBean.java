package com.power.home.data.bean;

/**
 * Created by zhp on 2020-03-10
 */
public class UserAssetsBean extends BaseEntity{


    /**
     * userAssetsId : 20002
     * userId : null
     * agencyIncome : 100
     * convertAmount : 50
     * accountBalance : 5
     * surplusQuota : null
     * usedQuota : null
     */

    private String userAssetsId;
    private String userId;
    private String agencyIncome;
    private String convertAmount;
    private String accountBalance;
    private String surplusQuota;
    private String usedQuota;
    private String shareImg;
    private String shareUrl;
    private String posterImg;
    private String shareTitle;
    private String shareSubtitle;
    private boolean isAuth;
    private boolean hasBankInfo;

    public boolean isHasBankInfo() {
        return hasBankInfo;
    }

    public void setHasBankInfo(boolean hasBankInfo) {
        this.hasBankInfo = hasBankInfo;
    }

    public boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public void setPosterImg(String posterImg) {
        this.posterImg = posterImg;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareSubtitle() {
        return shareSubtitle;
    }

    public void setShareSubtitle(String shareSubtitle) {
        this.shareSubtitle = shareSubtitle;
    }

    public String getUserAssetsId() {
        return userAssetsId;
    }

    public void setUserAssetsId(String userAssetsId) {
        this.userAssetsId = userAssetsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAgencyIncome() {
        return agencyIncome;
    }

    public void setAgencyIncome(String agencyIncome) {
        this.agencyIncome = agencyIncome;
    }

    public String getConvertAmount() {
        return convertAmount;
    }

    public void setConvertAmount(String convertAmount) {
        this.convertAmount = convertAmount;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(String surplusQuota) {
        this.surplusQuota = surplusQuota;
    }

    public String getUsedQuota() {
        return usedQuota;
    }

    public void setUsedQuota(String usedQuota) {
        this.usedQuota = usedQuota;
    }
}
