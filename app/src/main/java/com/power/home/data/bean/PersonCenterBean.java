package com.power.home.data.bean;

import java.util.List;

/**
 * Created by zhp on 2020-03-18
 */
public class PersonCenterBean extends BaseEntity{


    private List<MenuBean> diamond;
    private List<MenuBean> ad;
    private List<MenuBean> menu;
    private boolean allRead;
    private String growthPromotionActive;
    private String growthPromotionIcon;
    private String userId;
    private String recCode;
    private String nickName;
    private String agentLevel;
    private String avatar;
    private String happinessVipSource;
    private int happinessVipSurplusDays;
    private boolean isHappinessVip;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHappinessVipSource() {
        return happinessVipSource;
    }

    public void setHappinessVipSource(String happinessVipSource) {
        this.happinessVipSource = happinessVipSource;
    }

    public boolean isAllRead() {
        return allRead;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getHappinessVipSurplusDays() {
        return happinessVipSurplusDays;
    }

    public void setHappinessVipSurplusDays(int happinessVipSurplusDays) {
        this.happinessVipSurplusDays = happinessVipSurplusDays;
    }

    public boolean isHappinessVip() {
        return isHappinessVip;
    }

    public void setHappinessVip(boolean happinessVip) {
        isHappinessVip = happinessVip;
    }

    public String getGrowthPromotionActive() {
        return growthPromotionActive;
    }

    public void setGrowthPromotionActive(String growthPromotionActive) {
        this.growthPromotionActive = growthPromotionActive;
    }

    public String getGrowthPromotionIcon() {
        return growthPromotionIcon;
    }

    public void setGrowthPromotionIcon(String growthPromotionIcon) {
        this.growthPromotionIcon = growthPromotionIcon;
    }

    public boolean getAllRead() {
        return allRead;
    }

    public void setAllRead(boolean allRead) {
        this.allRead = allRead;
    }

    public List<MenuBean> getDiamond() {
        return diamond;
    }

    public void setDiamond(List<MenuBean> diamond) {
        this.diamond = diamond;
    }

    public List<MenuBean> getAd() {
        return ad;
    }

    public void setAd(List<MenuBean> ad) {
        this.ad = ad;
    }

    public List<MenuBean> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuBean> menu) {
        this.menu = menu;
    }

    public static class MenuBean extends BaseEntity{
        /**
         * isDelete : false
         * createTime : 2020-02-24 06:53:13
         * updateTime : 2020-02-24 06:53:15
         * id : 10047
         * title : 输入兑换码
         * subtitle : 输入兑换码
         * displayPic : http://www.test.image.future.sdgyjyzx.com/%E8%BE%93%E5%85%A5%E5%85%91%E6%8D%A2%E7%A0%81%403x.png
         * type : null
         * userType : null
         * forwardType : 1
         * forwardAddress : {"router":"convert","id":null}
         * displayCount : null
         * currentPrice : null
         * sort : 1
         */

        private String isDelete;
        private String createTime;
        private String updateTime;
        private String id;
        private String title;
        private String subtitle;
        private String displayPic;
        private String type;
        private String userType;
        private String forwardType;
        private String forwardAddress;
        private String displayCount;
        private String currentPrice;
        private String isNeedLogin;
        private int sort;

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
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

        public String getDisplayPic() {
            return displayPic;
        }

        public void setDisplayPic(String displayPic) {
            this.displayPic = displayPic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getForwardType() {
            return forwardType;
        }

        public void setForwardType(String forwardType) {
            this.forwardType = forwardType;
        }

        public String getForwardAddress() {
            return forwardAddress;
        }

        public void setForwardAddress(String forwardAddress) {
            this.forwardAddress = forwardAddress;
        }

        public String getDisplayCount() {
            return displayCount;
        }

        public void setDisplayCount(String displayCount) {
            this.displayCount = displayCount;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getIsNeedLogin() {
            return isNeedLogin;
        }

        public void setIsNeedLogin(String isNeedLogin) {
            this.isNeedLogin = isNeedLogin;
        }
    }
}
