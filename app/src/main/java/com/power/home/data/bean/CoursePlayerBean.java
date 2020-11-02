package com.power.home.data.bean;

import java.util.List;

/**
 * Created by XWL on 2020/3/10.
 * Description:
 */
public class CoursePlayerBean extends BaseEntity {

    /**
     * price : 0.00
     * isBuy : false
     * isEmba : null
     * displayPic : null
     * description : null
     * courses : [{"id":10000,"title":"每日商道课程1","subtitle":"每日商道课程1","courseTime":3000,"playCounts":1000,"videoUrl":null,"isFree":true,"description":null,"displayPic":null,"detailDisplayPic":null,"moduleId":10000,"topicId":10000,"sort":1},{"id":10001,"title":"每日商道课程2","subtitle":"每日商道课程2","courseTime":1200,"playCounts":1000,"videoUrl":null,"isFree":true,"description":null,"displayPic":null,"detailDisplayPic":null,"moduleId":10000,"topicId":10000,"sort":2},{"id":10002,"title":"每日商道课程3","subtitle":"每日商道课程3","courseTime":3000,"playCounts":1000,"videoUrl":null,"isFree":false,"description":null,"displayPic":null,"detailDisplayPic":null,"moduleId":10000,"topicId":10000,"sort":3},{"id":10003,"title":"每日商道课程4","subtitle":"每日商道课程4","courseTime":3500,"playCounts":1000,"videoUrl":null,"isFree":false,"description":null,"displayPic":null,"detailDisplayPic":null,"moduleId":10000,"topicId":10000,"sort":4},{"id":10004,"title":"每日商道课程5","subtitle":"每日商道课程5","courseTime":2500,"playCounts":1000,"videoUrl":null,"isFree":false,"description":null,"displayPic":null,"detailDisplayPic":null,"moduleId":10000,"topicId":10000,"sort":5},{"id":10005,"title":"每日商道课程6","subtitle":"每日商道课程6","courseTime":2630,"playCounts":1000,"videoUrl":null,"isFree":false,"description":null,"displayPic":null,"detailDisplayPic":null,"moduleId":10000,"topicId":10000,"sort":6}]
     */

    private String price;
    private boolean isBuy;
    private boolean isEmba;
    private boolean embaIsExpired;
    private boolean isVip;
    private String embaPrice;
    private boolean growthIsExpired;
    private String classification;
    private String displayPic;
    private String descriptionUrl;
    private String courseTopicTitle;
    private String courseTopicSubtitle;
    private String topicId;
    private String topicCover;
    private List<CoursesBean> courses;
    private String shareImg;
    private String shareUrl;
    private List<String> posterImgs;
    private List<String> slogans;
    private String shareTitle;
    private String shareSubtitle;
    private String growingPrice;
    private String growingIcon;
    private String courseTotalSize;
    private String courseSize;
    private String belongsTo;
    private boolean continueUpdating;

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    public String getCourseTotalSize() {
        return courseTotalSize;
    }

    public void setCourseTotalSize(String courseTotalSize) {
        this.courseTotalSize = courseTotalSize;
    }

    public String getCourseSize() {
        return courseSize;
    }

    public void setCourseSize(String courseSize) {
        this.courseSize = courseSize;
    }

    public boolean isContinueUpdating() {
        return continueUpdating;
    }

    public void setContinueUpdating(boolean continueUpdating) {
        this.continueUpdating = continueUpdating;
    }

    public String getGrowingPrice() {
        return growingPrice;
    }

    public void setGrowingPrice(String growingPrice) {
        this.growingPrice = growingPrice;
    }

    public String getGrowingIcon() {
        return growingIcon;
    }

    public void setGrowingIcon(String growingIcon) {
        this.growingIcon = growingIcon;
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

    public String getTopicCover() {
        return topicCover;
    }

    public void setTopicCover(String topicCover) {
        this.topicCover = topicCover;
    }

    public String getEmbaPrice() {
        return embaPrice;
    }

    public void setEmbaPrice(String embaPrice) {
        this.embaPrice = embaPrice;
    }

    public String getCourseTopicTitle() {
        return courseTopicTitle;
    }

    public void setCourseTopicTitle(String courseTopicTitle) {
        this.courseTopicTitle = courseTopicTitle;
    }

    public String getCourseTopicSubtitle() {
        return courseTopicSubtitle;
    }

    public void setCourseTopicSubtitle(String courseTopicSubtitle) {
        this.courseTopicSubtitle = courseTopicSubtitle;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isEmba() {
        return isEmba;
    }

    public void setEmba(boolean emba) {
        isEmba = emba;
    }

    public boolean isEmbaIsExpired() {
        return embaIsExpired;
    }

    public void setEmbaIsExpired(boolean embaIsExpired) {
        this.embaIsExpired = embaIsExpired;
    }

    public boolean isGrowthIsExpired() {
        return growthIsExpired;
    }

    public void setGrowthIsExpired(boolean growthIsExpired) {
        this.growthIsExpired = growthIsExpired;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic;
    }

    public String getDescriptionUrl() {
        return descriptionUrl;
    }

    public void setDescriptionUrl(String descriptionUrl) {
        this.descriptionUrl = descriptionUrl;
    }

    public List<CoursesBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesBean> courses) {
        this.courses = courses;
    }

    public static class CoursesBean extends BaseEntity {
        /**
         * id : 10000
         * title : 每日商道课程1
         * subtitle : 每日商道课程1
         * courseTime : 3000
         * playCounts : 1000
         * videoUrl : null
         * isFree : true
         * description : null
         * displayPic : null
         * detailDisplayPic : null
         * moduleId : 10000
         * topicId : 10000
         * sort : 1
         */

        private String id;
        private String title;
        private String subtitle;
        private int courseTime;
        private int playCounts;
        private String videoUrl;
        private boolean isFree;
        private String description;
        private String displayPic;
        private String detailDisplayPic;
        private String moduleId;
        private String topicId;
        private String sort;
        private boolean canPlay;
        private int latestProgress;
        private int topProgress;
        private boolean isFinish;
        private boolean isBuy;
        private int position;
        private boolean isLastStudy;
        private String courseCover;
        private String shareIcon;
        private String audioUrl;

        public String getShareIcon() {
            return shareIcon;
        }

        public void setShareIcon(String shareIcon) {
            this.shareIcon = shareIcon;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public String getCourseCover() {
            return courseCover;
        }

        public void setCourseCover(String courseCover) {
            this.courseCover = courseCover;
        }

        public boolean isLastStudy() {
            return isLastStudy;
        }

        public void setLastStudy(boolean lastStudy) {
            isLastStudy = lastStudy;
        }

        public boolean isCanPlay() {
            return canPlay;
        }

        public void setCanPlay(boolean canPlay) {
            this.canPlay = canPlay;
        }

        public int getLatestProgress() {
            return latestProgress;
        }

        public void setLatestProgress(int latestProgress) {
            this.latestProgress = latestProgress;
        }

        public int getTopProgress() {
            return topProgress;
        }

        public void setTopProgress(int topProgress) {
            this.topProgress = topProgress;
        }

        public boolean isFinish() {
            return isFinish;
        }

        public void setFinish(boolean finish) {
            isFinish = finish;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
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

        public int getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(int courseTime) {
            this.courseTime = courseTime;
        }

        public int getPlayCounts() {
            return playCounts;
        }

        public void setPlayCounts(int playCounts) {
            this.playCounts = playCounts;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisplayPic() {
            return displayPic;
        }

        public void setDisplayPic(String displayPic) {
            this.displayPic = displayPic;
        }

        public String getDetailDisplayPic() {
            return detailDisplayPic;
        }

        public void setDetailDisplayPic(String detailDisplayPic) {
            this.detailDisplayPic = detailDisplayPic;
        }

        public String getModuleId() {
            return moduleId;
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public String getTopicId() {
            return topicId;
        }

        public void setTopicId(String topicId) {
            this.topicId = topicId;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
