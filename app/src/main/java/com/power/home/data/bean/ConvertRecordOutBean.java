package com.power.home.data.bean;

import java.util.List;

/**
 * Created by zhp on 2020-03-10
 */
public class ConvertRecordOutBean extends BaseEntity {


    private String totalElements;
    private List<ConvertRecordBean> content;


    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public List<ConvertRecordBean> getContent() {
        return content;
    }

    public void setContent(List<ConvertRecordBean> content) {
        this.content = content;
    }
}
