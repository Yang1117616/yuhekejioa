package com.example.yuhekejioa.Bean;

public class StatusBean {
    private String name;

    public StatusBean() {
        super();
    }

    public StatusBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StatusBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
