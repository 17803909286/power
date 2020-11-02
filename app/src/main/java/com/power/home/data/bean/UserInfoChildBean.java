package com.power.home.data.bean;

/**
 * Created by zhp on 2019-12-09
 */
public class UserInfoChildBean extends BaseEntity {

    /**
     * userId : 10006
     * userAssetsId : null
     * nickName : null
     * username : 18765313630
     * phone : 18765313630
     * avatar : null
     * recCode : W9YT4L
     * recedCode : null
     * isGrowth : false
     * growthDueTime : null
     * isEmba : true
     * embaDueTime : 2020-03-06 17:07:27
     * isRefer : false
     * isAgent : false
     * enabled : true
     * createTime : 2020-03-07 14:22:00
     * password : *****
     */

    private String userId;
    private String userAssetsId;
    private String nickName;
    private String username;
    private String phone;
    private String avatar;
    private String userSex;
    private String birthday;
    private String provinceCode;
    private String cityCode;
    private String area;
    private String recCode;
    private String recedCode;
    private boolean isGrowth;
    private boolean isHappinessVip;
    private String growthDueTime;
    private String happinessDueTime;
    private boolean isEmba;
    private boolean isFamilyVip;
    private boolean happinessIsExpired;
    private String embaDueTime;
    private String familyDueTime;
    private boolean familyIsExpired;
    private long freeTryDueTimestamp;
    private boolean freeTryIsExpired;
    private boolean isRefer;
    private boolean isAgent;
    private boolean enabled;
    private String isFirstRegistration;
    private String createTime;
    private String password;
    private String agentLevel;
    private String growthStartTime;
    private String embaStartTime;
    private boolean growthIsExpired;
    private boolean embaIsExpired;

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getFreeTryDueTimestamp() {
        return freeTryDueTimestamp;
    }

    public void setFreeTryDueTimestamp(long freeTryDueTimestamp) {
        this.freeTryDueTimestamp = freeTryDueTimestamp;
    }

    public boolean isFreeTryIsExpired() {
        return freeTryIsExpired;
    }

    public void setFreeTryIsExpired(boolean freeTryIsExpired) {
        this.freeTryIsExpired = freeTryIsExpired;
    }

    public boolean isHappinessVip() {
        return isHappinessVip;
    }

    public void setHappinessVip(boolean happinessVip) {
        isHappinessVip = happinessVip;
    }

    public String getHappinessDueTime() {
        return happinessDueTime;
    }

    public void setHappinessDueTime(String happinessDueTime) {
        this.happinessDueTime = happinessDueTime;
    }

    public boolean isFamilyVip() {
        return isFamilyVip;
    }

    public void setFamilyVip(boolean familyVip) {
        isFamilyVip = familyVip;
    }

    public boolean isHappinessIsExpired() {
        return happinessIsExpired;
    }

    public void setHappinessIsExpired(boolean happinessIsExpired) {
        this.happinessIsExpired = happinessIsExpired;
    }

    public String getFamilyDueTime() {
        return familyDueTime;
    }

    public void setFamilyDueTime(String familyDueTime) {
        this.familyDueTime = familyDueTime;
    }

    public boolean isFamilyIsExpired() {
        return familyIsExpired;
    }

    public void setFamilyIsExpired(boolean familyIsExpired) {
        this.familyIsExpired = familyIsExpired;
    }

    public void setIsFirstRegistration(String isFirstRegistration) {
        this.isFirstRegistration = isFirstRegistration;
    }

    public boolean isGrowthIsExpired() {
        return growthIsExpired;
    }

    public void setGrowthIsExpired(boolean growthIsExpired) {
        this.growthIsExpired = growthIsExpired;
    }

    public boolean isEmbaIsExpired() {
        return embaIsExpired;
    }

    public void setEmbaIsExpired(boolean embaIsExpired) {
        this.embaIsExpired = embaIsExpired;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getIsFirstRegistration() {
        return isFirstRegistration;
    }

    public void setFirstRegistration(String firstRegistration) {
        isFirstRegistration = firstRegistration;
    }

    public String getGrowthStartTime() {
        return growthStartTime;
    }

    public void setGrowthStartTime(String growthStartTime) {
        this.growthStartTime = growthStartTime;
    }

    public String getEmbaStartTime() {
        return embaStartTime;
    }

    public void setEmbaStartTime(String embaStartTime) {
        this.embaStartTime = embaStartTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAssetsId() {
        return userAssetsId;
    }

    public void setUserAssetsId(String userAssetsId) {
        this.userAssetsId = userAssetsId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRecedCode() {
        return recedCode;
    }

    public void setRecedCode(String recedCode) {
        this.recedCode = recedCode;
    }

    public boolean isGrowth() {
        return isGrowth;
    }

    public void setGrowth(boolean growth) {
        isGrowth = growth;
    }

    public String getGrowthDueTime() {
        return growthDueTime;
    }

    public void setGrowthDueTime(String growthDueTime) {
        this.growthDueTime = growthDueTime;
    }

    public boolean isEmba() {
        return isEmba;
    }

    public void setEmba(boolean emba) {
        isEmba = emba;
    }

    public String getEmbaDueTime() {
        return embaDueTime;
    }

    public void setEmbaDueTime(String embaDueTime) {
        this.embaDueTime = embaDueTime;
    }

    public boolean isRefer() {
        return isRefer;
    }

    public void setRefer(boolean refer) {
        isRefer = refer;
    }

    public boolean isAgent() {
        return isAgent;
    }

    public void setAgent(boolean agent) {
        isAgent = agent;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
