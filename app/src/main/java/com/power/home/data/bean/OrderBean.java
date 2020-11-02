package com.power.home.data.bean;

/**
 * Created by zhp on 2019-11-20
 */
public class OrderBean extends BaseEntity {


    /**
     * orderNum : 2020115
     * orderType : SPECIAL
     * towTopicId : 10007
     * title : 商业模式专题1
     * subtitle : 商业模式副标题专题1
     * displayCount : 5000
     * displayPic : null
     * orderTime : 2020-02-20T17:15:32.000+0800
     */

    private String orderNum;
    private String orderType;
    private String towTopicId;
    private String title;
    private String subtitle;
    private String displayCount;
    private String displayPic;
    private String orderTime;
    private String topicEndTime;
    private String topicPrice;
    private boolean topicExpire;
    private String belongsTo;
    private int courseSize;

    public String getTopicPrice() {
        return topicPrice;
    }

    public void setTopicPrice(String topicPrice) {
        this.topicPrice = topicPrice;
    }

    public boolean getTopicExpire() {
        return topicExpire;
    }

    public void setTopicExpire(boolean topicExpire) {
        this.topicExpire = topicExpire;
    }


    public String getTopicEndTime() {
        return topicEndTime;
    }

    public void setTopicEndTime(String topicEndTime) {
        this.topicEndTime = topicEndTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTowTopicId() {
        return towTopicId;
    }

    public void setTowTopicId(String towTopicId) {
        this.towTopicId = towTopicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(String displayCount) {
        this.displayCount = displayCount;
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public int getCourseSize() {
        return courseSize;
    }

    public void setCourseSize(int courseSize) {
        this.courseSize = courseSize;
    }
}
