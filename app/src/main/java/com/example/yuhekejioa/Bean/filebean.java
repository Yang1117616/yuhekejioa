package com.example.yuhekejioa.Bean;

public class filebean {
    private String filename;
    private String fileram;

    public filebean() {
        super();
    }

    public filebean(String filename, String fileram) {
        this.filename = filename;
        this.fileram = fileram;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileram() {
        return fileram;
    }

    public void setFileram(String fileram) {
        this.fileram = fileram;
    }

    @Override
    public String toString() {
        return "filebean{" +
                "filename='" + filename + '\'' +
                ", fileram='" + fileram + '\'' +
                '}';
    }
}
