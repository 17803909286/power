package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/7/21 0021.
 */
public class EveryDayBean extends BaseEntity{


    /**
     * id : 1
     * activityTitle : 每日分享得红包
     * activityContent : 每日红包量
     * activityClassify : REDPACKAGE
     * activityType : 2
     * finishValue : null
     * targetValue : 10
     */

    private String id;
    private String activityTitle;
    private String activityContent;
    private String activityClassify;
    private int activityType;
    private int finishValue;
    private int targetValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityClassify() {
        return activityClassify;
    }

    public void setActivityClassify(String activityClassify) {
        this.activityClassify = activityClassify;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public int getFinishValue() {
        return finishValue;
    }

    public void setFinishValue(int finishValue) {
        this.finishValue = finishValue;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }
}
