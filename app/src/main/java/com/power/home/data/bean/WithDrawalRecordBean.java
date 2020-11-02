package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/2/26 0026.
 */
public class WithDrawalRecordBean extends BaseEntity {


    /**
     * id : 1227
     * accountName : 张彭
     * bankName : 工商银行
     * bankCardNum : 6212261602007112
     * applyAmount : 100
     * applyTime : 2020-03-19 14:25:42
     * auditStatus : 正在审核
     * auditRemark : null
     * makeMoneyStatus : null
     */

    private String id;
    private String accountName;
    private String bankName;
    private String bankCardNum;
    private String applyAmount;
    private String applyTime;
    private String auditStatus;
    private String auditRemark;
    private String makeMoneyStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(String applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getMakeMoneyStatus() {
        return makeMoneyStatus;
    }

    public void setMakeMoneyStatus(String makeMoneyStatus) {
        this.makeMoneyStatus = makeMoneyStatus;
    }
}
