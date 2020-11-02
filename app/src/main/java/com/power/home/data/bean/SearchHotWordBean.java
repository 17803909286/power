package com.power.home.data.bean;

/**
 * Created by XWL on 2020/3/6.
 * Description:
 */
public class SearchHotWordBean {

    /**
     * isDelete : false
     * createTime : 2020-02-14 00:00:00
     * updateTime : 2020-02-14 00:00:00
     * id : 10000
     * wordsName : 热词1
     * sort : 1
     * count : 1
     * status : true
     * hasResult : true
     */

    private boolean isDelete;
    private String createTime;
    private String updateTime;
    private int id;
    private String wordsName;
    private int sort;
    private int count;
    private boolean status;
    private boolean hasResult;

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordsName() {
        return wordsName;
    }

    public void setWordsName(String wordsName) {
        this.wordsName = wordsName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isHasResult() {
        return hasResult;
    }

    public void setHasResult(boolean hasResult) {
        this.hasResult = hasResult;
    }
}
