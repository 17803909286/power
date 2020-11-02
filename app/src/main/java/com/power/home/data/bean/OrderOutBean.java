package com.power.home.data.bean;

import java.util.List;

/**
 * Created by zhp on 2020-03-10
 */
public class OrderOutBean extends BaseEntity{

    private String totalElements;
    private List<OrderBean> content;

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public List<OrderBean> getContent() {
        return content;
    }

    public void setContent(List<OrderBean> content) {
        this.content = content;
    }
}
