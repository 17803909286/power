package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/7 0007.
 */
public class DailyBusinessBean extends BaseEntity{

    /**
     * areaName : 免费体验
     * contents : [{"isDelete":false,"createTime":"2020-02-24 15:01:02","updateTime":"2020-02-24 15:01:05","id":10014,"title":"免费体验课程1","subtitle":"免费体验课程1","displayPic":"/course/20200115-113437.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10006","displayCount":1000,"currentPrice":"88.00","sort":1},{"isDelete":false,"createTime":"2020-02-24 15:02:35","updateTime":"2020-02-24 15:02:38","id":10015,"title":"免费体验课程2","subtitle":"免费体验课程2","displayPic":"/course/20200116-102001.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10007","displayCount":1000,"currentPrice":"88.00","sort":2},{"isDelete":false,"createTime":"2020-02-24 15:03:28","updateTime":"2020-02-24 15:03:30","id":10016,"title":"免费体验课程3","subtitle":"免费体验课程3","displayPic":"/course/20200116-102930.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10008","displayCount":1000,"currentPrice":"88.00","sort":3},{"isDelete":false,"createTime":"2020-02-24 15:04:19","updateTime":"2020-02-24 15:04:21","id":10017,"title":"免费体验课程4","subtitle":"免费体验课程4","displayPic":"/course/20200116-103848.png","type":2,"userType":null,"forwardType":1,"forwardAddress":"/app/course/findCourseById/10009","displayCount":1000,"currentPrice":"88.00","sort":4}]
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

    public static class ContentsBean {
        /**
         * isDelete : false
         * createTime : 2020-02-24 15:01:02
         * updateTime : 2020-02-24 15:01:05
         * id : 10014
         * title : 免费体验课程1
         * subtitle : 免费体验课程1
         * displayPic : /course/20200115-113437.png
         * type : 2
         * userType : null
         * forwardType : 1
         * forwardAddress : /app/course/findCourseById/10006
         * displayCount : 1000
         * currentPrice : 88.00
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
        private int courseTime;
        private String courseSize;
        private String belongsTo;
        private boolean isNeedLogin;

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        public boolean isNeedLogin() {
            return isNeedLogin;
        }

        public void setNeedLogin(boolean needLogin) {
            isNeedLogin = needLogin;
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

        public int getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(int courseTime) {
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

        public boolean isIsNeedLogin() {
            return isNeedLogin;
        }

        public void setIsNeedLogin(boolean isNeedLogin) {
            this.isNeedLogin = isNeedLogin;
        }
    }
}
