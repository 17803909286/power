package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/7/9 0009.
 */
public class ChampDetailBean extends BaseEntity {


    /**
     * id : 1
     * teacherAvatar : http://www.test.image.wlfxdata.com/timg.jpg
     * teacherName : 郭志强
     * teacherSummary : 第一个状元
     * courseTopicTotalSize : 3
     * courseTopicDtos : [{"id":10080,"title":"状元专辑副标题","subtitle":"状元专辑标题","moduleId":10027,"price":"298.00","originalPrice":"298.00","displayPic":"http://www.test.image.wlfxdata.com/20200115-105050.png","isBuy":null,"description":"描述","topicCover":"http://www.test.image.wlfxdata.com/shangdaowufenzhong34-2.png","displayCount":null,"type":1,"classification":5,"belongsTo":null,"courseSize":null,"courseTotalSize":35,"continueUpdating":false,"vipFlag":false,"isFree":false},{"id":10081,"title":"状元专辑副标题1","subtitle":"状元专辑标题1","moduleId":10027,"price":"298.00","originalPrice":"298.00","displayPic":"http://www.test.image.wlfxdata.com/20200115-105050.png","isBuy":null,"description":"描述","topicCover":"http://www.test.image.wlfxdata.com/shangdaowufenzhong34-2.png","displayCount":null,"type":1,"classification":5,"belongsTo":null,"courseSize":null,"courseTotalSize":35,"continueUpdating":false,"vipFlag":false,"isFree":false},{"id":10082,"title":"状元专辑副标题2","subtitle":"状元专辑标题2","moduleId":10027,"price":"298.00","originalPrice":"298.00","displayPic":"http://www.test.image.wlfxdata.com/20200115-105050.png","isBuy":null,"description":"描述","topicCover":"http://www.test.image.wlfxdata.com/shangdaowufenzhong34-2.png","displayCount":null,"type":1,"classification":5,"belongsTo":null,"courseSize":null,"courseTotalSize":35,"continueUpdating":false,"vipFlag":false,"isFree":false}]
     */

    private int id;
    private String teacherAvatar;
    private String teacherName;
    private String teacherSummary;
    private int courseTopicTotalSize;
    private List<CourseTopicsBean> courseTopicDtos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<CourseTopicsBean> getCourseTopicDtos() {
        return courseTopicDtos;
    }

    public void setCourseTopicDtos(List<CourseTopicsBean> courseTopicDtos) {
        this.courseTopicDtos = courseTopicDtos;
    }
}
