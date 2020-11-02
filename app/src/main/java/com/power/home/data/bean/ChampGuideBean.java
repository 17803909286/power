package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/7 0007.
 */

public class ChampGuideBean extends BaseEntity{


    /**
     * areaName : 状元指导
     * contents : [{"createTime":"2020-07-07 02:55:15","updateTime":"2020-07-07 02:55:23","id":10176,"title":"郭志强","subtitle":"2018届山东文科状元","displayPic":null,"type":null,"userType":null,"forwardType":1,"forwardAddress":"{\"router\":\"champ\",\"id\":1}","vipFlag":false,"continueUpdating":false,"courseTotalSize":null,"displayCount":null,"currentPrice":null,"originalPrice":null,"isBuy":false,"isFree":null,"courseTime":null,"courseSize":null,"belongsTo":null,"sort":1,"isNeedLogin":false},{"createTime":"2020-07-07 02:55:15","updateTime":"2020-07-07 02:55:15","id":10177,"title":"乐平","subtitle":"2018届山东理科状元","displayPic":null,"type":null,"userType":null,"forwardType":1,"forwardAddress":"{\"router\":\"champ\",\"id\":2}","vipFlag":false,"continueUpdating":false,"courseTotalSize":null,"displayCount":null,"currentPrice":null,"originalPrice":null,"isBuy":false,"isFree":null,"courseTime":null,"courseSize":null,"belongsTo":null,"sort":2,"isNeedLogin":false},{"createTime":"2020-07-07 02:55:15","updateTime":"2020-07-07 02:55:15","id":10178,"title":"张彭","subtitle":"2019届山东文科状元","displayPic":null,"type":null,"userType":null,"forwardType":1,"forwardAddress":"{\"router\":\"champ\",\"id\":3}","vipFlag":false,"continueUpdating":false,"courseTotalSize":null,"displayCount":null,"currentPrice":null,"originalPrice":null,"isBuy":false,"isFree":null,"courseTime":null,"courseSize":null,"belongsTo":null,"sort":3,"isNeedLogin":false}]
     */

    private String areaName;
    private List<ContentsBean> contents;
    /**
     * id : 1
     * teacherAvatar : http://www.test.image.wlfxdata.com/timg.jpg
     * teacherName : 郭志强
     * teacherSummary : 第一个状元
     * courseTopicTotalSize : 3
     * courseTopicDtos : null
     */

    private String id;
    private String teacherAvatar;
    private String teacherName;
    private String teacherSummary;
    private int courseTopicTotalSize;
    private Object courseTopicDtos;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherAvatar() {
        return teacherAvatar;
    }

    public void setTeacherAvatar(String teacherAvatar) {
        this.teacherAvatar = teacherAvatar;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSummary() {
        return teacherSummary;
    }

    public void setTeacherSummary(String teacherSummary) {
        this.teacherSummary = teacherSummary;
    }

    public int getCourseTopicTotalSize() {
        return courseTopicTotalSize;
    }

    public void setCourseTopicTotalSize(int courseTopicTotalSize) {
        this.courseTopicTotalSize = courseTopicTotalSize;
    }

    public Object getCourseTopicDtos() {
        return courseTopicDtos;
    }

    public void setCourseTopicDtos(Object courseTopicDtos) {
        this.courseTopicDtos = courseTopicDtos;
    }

    public static class ContentsBean extends BaseEntity{
        /**
         * createTime : 2020-07-07 02:55:15
         * updateTime : 2020-07-07 02:55:23
         * id : 10176
         * title : 郭志强
         * subtitle : 2018届山东文科状元
         * displayPic : null
         * type : null
         * userType : null
         * forwardType : 1
         * forwardAddress : {"router":"champ","id":1}
         * vipFlag : false
         * continueUpdating : false
         * courseTotalSize : null
         * displayCount : null
         * currentPrice : null
         * originalPrice : null
         * isBuy : false
         * isFree : null
         * courseTime : null
         * courseSize : null
         * belongsTo : null
         * sort : 1
         * isNeedLogin : false
         */

        private String createTime;
        private String updateTime;
        private String id;
        private String title;
        private String subtitle;
        private Object displayPic;
        private Object type;
        private Object userType;
        private int forwardType;
        private String forwardAddress;
        private boolean vipFlag;
        private boolean continueUpdating;
        private Object courseTotalSize;
        private Object displayCount;
        private Object currentPrice;
        private Object originalPrice;
        private boolean isBuy;
        private Object isFree;
        private Object courseTime;
        private Object courseSize;
        private Object belongsTo;
        private int sort;
        private boolean isNeedLogin;

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

        public Object getDisplayPic() {
            return displayPic;
        }

        public void setDisplayPic(Object displayPic) {
            this.displayPic = displayPic;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getUserType() {
            return userType;
        }

        public void setUserType(Object userType) {
            this.userType = userType;
        }

        public int getForwardType() {
            return forwardType;
        }

        public void setForwardType(int forwardType) {
            this.forwardType = forwardType;
        }

        public String getForwardAddress() {
            return forwardAddress;
        }

        public void setForwardAddress(String forwardAddress) {
            this.forwardAddress = forwardAddress;
        }

        public boolean isVipFlag() {
            return vipFlag;
        }

        public void setVipFlag(boolean vipFlag) {
            this.vipFlag = vipFlag;
        }

        public boolean isContinueUpdating() {
            return continueUpdating;
        }

        public void setContinueUpdating(boolean continueUpdating) {
            this.continueUpdating = continueUpdating;
        }

        public Object getCourseTotalSize() {
            return courseTotalSize;
        }

        public void setCourseTotalSize(Object courseTotalSize) {
            this.courseTotalSize = courseTotalSize;
        }

        public Object getDisplayCount() {
            return displayCount;
        }

        public void setDisplayCount(Object displayCount) {
            this.displayCount = displayCount;
        }

        public Object getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(Object currentPrice) {
            this.currentPrice = currentPrice;
        }

        public Object getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(Object originalPrice) {
            this.originalPrice = originalPrice;
        }

        public boolean isIsBuy() {
            return isBuy;
        }

        public void setIsBuy(boolean isBuy) {
            this.isBuy = isBuy;
        }

        public Object getIsFree() {
            return isFree;
        }

        public void setIsFree(Object isFree) {
            this.isFree = isFree;
        }

        public Object getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(Object courseTime) {
            this.courseTime = courseTime;
        }

        public Object getCourseSize() {
            return courseSize;
        }

        public void setCourseSize(Object courseSize) {
            this.courseSize = courseSize;
        }

        public Object getBelongsTo() {
            return belongsTo;
        }

        public void setBelongsTo(Object belongsTo) {
            this.belongsTo = belongsTo;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public boolean isIsNeedLogin() {
            return isNeedLogin;
        }

        public void setIsNeedLogin(boolean isNeedLogin) {
            this.isNeedLogin = isNeedLogin;
        }
    }
}
