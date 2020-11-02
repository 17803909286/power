package com.power.home.data.bean;

/**
 * Created by zhp on 2020-02-20
 */
public class CourseOfflineBean extends BaseEntity {
    /**
     * id : 10002
     * name : 线下活动3
     * displayPic : http://www.test.image.future.sdgyjyzx.com/v2-67facf7ee550c54acb0a5bd4c7751a8e_1200x500.jpg
     * description : 线下活动3介绍
     * wordPicDes : https://mp.weixin.qq.com/s/BomoS7zOZUk2gtHmhyJF_g
     * enrollStartTime : 2020-04-17 07:37:43
     * enrollEndTime : 2020-04-18 07:38:12
     * startTime : null
     * endTime : null
     * enrollStatus : ENROLL_NOT_START
     */
    private String id;
    private String name;
    private String displayPic;
    private String description;
    private String wordPicDes;
    private String enrollStartTime;
    private String enrollEndTime;
    private String startTime;
    private String createTime;
    private String endTime;
    private String enrollStatus;
    /**
     * id : 10043
     * shareIcon : null
     * startTime : null
     * endTime : null
     * updateTime : 2020-06-18 21:37:04
     * assistActivityGroupSize : 0
     * assistActivityOpen : true
     * assistActivityRewardDescPic : http://www.test.image.wlfxdata.com/1.jpg
     * assistActivityStartTime : 1592496000000
     * assistActivityEndTime : 1592841600000
     * assistActivityRewardAdoptRule : <p>  的地方是</p>
     */

    private String shareIcon;
    private String updateTime;
    private int assistActivityGroupSize;
    private boolean assistActivityOpen;
    private String assistActivityRewardDescPic;
    private long assistActivityStartTime;
    private long assistActivityEndTime;
    private String assistActivityRewardAdoptRule;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWordPicDes() {
        return wordPicDes;
    }

    public void setWordPicDes(String wordPicDes) {
        this.wordPicDes = wordPicDes;
    }

    public String getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(String enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public String getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(String enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(String enrollStatus) {
        this.enrollStatus = enrollStatus;
    }

    public String getShareIcon() {
        return shareIcon;
    }

    public void setShareIcon(String shareIcon) {
        this.shareIcon = shareIcon;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getAssistActivityGroupSize() {
        return assistActivityGroupSize;
    }

    public void setAssistActivityGroupSize(int assistActivityGroupSize) {
        this.assistActivityGroupSize = assistActivityGroupSize;
    }

    public boolean isAssistActivityOpen() {
        return assistActivityOpen;
    }

    public void setAssistActivityOpen(boolean assistActivityOpen) {
        this.assistActivityOpen = assistActivityOpen;
    }

    public String getAssistActivityRewardDescPic() {
        return assistActivityRewardDescPic;
    }

    public void setAssistActivityRewardDescPic(String assistActivityRewardDescPic) {
        this.assistActivityRewardDescPic = assistActivityRewardDescPic;
    }

    public long getAssistActivityStartTime() {
        return assistActivityStartTime;
    }

    public void setAssistActivityStartTime(long assistActivityStartTime) {
        this.assistActivityStartTime = assistActivityStartTime;
    }

    public long getAssistActivityEndTime() {
        return assistActivityEndTime;
    }

    public void setAssistActivityEndTime(long assistActivityEndTime) {
        this.assistActivityEndTime = assistActivityEndTime;
    }

    public String getAssistActivityRewardAdoptRule() {
        return assistActivityRewardAdoptRule;
    }

    public void setAssistActivityRewardAdoptRule(String assistActivityRewardAdoptRule) {
        this.assistActivityRewardAdoptRule = assistActivityRewardAdoptRule;
    }
}
