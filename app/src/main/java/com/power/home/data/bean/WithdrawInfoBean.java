package com.power.home.data.bean;

/**
 * Created by zhp on 2020-03-10
 */
public class WithdrawInfoBean extends BaseEntity{


    /**
     * userId : 10010
     * bankId : 7
     * accountName : 张彭
     * bankName : 工商银行
     * openBankName : 高新支行
     * bankCardNum : 6212261602007112
     * accountBalance : 500
     * withdrawTip : 100
     * withdrawShow : 说明：每月1日到5日为提现日，其余时间暂不支持提现。
     */

    private String userId;
    private String bankId;
    private String accountName;
    private String bankName;
    private String openBankName;
    private String bankCardNum;
    private String accountBalance;
    private String withdrawTip;
    private String withdrawShow;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getOpenBankName() {
        return openBankName;
    }

    public void setOpenBankName(String openBankName) {
        this.openBankName = openBankName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getWithdrawTip() {
        return withdrawTip;
    }

    public void setWithdrawTip(String withdrawTip) {
        this.withdrawTip = withdrawTip;
    }

    public String getWithdrawShow() {
        return withdrawShow;
    }

    public void setWithdrawShow(String withdrawShow) {
        this.withdrawShow = withdrawShow;
    }
}
