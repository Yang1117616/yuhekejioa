package com.example.yuhekejioa.Bean;

import java.util.List;

public class Mainbean {


    /**
     * msg : 操作成功
     * code : 200
     * data : [{"createBy":"SLYF001","createTime":"2020-07-25 09:50","updateBy":null,"updateTime":null,"remark":null,"params":{},"msgId":863,"id":863,"type":"新任务单","receive":"SLYF008","content":"您有新的任务单，请及时查看","isRead":0,"taskNo":"2020072509200018","taskId":629,"taskStatus":2,"taskTitle":"22222222222","isUrgent":0,"isFixed":1},{"createBy":"","createTime":"2020-07-24 17:50","updateBy":null,"updateTime":null,"remark":null,"params":{},"msgId":833,"id":833,"type":"新任务单","receive":"SLYF008","content":"您有新的任务单，请及时查看","isRead":1,"taskNo":"2020072409500125","taskId":606,"taskStatus":2,"taskTitle":"2020年7月24号17点46分固定任务单测试","isUrgent":0,"isFixed":1},{"createBy":"SLYF001","createTime":"2020-07-24 17:40","updateBy":null,"updateTime":null,"remark":null,"params":{},"msgId":823,"id":823,"type":"加急任务单","receive":"SLYF008","content":"您有一条加急任务单需要紧急处理，请确认","isRead":1,"taskNo":"2020072409500115","taskId":596,"taskStatus":2,"taskTitle":"测试附件2","isUrgent":1,"isFixed":0}]
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
         * createBy : SLYF001
         * createTime : 2020-07-25 09:50
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * msgId : 863
         * id : 863
         * type : 新任务单
         * receive : SLYF008
         * content : 您有新的任务单，请及时查看
         * isRead : 0
         * taskNo : 2020072509200018
         * taskId : 629
         * taskStatus : 2
         * taskTitle : 22222222222
         * isUrgent : 0
         * isFixed : 1
         */

        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int msgId;
        private int id;
        private String type;
        private String receive;
        private String content;
        private int isRead;
        private String taskNo;
        private int taskId;
        private int taskStatus;
        private String taskTitle;
        private int isUrgent;
        private int isFixed;

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

        public int getMsgId() {
            return msgId;
        }

        public void setMsgId(int msgId) {
            this.msgId = msgId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReceive() {
            return receive;
        }

        public void setReceive(String receive) {
            this.receive = receive;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getTaskNo() {
            return taskNo;
        }

        public void setTaskNo(String taskNo) {
            this.taskNo = taskNo;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(int taskStatus) {
            this.taskStatus = taskStatus;
        }

        public String getTaskTitle() {
            return taskTitle;
        }

        public void setTaskTitle(String taskTitle) {
            this.taskTitle = taskTitle;
        }

        public int getIsUrgent() {
            return isUrgent;
        }

        public void setIsUrgent(int isUrgent) {
            this.isUrgent = isUrgent;
        }

        public int getIsFixed() {
            return isFixed;
        }

        public void setIsFixed(int isFixed) {
            this.isFixed = isFixed;
        }

        public static class ParamsBean {
        }
    }
}
