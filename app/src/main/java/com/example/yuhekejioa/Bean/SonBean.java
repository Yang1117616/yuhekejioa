package com.example.yuhekejioa.Bean;

import java.io.Serializable;

public class SonBean implements Serializable {
    private String name;
    private String userNo;

    public SonBean() {
        super();
    }

    public SonBean(String name, String userNo) {
        this.name = name;
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "SonBean{" +
                "name='" + name + '\'' +
                ", userNo='" + userNo + '\'' +
                '}';
    }
}
