package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/7/23 0023.
 */
public class GiftBean extends BaseEntity{


    /**
     * redPackage : {"moneyIsReceiveFinish":0,"redPackage":{"id":1,"type":0,"userId":null,"moneyValue":6,"amount":300000,"balance":299584,"percents":"8000,1500,300,150,50","separates":"20,1000,2000,3000","min":1,"max":5000},"redPackageRecord":169}
     * dailyActivity : {"id":1,"activityTitle":"每日分享得红包","activityContent":"每日红包量","activityClassify":"REDPACKAGE","activityType":2,"finishValue":1,"targetValue":10}
     */

    private GitResultBean gitResult;
    private RedPackageBeanX redPackage;
    private DailyActivityBean dailyActivity;

    public GitResultBean getGitResult() {
        return gitResult;
    }

    public void setGitResult(GitResultBean gitResult) {
        this.gitResult = gitResult;
    }

    public RedPackageBeanX getRedPackage() {
        return redPackage;
    }

    public RedPackageBeanX getOutRedPackage() {
        return redPackage;
    }

    public void setRedPackage(RedPackageBeanX redPackage) {
        this.redPackage = redPackage;
    }

    public DailyActivityBean getDailyActivity() {
        return dailyActivity;
    }

    public void setDailyActivity(DailyActivityBean dailyActivity) {
        this.dailyActivity = dailyActivity;
    }

    public static class RedPackageBeanX extends BaseEntity{
        /**
         * moneyIsReceiveFinish : 0
         * redPackage : {"id":1,"type":0,"userId":null,"moneyValue":6,"amount":300000,"balance":299584,"percents":"8000,1500,300,150,50","separates":"20,1000,2000,3000","min":1,"max":5000}
         * redPackageRecord : 169
         */

        private String moneyIsReceiveFinish;
        private RedPackageBean redPackage;
        private String redPackageRecord;

        public String getMoneyIsReceiveFinish() {
            return moneyIsReceiveFinish;
        }

        public void setMoneyIsReceiveFinish(String moneyIsReceiveFinish) {
            this.moneyIsReceiveFinish = moneyIsReceiveFinish;
        }

        public RedPackageBean getRedPackage() {
            return redPackage;
        }

        public void setRedPackage(RedPackageBean redPackage) {
            this.redPackage = redPackage;
        }

        public String getRedPackageRecord() {
            return redPackageRecord;
        }

        public void setRedPackageRecord(String redPackageRecord) {
            this.redPackageRecord = redPackageRecord;
        }

        public static class RedPackageBean extends BaseEntity{
            /**
             * id : 1
             * type : 0
             * userId : null
             * moneyValue : 6
             * amount : 300000
             * balance : 299584
             * percents : 8000,1500,300,150,50
             * separates : 20,1000,2000,3000
             * min : 1
             * max : 5000
             */

            private int id;
            private int type;
            private Object userId;
            private double moneyValue;
            private int amount;
            private int balance;
            private String percents;
            private String separates;
            private int min;
            private int max;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public double getMoneyValue() {
                return moneyValue;
            }

            public void setMoneyValue(double moneyValue) {
                this.moneyValue = moneyValue;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public String getPercents() {
                return percents;
            }

            public void setPercents(String percents) {
                this.percents = percents;
            }

            public String getSeparates() {
                return separates;
            }

            public void setSeparates(String separates) {
                this.separates = separates;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }
        }
    }

    public static class GitResultBean extends BaseEntity{


        /**
         * name : 客服1
         * qrCodeImg : http://qdhrapvti.bkt.clouddn.com/pic2.png
         */

        private String name;
        private String qrCodeImg;
        private String exchangeCode;

        private String id;
        private String title;
        private String subtitle;
        private int moduleId;
        private String price;
        private String originalPrice;
        private String displayPic;
        private boolean isBuy;
        private String description;
        private String topicCover;
        private String displayCount;
        private int type;
        private int classification;
        private String belongsTo;
        private String courseSize;
        private int courseTotalSize;
        private boolean continueUpdating;
        private boolean isFree;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public int getModuleId() {
            return moduleId;
        }

        public void setModuleId(int moduleId) {
            this.moduleId = moduleId;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getDisplayPic() {
            return displayPic;
        }

        public void setDisplayPic(String displayPic) {
            this.displayPic = displayPic;
        }

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTopicCover() {
            return topicCover;
        }

        public void setTopicCover(String topicCover) {
            this.topicCover = topicCover;
        }

        public String getDisplayCount() {
            return displayCount;
        }

        public void setDisplayCount(String displayCount) {
            this.displayCount = displayCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getClassification() {
            return classification;
        }

        public void setClassification(int classification) {
            this.classification = classification;
        }

        public String getBelongsTo() {
            return belongsTo;
        }

        public void setBelongsTo(String belongsTo) {
            this.belongsTo = belongsTo;
        }

        public String getCourseSize() {
            return courseSize;
        }

        public void setCourseSize(String courseSize) {
            this.courseSize = courseSize;
        }

        public int getCourseTotalSize() {
            return courseTotalSize;
        }

        public void setCourseTotalSize(int courseTotalSize) {
            this.courseTotalSize = courseTotalSize;
        }

        public boolean isContinueUpdating() {
            return continueUpdating;
        }

        public void setContinueUpdating(boolean continueUpdating) {
            this.continueUpdating = continueUpdating;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        public String getExchangeCode() {
            return exchangeCode;
        }

        public void setExchangeCode(String exchangeCode) {
            this.exchangeCode = exchangeCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQrCodeImg() {
            return qrCodeImg;
        }

        public void setQrCodeImg(String qrCodeImg) {
            this.qrCodeImg = qrCodeImg;
        }
    }
    public static class DailyActivityBean extends BaseEntity{
        /**
         * id : 1
         * activityTitle : 每日分享得红包
         * activityContent : 每日红包量
         * activityClassify : REDPACKAGE
         * activityType : 2
         * finishValue : 1
         * targetValue : 10
         */

        private String id;
        private String activityTitle;
        private String activityContent;
        private String activityClassify;
        private int activityType;
        private int finishValue;
        private int targetValue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getActivityTitle() {
            return activityTitle;
        }

        public void setActivityTitle(String activityTitle) {
            this.activityTitle = activityTitle;
        }

        public String getActivityContent() {
            return activityContent;
        }

        public void setActivityContent(String activityContent) {
            this.activityContent = activityContent;
        }

        public String getActivityClassify() {
            return activityClassify;
        }

        public void setActivityClassify(String activityClassify) {
            this.activityClassify = activityClassify;
        }

        public int getActivityType() {
            return activityType;
        }

        public void setActivityType(int activityType) {
            this.activityType = activityType;
        }

        public int getFinishValue() {
            return finishValue;
        }

        public void setFinishValue(int finishValue) {
            this.finishValue = finishValue;
        }

        public int getTargetValue() {
            return targetValue;
        }

        public void setTargetValue(int targetValue) {
            this.targetValue = targetValue;
        }
    }
}
