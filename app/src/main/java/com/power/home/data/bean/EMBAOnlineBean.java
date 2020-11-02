package com.power.home.data.bean;

import java.util.List;

/**
 * Created by XWL on 2020/3/7.
 * Description:
 */
public class EMBAOnlineBean extends BaseEntity{


    /**
     * id : 10000
     * landingPage : http://139.129.98.107/wlfxapp/resource/course/landingpage.png
     * price : 1970-01-01
     * embaDueTime :
     * isBuy : false
     * isExpired : true
     * courseTopics : [{"id":10018,"title":"专题标题","subtitle":"专题副标题","moduleId":10004,"price":"59.00","displayPic":null,"description":null,"displayCount":5000,"type":1,"classification":3},{"id":10019,"title":"专题增长标题","subtitle":"专题副标题","moduleId":10004,"price":"59.00","displayPic":null,"description":null,"displayCount":5000,"type":1,"classification":3},{"id":10020,"title":"专题增长标题","subtitle":"专题副标题","moduleId":10004,"price":"59.00","displayPic":null,"description":null,"displayCount":6000,"type":1,"classification":3},{"id":10021,"title":"增长标题","subtitle":"专题副标题","moduleId":10004,"price":"59.00","displayPic":null,"description":null,"displayCount":5000,"type":1,"classification":3}]
     * growingCourse : {"id":10003,"title":"动力营专题","subtitle":"动力营副标题","moduleId":10001,"price":"100.00","displayPic":"http://139.129.98.107/wlfxapp/resource/course/growing.png","description":null,"displayCount":15000,"type":1,"classification":2}
     */

    private String id;
    private String landingPage;
    private String price;
    private String embaDueTime;
    private boolean isBuy;
    private boolean isExpired;
    private GrowingCourseBean growingCourse;
    private List<CourseTopicsBean> courseTopics;
    private String shareImg;
    private String shareUrl;
    private List<String> posterImgs;
    private List<String> slogans;
    private String shareTitle;
    private String shareSubtitle;
    private String descriptionUrl;

    public String getDescriptionUrl() {
        return descriptionUrl;
    }

    public void setDescriptionUrl(String descriptionUrl) {
        this.descriptionUrl = descriptionUrl;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public List<String> getPosterImgs() {
        return posterImgs;
    }

    public void setPosterImgs(List<String> posterImgs) {
        this.posterImgs = posterImgs;
    }

    public List<String> getSlogans() {
        return slogans;
    }

    public void setSlogans(List<String> slogans) {
        this.slogans = slogans;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareSubtitle() {
        return shareSubtitle;
    }

    public void setShareSubtitle(String shareSubtitle) {
        this.shareSubtitle = shareSubtitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmbaDueTime() {
        return embaDueTime;
    }

    public void setEmbaDueTime(String embaDueTime) {
        this.embaDueTime = embaDueTime;
    }

    public boolean isIsBuy() {
        return isBuy;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public boolean isIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public GrowingCourseBean getGrowingCourse() {
        return growingCourse;
    }

    public void setGrowingCourse(GrowingCourseBean growingCourse) {
        this.growingCourse = growingCourse;
    }

    public List<CourseTopicsBean> getCourseTopics() {
        return courseTopics;
    }

    public void setCourseTopics(List<CourseTopicsBean> courseTopics) {
        this.courseTopics = courseTopics;
    }

    public static class GrowingCourseBean extends BaseEntity{
        /**
         * id : 10003
         * title : 动力营专题
         * subtitle : 动力营副标题
         * moduleId : 10001
         * price : 100.00
         * displayPic : http://139.129.98.107/wlfxapp/resource/course/growing.png
         * description : null
         * displayCount : 15000
         * type : 1
         * classification : 2
         */

        private String id;
        private String title;
        private String subtitle;
        private String moduleId;
        private String price;
        private String displayPic;
        private String description;
        private int displayCount;
        private String type;
        private String classification;

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

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDisplayPic() {
            return displayPic;
        }

        public void setDisplayPic(String displayPic) {
            this.displayPic = displayPic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDisplayCount() {
            return displayCount;
        }

        public void setDisplayCount(int displayCount) {
            this.displayCount = displayCount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }
    }

    public static class CourseTopicsBean extends BaseEntity{
        /**
         * id : 10018
         * title : 专题标题
         * subtitle : 专题副标题
         * moduleId : 10004
         * price : 59.00
         * displayPic : null
         * description : null
         * displayCount : 5000
         * type : 1
         * classification : 3
         */

        private String id;
        private String title;
        private String subtitle;
        private String moduleId;
        private String price;
        private String displayPic;
        private String description;
        private int displayCount;
        private String type;
        private String classification;
        private boolean isBuy;

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
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

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDisplayPic() {
            return displayPic;
        }

        public void setDisplayPic(String displayPic) {
            this.displayPic = displayPic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDisplayCount() {
            return displayCount;
        }

        public void setDisplayCount(int displayCount) {
            this.displayCount = displayCount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }
    }
}
