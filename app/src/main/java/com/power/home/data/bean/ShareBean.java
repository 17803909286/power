package com.power.home.data.bean;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by XWL on 2020/3/21.
 * Description:
 */
public class ShareBean extends BaseEntity{
    List<String> posterImgs;
    List<String> slogans;
    private String shareTitle;
    private String shareSubtitle;
    private String shareImg;
    private String shareUrl;
    private String courseName;
    private String inviteFrontName;
    private String inviteBehindName;

    public String getInviteFrontName() {
        return inviteFrontName;
    }

    public void setInviteFrontName(String inviteFrontName) {
        this.inviteFrontName = inviteFrontName;
    }

    public String getInviteBehindName() {
        return inviteBehindName;
    }

    public void setInviteBehindName(String inviteBehindName) {
        this.inviteBehindName = inviteBehindName;
    }

    public List<String> getPosterImgs() {
        return posterImgs;
    }

    public void setPosterImgs(List<String> posterImgs) {
        this.posterImgs = posterImgs;
    }

    public List<String> getSlogans() {
        return slogans;
    }

    public void setSlogans(List<String> slogans) {
        this.slogans = slogans;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareSubtitle() {
        return shareSubtitle;
    }

    public void setShareSubtitle(String shareSubtitle) {
        this.shareSubtitle = shareSubtitle;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
