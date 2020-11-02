package com.power.home.data.bean;

/**
 * Created by zhp on 2019-12-09
 */
public class WeekRecommendBean extends BaseEntity {


    /**
     * id : 1
     * title : 高数
     * subtitle : 同济大学高数一
     * desc : 高数简要描述
     * pic : null
     * issue : 1
     * message : null
     * backPic : null
     */

    private String id;
    private String title;
    private String subtitle;
    private String desc;
    private String pic;
    private int issue;
    private String message;
    private String backPic;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBackPic() {
        return backPic;
    }

    public void setBackPic(String backPic) {
        this.backPic = backPic;
    }
}
