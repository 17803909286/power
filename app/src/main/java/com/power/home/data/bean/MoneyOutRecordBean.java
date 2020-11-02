package com.power.home.data.bean;

import java.util.List;

/**
 * Created by ZHP on 2020/2/26 0026.
 */
public class MoneyOutRecordBean extends BaseEntity{

    private String totalElements;
    private List<MoneyInRecordBean> content;

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public List<MoneyInRecordBean> getContent() {
        return content;
    }

    public void setContent(List<MoneyInRecordBean> content) {
        this.content = content;
    }
}
