package com.power.home.data.bean;

/**
 * Created by XWL on 2020/3/18.
 * Description:
 */
public class PayOrderBean {

    /**
     * sign : 07EA5BF206357191D870805FAC74E1E9
     * prepayId : wx2818130863819400a77342bb1238416400
     * partnerId : 1581511231
     * appId : wx5d0ae1c81a88a94c
     * packageValue : Sign=WXPay
     * timeStamp : 1585390388
     * nonceStr : 1585390388894
     */

    private String sign;
    private String prepayId;
    private String partnerId;
    private String appId;
    private String packageValue;
    private String timeStamp;
    private String nonceStr;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
}
