package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/2/26 0026.
 */
public class MessageBean extends BaseEntity{


    /**
     * id : 20003
     * content : 你的账户余额有一笔新的资金入账
     * type : PUSH
     * isRead : true
     * userId : 10010
     * forwardAddress : {"router":"withdraw","id":null}
     * sendTime : 2020-03-20 10:08:16
     * readTime : null
     */

    private String id;
    private String content;
    private String type;
    private String isRead;
    private String userId;
    private String forwardAddress;
    private String sendTime;
    private String readTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getForwardAddress() {
        return forwardAddress;
    }

    public void setForwardAddress(String forwardAddress) {
        this.forwardAddress = forwardAddress;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }
}
