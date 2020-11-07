package com.power.home.data.bean;

/**
 * Created by zhp on 2020-02-20
 */
public class StudyRecordsItemBean extends BaseEntity{

    /**
     * id : 10009
     * courseId : 10002
     * courseTitle : 每日商道课程3
     * courseSubtitle : 每日商道课程3
     * isFinish : false
     * latestProcess : 1200
     * topProcess : 2100
     * courseTime : 3000
     */

    private int id;
    private String courseId;
    private String courseTitle;
    private String courseSubtitle;
    private boolean isFinish;
    private int latestProcess;
    private int topProcess;
    private int courseTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseSubtitle() {
        return courseSubtitle;
    }

    public void setCourseSubtitle(String courseSubtitle) {
        this.courseSubtitle = courseSubtitle;
    }

    public boolean isIsFinish() {
        return isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public int getLatestProcess() {
        return latestProcess;
    }

    public void setLatestProcess(int latestProcess) {
        this.latestProcess = latestProcess;
    }

    public int getTopProcess() {
        return topProcess;
    }

    public void setTopProcess(int topProcess) {
        this.topProcess = topProcess;
    }

    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    @Override
    public String toString() {
        return "StudyRecordsItemBean{" +
                "id=" + id +
                ", courseId='" + courseId + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseSubtitle='" + courseSubtitle + '\'' +
                ", isFinish=" + isFinish +
                ", latestProcess=" + latestProcess +
                ", topProcess=" + topProcess +
                ", courseTime=" + courseTime +
                '}';
    }
}
