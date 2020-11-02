package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/7 0007.
 */
public class FreeExperienceBean extends BaseEntity{

    /**
     * areaName : 每日商道
     * contents : [{"isDelete":false,"createTime":"2020-02-24 14:55:19","updateTime":"2020-02-24 14:55:22","id":10010,"title":"合伙人","subtitle":"合伙人密训","displayPic":"/course/20200115-095556.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10000","displayCount":1000,"currentPrice":"99.00","sort":1},{"isDelete":false,"createTime":"2020-02-24 14:57:41","updateTime":"2020-02-24 14:57:43","id":10011,"title":"曾经的时间，塑造了我们现在的样子","subtitle":"现在的时间，正在创造你未来的样子","displayPic":"/course/20200115-105050.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10001","displayCount":1000,"currentPrice":"99.00","sort":2},{"isDelete":false,"createTime":"2020-02-24 14:58:39","updateTime":"2020-02-24 14:58:42","id":10012,"title":"时间管理","subtitle":"时间管理","displayPic":"/course/20200115-110053.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10002","displayCount":1000,"currentPrice":"99.00","sort":3},{"isDelete":false,"createTime":"2020-02-24 14:59:36","updateTime":"2020-02-24 14:59:38","id":10013,"title":"你在人生中的迷茫与混乱","subtitle":"每日商道课程4","displayPic":"/course/20200115-112405.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10003","displayCount":1000,"currentPrice":"99.00","sort":4}]
     */

    private String areaName;
    private List<ContentsBean> contents;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public static class ContentsBean extends BaseEntity{
        /**
         * isDelete : false
         * createTime : 2020-02-24 14:55:19
         * updateTime : 2020-02-24 14:55:22
         * id : 10010
         * title : 合伙人
         * subtitle : 合伙人密训
         * displayPic : /course/20200115-095556.png
         * type : 2
         * userType : null
         * forwardType : 1
         * forwardAddress : /app/course/findCourseById/10000
         * displayCount : 1000
         * currentPrice : 99.00
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
        private String courseSize;
        private String courseTotalSize;
        private int courseTime;
        private boolean continueUpdating;
        private int sort;

        public int getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(int courseTime) {
            this.courseTime = courseTime;
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

        public String getCourseSize() {
            return courseSize;
        }

        public void setCourseSize(String courseSize) {
            this.courseSize = courseSize;
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
}
