package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/7/21 0021.
 */
public class BankInfoBean extends BaseEntity{


    /**
     * createTime : 2020-07-22 06:27:58
     * updateTime : 2020-07-22 06:27:58
     * bankId : 14
     * accountName : 张彭
     * bankName : 建行
     * openBankName : null
     * bankCardNum : 6217001210054481963
     * isWithdraw : true
     * authId : 10000
     * revision : null
     * createBy : null
     * updateBy : null
     */

    private String createTime;
    private String updateTime;
    private String bankId;
    private String accountName;
    private String bankName;
    private Object openBankName;
    private String bankCardNum;
    private boolean isWithdraw;
    private int authId;
    private Object revision;
    private Object createBy;
    private Object updateBy;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Object getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(Object openBankName) {
        this.openBankName = openBankName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public boolean isIsWithdraw() {
        return isWithdraw;
    }

    public void setIsWithdraw(boolean isWithdraw) {
        this.isWithdraw = isWithdraw;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public Object getRevision() {
        return revision;
    }

    public void setRevision(Object revision) {
        this.revision = revision;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }
}
