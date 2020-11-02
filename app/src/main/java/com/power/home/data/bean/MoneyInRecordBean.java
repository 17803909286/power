package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/2/26 0026.
 */
public class MoneyInRecordBean extends BaseEntity{


    /**
     * id : 30000
     * prefixName : 购买用户：
     * incomeName : 增长营零售
     * nickName : 182****1469
     * userId : 10002
     * incomeAmount : +398.00
     * incomeTime : 2020-03-28 19:10:43
     * showDetail : false
     * targetId : null
     * topicName : null
     * topicPrice : null
     */

    private String id;
    private String prefixName;
    private String incomeName;
    private String nickName;
    private String userId;
    private String incomeAmount;
    private String incomeTime;
    private String showDetail;
    private String targetId;
    private String topicName;
    private String topicPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(String incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(String incomeTime) {
        this.incomeTime = incomeTime;
    }

    public String getShowDetail() {
        return showDetail;
    }

    public void setShowDetail(String showDetail) {
        this.showDetail = showDetail;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicPrice() {
        return topicPrice;
    }

    public void setTopicPrice(String topicPrice) {
        this.topicPrice = topicPrice;
    }
}
