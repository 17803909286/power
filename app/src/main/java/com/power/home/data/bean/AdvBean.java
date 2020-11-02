package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/7 0007.
 */
public class AdvBean extends BaseEntity {

    /**
     * areaName : 广告栏
     * contents : [{"isDelete":false,"createTime":"2020-02-24 14:53:13","updateTime":"2020-02-24 14:53:15","id":10009,"title":"广告栏","subtitle":"广告栏","displayPic":"/adv/adv1.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":1}]
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
         * createTime : 2020-02-24 14:53:13
         * updateTime : 2020-02-24 14:53:15
         * id : 10009
         * title : 广告栏
         * subtitle : 广告栏
         * displayPic : /adv/adv1.png
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
        private boolean isNeedLogin;

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

        public boolean isDelete() {
            return isDelete;
        }

        public void setDelete(boolean delete) {
            isDelete = delete;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
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
