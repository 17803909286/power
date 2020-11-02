package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/7 0007.
 */
public class MainGridBean extends BaseEntity{

    /**
     * areaName : 宫格区
     * contents : [{"isDelete":false,"createTime":"2020-02-24 14:48:37","updateTime":"2020-02-24 14:48:40","id":10005,"title":"全部课程","subtitle":"全部课程","displayPic":"/icon/all_course.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":1},{"isDelete":false,"createTime":"2020-02-24 14:49:30","updateTime":"2020-02-24 14:49:32","id":10006,"title":"动力营","subtitle":"动力营","displayPic":"/icon/company_growing.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":2},{"isDelete":false,"createTime":"2020-02-24 14:50:43","updateTime":"2020-02-24 14:50:48","id":10007,"title":"在线EMBA","subtitle":"在线EMBA","displayPic":"/icon/online_emba.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":3},{"isDelete":false,"createTime":"2020-02-24 14:52:01","updateTime":"2020-02-24 14:52:03","id":10008,"title":"线下活动","subtitle":"线下活动","displayPic":"/icon/offline_activity.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":4}]
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
         * createTime : 2020-02-24 14:48:37
         * updateTime : 2020-02-24 14:48:40
         * id : 10005
         * title : 全部课程
         * subtitle : 全部课程
         * displayPic : /icon/all_course.png
         * type : null
         * userType : null
         * forwardType : 1
         * forwardAddress : 暂时没有地址
         * displayCount : 0
         * currentPrice : 0.00
         * sort : 1
         */

        private boolean isDelete;
        private String createTime;
        private String updateTime;
        private int id;
        private String title;
        private String subtitle;
        private String displayPic;
        private int type;
        private String userType;
        private String forwardType;
        private String forwardAddress;
        private int displayCount;
        private String currentPrice;
        private int sort;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public boolean isDelete() {
            return isDelete;
        }

        public void setDelete(boolean delete) {
            isDelete = delete;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
