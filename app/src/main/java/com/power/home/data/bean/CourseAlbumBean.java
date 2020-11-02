package com.power.home.data.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by zhp on 2019-12-09
 */
public class CourseAlbumBean extends BaseEntity implements MultiItemEntity {


    /**
     * id : 16
     * title : 计算机基础
     * subtitle : PowderPoint从入门到精通
     * authorId : 3
     * authorName : 王智勇
     * authorAvatar : null
     * price : 45
     * categoryName : 计算机
     * pic : null
     */

    private String id;
    private String title;
    private String subtitle;
    private String authorId;
    private String authorName;
    private String authorAvatar;
    private int price;
    private String categoryName;
    private String pic;
    private String picType;

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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public int getItemType() {
        return picType.equals("1") ? 0 : 1;
    }
}
