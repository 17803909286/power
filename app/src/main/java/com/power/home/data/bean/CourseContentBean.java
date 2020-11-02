package com.power.home.data.bean;

import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.common.util.StringUtil;
import com.power.home.common.util.UserCacheUtil;

/**
 * Created by ZHP on 2020/4/28 0028.
 */
public class CourseContentBean extends BaseEntity {
    /**
     * isDelete : false
     * createTime : 2020-02-24 15:08:07
     * updateTime : 2020-02-24 15:08:09
     * id : 10018
     * title : 企业文化课程1
     * subtitle : 企业文化课程1
     * displayPic : /course/20200116-110549.png
     * type : 1
     * userType : null
     * forwardType : 1
     * forwardAddress : /app/courseTopic/findCourseTopicById/10018
     * displayCount : 5000
     * currentPrice : 59.00
     * sort : 1
     */

    private boolean isDelete;
    private String createTime;
    private String updateTime;
    private String id;
    private String title;
    private String subtitle;
    private String displayPic;
    private String type;
    private String userType;
    private String forwardType;
    private String forwardAddress;
    private int displayCount;
    private String currentPrice;
    private int sort;
    private boolean isBuy;
    private boolean isFree;
    private String courseTime;
    private String courseSize;
    private String courseTotalSize;
    private boolean continueUpdating;
    private String belongsTo;
    private String isNeedLogin;
    private boolean isDiscount;
    private String originalPrice;
    private boolean vipFlag;

    public boolean isVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(boolean vipFlag) {
        this.vipFlag = vipFlag;
    }

    public String getCourseTotalSize() {
        return courseTotalSize;
    }

    public void setCourseTotalSize(String courseTotalSize) {
        this.courseTotalSize = courseTotalSize;
    }

    public boolean isContinueUpdating() {
        return continueUpdating;
    }

    public void setContinueUpdating(boolean continueUpdating) {
        this.continueUpdating = continueUpdating;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseSize() {
        return courseSize;
    }

    public void setCourseSize(String courseSize) {
        this.courseSize = courseSize;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public String getIsNeedLogin() {
        return isNeedLogin;
    }

    public void setIsNeedLogin(String isNeedLogin) {
        this.isNeedLogin = isNeedLogin;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getForwardType() {
        return forwardType;
    }

    public void setForwardType(String forwardType) {
        this.forwardType = forwardType;
    }

    public String getForwardAddress() {
        return forwardAddress;
    }

    public void setForwardAddress(String forwardAddress) {
        this.forwardAddress = forwardAddress;
    }

    public int getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(int displayCount) {
        this.displayCount = displayCount;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

}
