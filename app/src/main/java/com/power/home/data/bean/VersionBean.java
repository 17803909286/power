package com.power.home.data.bean;

/**
 * Created by ZHP on 2019/1/2/0002.
 */

public class VersionBean extends BaseEntity {


    /**
     * appName : 动力学社
     * appType : ANDROID
     * versionNumber : 1.0.5
     * versionDesc : 测试最新版
     * fileDownLink : http://q7ff1b4p3.bkt.clouddn.com/app-normal-release.apk
     * fileSize : 11.9M
     * isPop : true
     * isForceUpdate : true
     */

    private String appName;
    private String appType;
    private String versionNumber;
    private String versionDesc;
    private String fileDownLink;
    private String fileSize;
    private boolean isPop;
    private boolean isForceUpdate;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getFileDownLink() {
        return fileDownLink;
    }

    public void setFileDownLink(String fileDownLink) {
        this.fileDownLink = fileDownLink;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public boolean isIsPop() {
        return isPop;
    }

    public void setIsPop(boolean isPop) {
        this.isPop = isPop;
    }

    public boolean isIsForceUpdate() {
        return isForceUpdate;
    }

    public void setIsForceUpdate(boolean isForceUpdate) {
        this.isForceUpdate = isForceUpdate;
    }
}
