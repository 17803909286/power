package com.power.home.data.bean;

import java.util.List;

/**
 * Created by zhp on 2020-03-19
 */
public class FriendsBean extends BaseEntity {


    /**
     * posterImage : http://www.test.image.future.sdgyjyzx.com/true.jpg
     * recCode : TDA82Y
     */

    private String inviteUrl;
    private String recCode;
    private List<String> posterImages;
    private List<String> slogans;

    public String getInviteUrl() {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public List<String> getPosterImages() {
        return posterImages;
    }

    public void setPosterImages(List<String> posterImages) {
        this.posterImages = posterImages;
    }

    public List<String> getSlogans() {
        return slogans;
    }

    public void setSlogans(List<String> slogans) {
        this.slogans = slogans;
    }
}
