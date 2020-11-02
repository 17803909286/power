package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/4/28 0028.
 */
public class VipPowerBean extends BaseEntity {

    private int img;
    private String title;
    private String content;

    public VipPowerBean(int img, String title, String content) {
        this.img = img;
        this.title = title;
        this.content = content;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
