package com.example.yuhekejioa.Bean;

public class PendingBean {
    private String name;
    private String filearm;

    public PendingBean() {
        super();
    }

    public PendingBean(String name, String filearm) {
        this.name = name;
        this.filearm = filearm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilearm() {
        return filearm;
    }

    public void setFilearm(String filearm) {
        this.filearm = filearm;
    }

    @Override
    public String toString() {
        return "PendingBean{" +
                "name='" + name + '\'' +
                ", filearm='" + filearm + '\'' +
                '}';
    }
}
