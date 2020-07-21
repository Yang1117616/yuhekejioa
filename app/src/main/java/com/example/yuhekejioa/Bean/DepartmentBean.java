package com.example.yuhekejioa.Bean;

public class DepartmentBean {
    private int deptId;
    private String deptName;

    public DepartmentBean(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public DepartmentBean() {
        super();
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
