package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/4/29 0029.
 */
public class VipRecordBean extends BaseEntity{


    /**
     * changeTitle : 名额增加
     * changeSubtitle : 总部
     * changeNumberStr : +100
     * changeTime : 2020年04月29日 14:40
     */

    private String changeTitle;
    private String changeSubtitle;
    private String changeNumberStr;
    private String changeTime;

    public String getChangeTitle() {
        return changeTitle;
    }

    public void setChangeTitle(String changeTitle) {
        this.changeTitle = changeTitle;
    }

    public String getChangeSubtitle() {
        return changeSubtitle;
    }

    public void setChangeSubtitle(String changeSubtitle) {
        this.changeSubtitle = changeSubtitle;
    }

    public String getChangeNumberStr() {
        return changeNumberStr;
    }

    public void setChangeNumberStr(String changeNumberStr) {
        this.changeNumberStr = changeNumberStr;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }
}
