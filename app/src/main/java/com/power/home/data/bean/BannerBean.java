package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/7 0007.
 */
public class BannerBean extends BaseEntity{

    /**
     * areaName : BANNER
     * contents : [{"isDelete":false,"createTime":"2020-02-24 14:40:28","updateTime":"2020-02-24 14:40:31","id":10000,"title":"BANNER展示一","subtitle":"BANNER展示一","displayPic":"/banner/banner1.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":1},{"isDelete":false,"createTime":"2020-02-24 14:41:47","updateTime":"2020-02-24 14:41:50","id":10001,"title":"BANNER展示二","subtitle":"BANNER展示二","displayPic":"/banner/banner2.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":2},{"isDelete":false,"createTime":"2020-02-24 14:43:50","updateTime":"2020-02-24 14:43:53","id":10002,"title":"BANNER展示三","subtitle":"BANNER展示三","displayPic":"/banner/banner3.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":3},{"isDelete":false,"createTime":"2020-02-24 14:44:53","updateTime":"2020-02-24 14:44:56","id":10003,"title":"BANNER展示四","subtitle":"BANNER展示四","displayPic":"/banner/banner4.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":4},{"isDelete":false,"createTime":"2020-02-24 14:47:02","updateTime":"2020-02-24 14:47:05","id":10004,"title":"BANNER展示五","subtitle":"BANNER展示五","displayPic":"/banner/banner5.png","type":null,"userType":null,"forwardType":1,"forwardAddress":"暂时没有地址","displayCount":0,"currentPrice":"0.00","sort":5}]
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
         * createTime : 2020-02-24 14:40:28
         * updateTime : 2020-02-24 14:40:31
         * id : 10000
         * title : BANNER展示一
         * subtitle : BANNER展示一
         * displayPic : /banner/banner1.png
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
        private String type;
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
