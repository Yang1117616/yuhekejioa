package com.example.yuhekejioa.Adapter;

import java.io.Serializable;

public class FatherBean implements Serializable {
    private String name;
    private int deptId;

    public FatherBean() {
        super();
    }

    public FatherBean(String name, int deptId) {
        this.name = name;
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "FatherBean{" +
                "name='" + name + '\'' +
                ", deptId=" + deptId +
                '}';
    }
}
