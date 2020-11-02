package com.power.home.data.bean;

import java.util.List;

public class AreaBean {

    /**
     * provinceId : 1
     * provinceCode : 110000
     * provinceName : 北京
     * state : 0
     * zoneCityList : [{"cityId":1,"cityCode":"110100","state":1,"cityName":"北京市"}]
     */

    private int provinceId;
    private String provinceCode;
    private String provinceName;
    private int state;
    private List<ZoneCityListBean> zoneCities;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ZoneCityListBean> getZoneCityList() {
        return zoneCities;
    }

    public void setZoneCityList(List<ZoneCityListBean> zoneCityList) {
        this.zoneCities = zoneCityList;
    }

    public static class ZoneCityListBean {
        /**
         * cityId : 1
         * cityCode : 110100
         * state : 1
         * cityName : 北京市
         */

        private int cityId;
        private String cityCode;
        private int state;
        private String cityName;

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }
}
