package com.power.home.data.bean;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public class CourseFreeDailyBean extends BaseEntity {


    /**
     * id : 10000
     * title : 每日商道课程1
     * subtitle : 每日商道课程1
     * courseTime : 3000
     * playCounts : 1000
     * videoUrl : http://www.test.video.future.sdgyjyzx.com/course_chen_720p_high_index.m3u8;http://www.test.video.future.sdgyjyzx.com/course_chen_480p_standard_index.m3u8
     * isFree : true
     * description : null
     * displayPic : null
     * detailDisplayPic : null
     * moduleId : 10000
     * topicId : 10000
     * sort : 1
     * canPlay : false
     * latestProgress : null
     * topProgress : null
     * isFinish : null
     * type : 2
     */

    private String id;
    private String title;
    private String subtitle;
    private int courseTime;
    private int playCounts;
    private String videoUrl;
    private boolean isFree;
    private boolean isBuy;
    private String description;
    private String displayPic;
    private String detailDisplayPic;
    private String moduleId;
    private String topicId;
    private String sort;
    private boolean canPlay;
    private String latestProgress;
    private String topProgress;
    private boolean isFinish;
    private String type;
    private boolean isLastStudy;
    private String courseCover;
    private String shareIcon;
    private String audioUrl;

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public boolean isLastStudy() {
        return isLastStudy;
    }

    public void setLastStudy(boolean lastStudy) {
        isLastStudy = lastStudy;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

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

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public String getLatestProgress() {
        return latestProgress;
    }

    public void setLatestProgress(String latestProgress) {
        this.latestProgress = latestProgress;
    }

    public String getTopProgress() {
        return topProgress;
    }

    public void setTopProgress(String topProgress) {
        this.topProgress = topProgress;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
