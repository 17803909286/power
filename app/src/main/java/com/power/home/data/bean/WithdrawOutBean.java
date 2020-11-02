package com.power.home.data.bean;

import java.util.List;

/**
 * Created by zhp on 2020-03-10
 */
public class WithdrawOutBean extends BaseEntity{

    private String totalElements;
    private List<WithDrawalRecordBean> content;

    public String getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    public List<WithDrawalRecordBean> getContent() {
        return content;
    }

    public void setContent(List<WithDrawalRecordBean> content) {
        this.content = content;
    }
}
