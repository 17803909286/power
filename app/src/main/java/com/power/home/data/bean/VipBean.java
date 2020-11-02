package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/4/28 0028.
 */
public class VipBean extends BaseEntity {


    /**
     * growingVipExpired : false
     * growingPrice : 0.01
     * growingVipRightsIntroduce : null
     * growingCoverImg : null
     * excellentCourseTopics : [{"id":10027,"title":"传统行业如何通过新的交易结构，让企业利润翻10倍？","subtitle":"具体怎么做到？本节课程将为你一一剖析","moduleId":10006,"price":"0.01","displayPic":"http://www.test.image.wlfxdata.com/20200115-113437.png","isBuy":true,"description":null,"topicCover":null,"displayCount":5002,"type":1,"classification":3,"belongsTo":"GROWING","free":false},{"id":10022,"title":"如何把2000万的成本投资，在营业前收回来？","subtitle":"如何\u201c不花1分钱\u201d开1000家连锁店","moduleId":10005,"price":"0.01","displayPic":"http://www.test.image.wlfxdata.com/20200116-102930.png","isBuy":true,"description":null,"topicCover":null,"displayCount":5026,"type":1,"classification":3,"belongsTo":"GROWING","free":false},{"id":10012,"title":"如何通过互联网方式，让传统行业快速找到利润的出路？","subtitle":"不推销，不打价格战，让顾客源源不断主动上门？","moduleId":10003,"price":"0.01","displayPic":"http://www.test.image.wlfxdata.com/20200117-144618.png","isBuy":true,"description":null,"topicCover":null,"displayCount":7051,"type":1,"classification":3,"belongsTo":"GROWING","free":false}]
     * growingVip : false
     * growingFlag: false 会员身份标志
     */

    private boolean growingVipExpired;
    private String growingPrice;
    private String growingVipRightsIntroduce;
    private String growingCoverImg;
    private String growingExpiredTime;
    private boolean growingVip;
    private boolean growingFlag;
    private List<CourseTopicsBean> excellentCourseTopics;

    public boolean isGrowingFlag() {
        return growingFlag;
    }

    public void setGrowingFlag(boolean growingFlag) {
        this.growingFlag = growingFlag;
    }

    public String getGrowingExpiredTime() {
        return growingExpiredTime;
    }

    public void setGrowingExpiredTime(String growingExpiredTime) {
        this.growingExpiredTime = growingExpiredTime;
    }

    public boolean isGrowingVipExpired() {
        return growingVipExpired;
    }

    public void setGrowingVipExpired(boolean growingVipExpired) {
        this.growingVipExpired = growingVipExpired;
    }

    public String getGrowingPrice() {
        return growingPrice;
    }

    public void setGrowingPrice(String growingPrice) {
        this.growingPrice = growingPrice;
    }

    public String getGrowingVipRightsIntroduce() {
        return growingVipRightsIntroduce;
    }

    public void setGrowingVipRightsIntroduce(String growingVipRightsIntroduce) {
        this.growingVipRightsIntroduce = growingVipRightsIntroduce;
    }

    public String getGrowingCoverImg() {
        return growingCoverImg;
    }

    public void setGrowingCoverImg(String growingCoverImg) {
        this.growingCoverImg = growingCoverImg;
    }

    public boolean isGrowingVip() {
        return growingVip;
    }

    public void setGrowingVip(boolean growingVip) {
        this.growingVip = growingVip;
    }

    public List<CourseTopicsBean> getExcellentCourseTopics() {
        return excellentCourseTopics;
    }

    public void setExcellentCourseTopics(List<CourseTopicsBean> excellentCourseTopics) {
        this.excellentCourseTopics = excellentCourseTopics;
    }
}
