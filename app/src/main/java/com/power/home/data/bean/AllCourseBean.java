package com.power.home.data.bean;

import java.util.List;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public class AllCourseBean {

    /**
     * isDelete : false
     * createTime : 2020-02-13 16:15:43
     * updateTime : 2020-03-05 14:06:56
     * id : 10000
     * moduleName : 每日商道
     * sort : 1
     * courseTopics : [{"isDelete":false,"createTime":"2020-02-13 20:15:20","updateTime":"2020-02-13 20:15:22","id":10001,"title":"如何突现突袭，越轨发展","subtitle":"如何来吸引外界各方支持","displayCount":5000,"price":"88.00","displayPic":null,"description":null,"sort":2,"status":true},{"isDelete":false,"createTime":"2020-02-13 20:16:42","updateTime":"2020-02-13 20:16:45","id":10002,"title":"如何让机制更好用，更有效","subtitle":"千年不倒企业到底在卖什么","displayCount":5000,"price":"98.00","displayPic":null,"description":null,"sort":3,"status":true}]
     */

    private boolean isDelete;
    private String createTime;
    private String updateTime;
    private String id;
    private String moduleName;
    private int sort;
    private List<CourseTopicsBean> courseTopics;

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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<CourseTopicsBean> getCourseTopics() {
        return courseTopics;
    }

    public void setCourseTopics(List<CourseTopicsBean> courseTopics) {
        this.courseTopics = courseTopics;
    }

}
