package com.power.home.data.bean;

public class PersonVerifyBackBean {
    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    private String begin;
    private String department;
    private String end;
    private String side;
    private String orderid;

    @Override
    public String toString() {
        return "PersonVerifyBackBean{" +
                "begin='" + begin + '\'' +
                ", department='" + department + '\'' +
                ", end='" + end + '\'' +
                ", side='" + side + '\'' +
                ", orderid=" + orderid +
                '}';
    }
}
