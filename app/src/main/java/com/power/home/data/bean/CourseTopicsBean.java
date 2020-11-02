package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/4/29 0029.
 */
public class CourseTopicsBean extends BaseEntity{

    /**
     * isDelete : false
     * createTime : 2020-02-13 20:15:20
     * updateTime : 2020-02-13 20:15:22
     * id : 10001
     * title : 如何突现突袭，越轨发展
     * subtitle : 如何来吸引外界各方支持
     * displayCount : 5000
     * price : 88.00
     * displayPic : null
     * description : null
     * sort : 2
     * status : true
     */

    private boolean isDelete;
    private String createTime;
    private String updateTime;
    private String id;
    private String title;
    private String subtitle;
    private int displayCount;
    private String price;
    private String displayPic;
    private String description;
    private int sort;
    private boolean status;
    private String type;
    private boolean isBuy;
    private String shareImg;
    private String shareUrl;
    private String posterImg;
    private String shareTitle;
    private String shareSubtitle;
    private String courseSize;
    private String courseTotalSize;
    private boolean continueUpdating;
    private boolean isFree;
    private boolean isDiscount;
    private String originalPrice;

    private Object topicCover;
    private int moduleId;
    private int classification;
    private String belongsTo;

    /**
     * id : 10080
     * title : 状元专辑副标题
     * subtitle : 状元专辑标题
     * moduleId : 10027
     * price : 298.00
     * originalPrice : 298.00
     * displayPic : http://www.test.image.wlfxdata.com/20200115-105050.png
     * isBuy : null
     * description : 描述
     * topicCover : http://www.test.image.wlfxdata.com/shangdaowufenzhong34-2.png
     * displayCount : null
     * type : 1
     * classification : 5
     * belongsTo : null
     * courseSize : null
     * courseTotalSize : 35
     * continueUpdating : false
     * vipFlag : false
     * isFree : false
     */
    private boolean vipFlag;

    public boolean isVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(boolean vipFlag) {
        this.vipFlag = vipFlag;
    }


    public String getCourseTotalSize() {
        return courseTotalSize;
    }

    public void setCourseTotalSize(String courseTotalSize) {
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

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getPosterImg() {
        return posterImg;
    }

    public void setPosterImg(String posterImg) {
        this.posterImg = posterImg;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareSubtitle() {
        return shareSubtitle;
    }

    public void setShareSubtitle(String shareSubtitle) {
        this.shareSubtitle = shareSubtitle;
    }

    public String getCourseSize() {
        return courseSize;
    }

    public void setCourseSize(String courseSize) {
        this.courseSize = courseSize;
    }

    public Object getTopicCover() {
        return topicCover;
    }

    public void setTopicCover(Object topicCover) {
        this.topicCover = topicCover;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
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




    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

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

    public int getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(int displayCount) {
        this.displayCount = displayCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
