package com.example.yuhekejioa.Bean;

import java.util.List;

public class DailyBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":"YHYF025","createTime":"2020-07-02 09:16:45","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":174,"taskNo":"2020070208260007","jobContent":"接收任务","progress":0,"fileId":null}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : YHYF025
         * createTime : 2020-07-02 09:16:45
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 174
         * taskNo : 2020070208260007
         * jobContent : 接收任务
         * progress : 0
         * fileId : null
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String taskNo;
        private String jobContent;
        private int progress;
        private Object fileId;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTaskNo() {
            return taskNo;
        }

        public void setTaskNo(String taskNo) {
            this.taskNo = taskNo;
        }

        public String getJobContent() {
            return jobContent;
        }

        public void setJobContent(String jobContent) {
            this.jobContent = jobContent;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public Object getFileId() {
            return fileId;
        }

        public void setFileId(Object fileId) {
            this.fileId = fileId;
        }

        public static class ParamsBean {
        }
    }
}
