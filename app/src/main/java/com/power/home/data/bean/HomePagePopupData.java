package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/6/15 0015.
 */
public class HomePagePopupData extends BaseEntity{


    /**
     * displayPic :
     * forwardAddress :
     * forwardType : 0
     * id : 0
     * title :
     */

    private String displayPic;
    private String forwardAddress;
    private String forwardType;
    private String id;
    private String title;

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic;
    }

    public String getForwardAddress() {
        return forwardAddress;
    }

    public void setForwardAddress(String forwardAddress) {
        this.forwardAddress = forwardAddress;
    }

    public String getForwardType() {
        return forwardType;
    }

    public void setForwardType(String forwardType) {
        this.forwardType = forwardType;
    }

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
}
