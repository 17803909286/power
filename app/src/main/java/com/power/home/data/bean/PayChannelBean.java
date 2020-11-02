package com.power.home.data.bean;

/**
 * Created by XWL on 2020/3/28.
 * Description:
 */
public class PayChannelBean {

    /**
     * id : 1
     * channelName : 微信
     * channelCode : WX
     * channelLogo : http://www.test.image.future.sdgyjyzx.com/%E5%9B%BE%E6%A0%87%EF%BC%8F%E5%85%B6%E4%BB%96%EF%BC%8F%E6%94%AF%E4%BB%98%E6%96%B9%E5%BC%8F%EF%BC%8F%E5%BE%AE%E4%BF%A1%403x.png
     * payPlatform : Android
     * state : true
     * remark : null
     */

    private String id;
    private String channelName;
    private String channelCode;
    private String channelLogo;
    private String payPlatform;
    private boolean state;
    private String remark;
    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }

    public String getPayPlatform() {
        return payPlatform;
    }

    public void setPayPlatform(String payPlatform) {
        this.payPlatform = payPlatform;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
