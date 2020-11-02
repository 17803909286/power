package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/2/26 0026.
 */
public class ConvertRecordBean extends BaseEntity{


    /**
     * recordId : 3006
     * userId : 10011
     * useType : 动力营
     * useTime : 2020年03月09日 17:20
     * isDelete : null
     */

    private int recordId;
    private int userId;
    private String useType;
    private String useTime;
    private String isDelete;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
