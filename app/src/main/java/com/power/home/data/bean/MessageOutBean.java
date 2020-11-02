package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/2/26 0026.
 */
public class MessageOutBean extends BaseEntity {

    private String totalElements;
    private List<MessageBean> content;

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public List<MessageBean> getContent() {
        return content;
    }

    public void setContent(List<MessageBean> content) {
        this.content = content;
    }
}
