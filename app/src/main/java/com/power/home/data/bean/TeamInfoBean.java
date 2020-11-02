package com.power.home.data.bean;

/**
 * Created by ZHP on 2020/4/15 0015.
 */
public class TeamInfoBean extends BaseEntity {

    /**
     * teamTotalCount : 7
     * surplusQuota : 10
     * usedQuota : 20
     * myVip : {"count":0}
     * mySchoolMaster : {"count":0}
     * myAmbassador : {"count":0}
     */

    private String teamTotalCount;
    private String surplusQuota;
    private String usedQuota;
    private MyVipBean myVip;
    private MySchoolMasterBean mySchoolMaster;
    private MyAmbassadorBean myAmbassador;

    public String getTeamTotalCount() {
        return teamTotalCount;
    }

    public void setTeamTotalCount(String teamTotalCount) {
        this.teamTotalCount = teamTotalCount;
    }

    public String getSurplusQuota() {
        return surplusQuota;
    }

    public void setSurplusQuota(String surplusQuota) {
        this.surplusQuota = surplusQuota;
    }

    public String getUsedQuota() {
        return usedQuota;
    }

    public void setUsedQuota(String usedQuota) {
        this.usedQuota = usedQuota;
    }

    public MyVipBean getMyVip() {
        return myVip;
    }

    public void setMyVip(MyVipBean myVip) {
        this.myVip = myVip;
    }

    public MySchoolMasterBean getMySchoolMaster() {
        return mySchoolMaster;
    }

    public void setMySchoolMaster(MySchoolMasterBean mySchoolMaster) {
        this.mySchoolMaster = mySchoolMaster;
    }

    public MyAmbassadorBean getMyAmbassador() {
        return myAmbassador;
    }

    public void setMyAmbassador(MyAmbassadorBean myAmbassador) {
        this.myAmbassador = myAmbassador;
    }

    public static class MyVipBean extends BaseEntity {
        /**
         * count : 0
         */

        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

    public static class MySchoolMasterBean extends BaseEntity {
        /**
         * count : 0
         */

        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

    public static class MyAmbassadorBean extends BaseEntity {
        /**
         * count : 0
         */

        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
