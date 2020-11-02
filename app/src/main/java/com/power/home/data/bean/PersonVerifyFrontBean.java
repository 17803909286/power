package com.power.home.data.bean;

public class PersonVerifyFrontBean {

    private String realname;
    private String sex;
    private String nation;
    private String born;
    private String address;
    private String idcard;
    private String side;
    private String orderid;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Override
    public String toString() {
        return "PersonVerifyFrontBean{" +
                "realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", born='" + born + '\'' +
                ", address='" + address + '\'' +
                ", idcard='" + idcard + '\'' +
                ", side='" + side + '\'' +
                ", orderid=" + orderid +
                '}';
    }
}
