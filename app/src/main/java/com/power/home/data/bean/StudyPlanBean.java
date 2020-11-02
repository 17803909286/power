package com.power.home.data.bean;

import java.util.List;

/**
 * Created by XWL on 2020/3/16.
 * Description:
 */
public class StudyPlanBean extends BaseEntity{

    private List<TodayPlanBean> todayPlan;
    private List<FinishedCourseBean> finishedCourse;

    public List<TodayPlanBean> getTodayPlan() {
        return todayPlan;
    }

    public void setTodayPlan(List<TodayPlanBean> todayPlan) {
        this.todayPlan = todayPlan;
    }

    public List<FinishedCourseBean> getFinishedCourse() {
        return finishedCourse;
    }

    public void setFinishedCourse(List<FinishedCourseBean> finishedCourse) {
        this.finishedCourse = finishedCourse;
    }

    public static class TodayPlanBean extends BaseEntity{
        /**
         * topicTitle : 动力营专题
         * courseTitle : 课程标题
         * courseTime : 2630
         * latestProcess : 0
         * topProcess : 0
         * isFree : false
         * courseId : 10018
         */

        private String topicTitle;
        private String courseTitle;
        private int courseTime;
        private int latestProcess;
        private int topProcess;
        private boolean isFree;
        private String courseId;

        public String getTopicTitle() {
            return topicTitle;
        }

        public void setTopicTitle(String topicTitle) {
            this.topicTitle = topicTitle;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }

        public int getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(int courseTime) {
            this.courseTime = courseTime;
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

        public boolean isIsFree() {
            return isFree;
        }

        public void setIsFree(boolean isFree) {
            this.isFree = isFree;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }
    }

    public static class FinishedCourseBean extends BaseEntity{
        /**
         * topicTitle : 动力营专题
         * courseTitle : 课程标题
         * courseTime : 2630
         * latestProcess : 0
         * topProcess : 0
         * isFree : false
         * courseId : 10018
         */

        private String topicTitle;
        private String courseTitle;
        private int courseTime;
        private int latestProcess;
        private int topProcess;
        private boolean isFree;
        private String courseId;

        public String getTopicTitle() {
            return topicTitle;
        }

        public void setTopicTitle(String topicTitle) {
            this.topicTitle = topicTitle;
        }

        public String getCourseTitle() {
            return courseTitle;
        }

        public void setCourseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
        }

        public int getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(int courseTime) {
            this.courseTime = courseTime;
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

        public boolean isIsFree() {
            return isFree;
        }

        public void setIsFree(boolean isFree) {
            this.isFree = isFree;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }
    }
}
