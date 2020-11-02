package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/4/14 0014.
 */
public class ShareImgeBean extends BaseEntity{

    private String title;
    private int position;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
