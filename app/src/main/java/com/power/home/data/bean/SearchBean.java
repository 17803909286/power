package com.power.home.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by XWL on 2020/3/6.
 * Description:
 */
public class SearchBean extends BaseEntity{


    /**
     * typeName : 专辑
     * courseType : 1
     * searchResults : [{"id":10003,"type":"1","playCount":15000,"price":"100.00","title":"动力营专题","subtitle":"动力营副标题","displayPic":"http://139.129.98.107/wlfxapp/resource/course/growing.png"},{"id":10019,"type":"1","playCount":5000,"price":"59.00","title":"专题增长标题","subtitle":"专题副标题","displayPic":null},{"id":10020,"type":"1","playCount":6000,"price":"59.00","title":"专题增长标题","subtitle":"专题副标题","displayPic":null}]
     */

    private String typeName;
    private String courseType;
    private List<SearchResultsBean> searchResults;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public List<SearchResultsBean> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<SearchResultsBean> searchResults) {
        this.searchResults = searchResults;
    }

    public static class SearchResultsBean extends BaseEntity implements MultiItemEntity {
        /**
         * id : 10003
         * type : 1
         * playCount : 15000
         * price : 100.00
         * title : 动力营专题
         * subtitle : 动力营副标题
         * displayPic : http://139.129.98.107/wlfxapp/resource/course/growing.png
         */

        private String id;
        private int type;
        private int playCount;
        private String price;
        private String title;
        private boolean isBuy;
        private String subtitle;
        private String displayPic;
        private String forwardAddress;
        private String forwardType;
        private int classification;
        private String belongsTo;
        private String courseSize;
        private String courseTotalSize;
        private boolean continueUpdating;
        private int courseTime;
        private boolean isFree;
        private boolean isDiscount;
        private String originalPrice;
        private String sort;
        private boolean vipFlag;

        public boolean isVipFlag() {
            return vipFlag;
        }

        public void setVipFlag(boolean vipFlag) {
            this.vipFlag = vipFlag;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
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

        public String getBelongsTo() {
            return belongsTo;
        }

        public void setBelongsTo(String belongsTo) {
            this.belongsTo = belongsTo;
        }

        public String getCourseSize() {
            return courseSize;
        }

        public void setCourseSize(String courseSize) {
            this.courseSize = courseSize;
        }

        public int getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(int courseTime) {
            this.courseTime = courseTime;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        public String getForwardAddress() {
            return forwardAddress;
        }

        public void setForwardAddress(String forwardAddress) {
            this.forwardAddress = forwardAddress;
        }

        public String getForwardType() {
            return forwardType;
        }

        public void setForwardType(String forwardType) {
            this.forwardType = forwardType;
        }

        public int getClassification() {
            return classification;
        }

        public void setClassification(int classification) {
            this.classification = classification;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        @Override
        public int getItemType() {
            return type;
        }
    }
}
