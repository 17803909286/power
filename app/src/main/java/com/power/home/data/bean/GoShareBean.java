package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/4/8 0008.
 */
public class GoShareBean extends BaseEntity {


    /**
     * shareImg : http://www.test.image.wlfxdata.com/%E4%BC%81%E4%B8%9A%E5%A2%9E%E9%95%BF%E8%90%A5%402x.png
     * shareUrl : /wlfx_mobile/give?recCode=ABVA7Q&share_id=10001
     * shareTitle : 你的好友免费送你一个动力营名额，快来领取
     * shareSubtitle : 300+优质课程，涵盖商业模式  创新营销  股权激励等八大分类
     */

    private String shareImg;
    private String shareUrl;
    private String shareTitle;
    private String shareSubtitle;

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
}
