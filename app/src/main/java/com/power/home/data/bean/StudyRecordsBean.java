package com.power.home.data.bean;

import java.util.List;

/**
 * Created by zhp on 2020-02-20
 */
public class StudyRecordsBean extends BaseEntity{


    /**
     * name : 2020-03-11
     * studyProgresses : [{"id":10009,"courseId":10002,"courseTitle":"每日商道课程3","courseSubtitle":"每日商道课程3","isFinish":false,"latestProcess":1200,"topProcess":2100,"courseTime":3000}]
     */

    private String name;
    private List<StudyRecordsItemBean> studyProgresses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudyRecordsItemBean> getStudyProgresses() {
        return studyProgresses;
    }

    public void setStudyProgresses(List<StudyRecordsItemBean> studyProgresses) {
        this.studyProgresses = studyProgresses;
    }

    @Override
    public String toString() {
        return "StudyRecordsBean{" +
                "name='" + name + '\'' +
                ", studyProgresses=" + studyProgresses.toString() +
                '}';
    }
}
