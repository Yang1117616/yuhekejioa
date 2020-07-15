package com.example.yuhekejioa.Bean;

import java.util.List;

public class DeterminBean {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"yangyinglin","createTime":"2020-06-30 17:50:59","updateBy":"yangyinglin","updateTime":"2020-06-30 17:50:59","remark":null,"params":{},"id":276,"taskNo":"2020063009130033","sponsor":"YHYF008","sponsorIdentity":101,"wantFinishTiem":"2020-06-30 17:50:00","taskDescribe":"qqqqq","options":"5DDB741CC1D54A36B75FECC965DB185C,31ED68B27E6A44D2B00AD6CB89C038FD","auditComments":0,"state":null,"taskStatus":3,"auditStatus":0,"setBacks":0,"receive":"YHYF025","auditBy":null,"realTime":null,"taskType":null,"inspected":2,"inspectedState":null,"stopReason":null,"isDelay":null,"delayReason":null,"sysFilesSponsor":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"5DDB741CC1D54A36B75FECC965DB185C","url":"/profile/upload/2020/06/30/c085bcd48bae50c24506bd5a07decda8.docx","name":"1、欢迎使用 WPS Office.docx","fileSize":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"31ED68B27E6A44D2B00AD6CB89C038FD","url":"/profile/upload/2020/06/30/7501bf3450e96fab6846ba98558676ba.docx","name":"1、欢迎使用 WPS Office.docx","fileSize":null}],"sysFilesReceive":[],"addNickName":"杨英林","receiveNickName":"南梦华","auditName":"","receiveDept":"研发中心"}
     */

    private String msg;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : yangyinglin
         * createTime : 2020-06-30 17:50:59
         * updateBy : yangyinglin
         * updateTime : 2020-06-30 17:50:59
         * remark : null
         * params : {}
         * id : 276
         * taskNo : 2020063009130033
         * sponsor : YHYF008
         * sponsorIdentity : 101
         * wantFinishTiem : 2020-06-30 17:50:00
         * taskDescribe : qqqqq
         * options : 5DDB741CC1D54A36B75FECC965DB185C,31ED68B27E6A44D2B00AD6CB89C038FD
         * auditComments : 0
         * state : null
         * taskStatus : 3
         * auditStatus : 0
         * setBacks : 0
         * receive : YHYF025
         * auditBy : null
         * realTime : null
         * taskType : null
         * inspected : 2
         * inspectedState : null
         * stopReason : null
         * isDelay : null
         * delayReason : null
         * sysFilesSponsor : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"5DDB741CC1D54A36B75FECC965DB185C","url":"/profile/upload/2020/06/30/c085bcd48bae50c24506bd5a07decda8.docx","name":"1、欢迎使用 WPS Office.docx","fileSize":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"31ED68B27E6A44D2B00AD6CB89C038FD","url":"/profile/upload/2020/06/30/7501bf3450e96fab6846ba98558676ba.docx","name":"1、欢迎使用 WPS Office.docx","fileSize":null}]
         * sysFilesReceive : []
         * addNickName : 杨英林
         * receiveNickName : 南梦华
         * auditName :
         * receiveDept : 研发中心
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String taskNo;
        private String sponsor;
        private int sponsorIdentity;
        private String wantFinishTiem;
        private String taskDescribe;
        private String options;
        private int auditComments;
        private Object state;
        private int taskStatus;
        private int auditStatus;
        private int setBacks;
        private String receive;
        private Object auditBy;
        private Object realTime;
        private Object taskType;
        private int inspected;
        private Object inspectedState;
        private Object stopReason;
        private Object isDelay;
        private Object delayReason;
        private String addNickName;
        private String receiveNickName;
        private String auditName;
        private String receiveDept;
        private List<SysFilesSponsorBean> sysFilesSponsor;
        private List<?> sysFilesReceive;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
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

        public String getSponsor() {
            return sponsor;
        }

        public void setSponsor(String sponsor) {
            this.sponsor = sponsor;
        }

        public int getSponsorIdentity() {
            return sponsorIdentity;
        }

        public void setSponsorIdentity(int sponsorIdentity) {
            this.sponsorIdentity = sponsorIdentity;
        }

        public String getWantFinishTiem() {
            return wantFinishTiem;
        }

        public void setWantFinishTiem(String wantFinishTiem) {
            this.wantFinishTiem = wantFinishTiem;
        }

        public String getTaskDescribe() {
            return taskDescribe;
        }

        public void setTaskDescribe(String taskDescribe) {
            this.taskDescribe = taskDescribe;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public int getAuditComments() {
            return auditComments;
        }

        public void setAuditComments(int auditComments) {
            this.auditComments = auditComments;
        }

        public Object getState() {
            return state;
        }

        public void setState(Object state) {
            this.state = state;
        }

        public int getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(int taskStatus) {
            this.taskStatus = taskStatus;
        }

        public int getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(int auditStatus) {
            this.auditStatus = auditStatus;
        }

        public int getSetBacks() {
            return setBacks;
        }

        public void setSetBacks(int setBacks) {
            this.setBacks = setBacks;
        }

        public String getReceive() {
            return receive;
        }

        public void setReceive(String receive) {
            this.receive = receive;
        }

        public Object getAuditBy() {
            return auditBy;
        }

        public void setAuditBy(Object auditBy) {
            this.auditBy = auditBy;
        }

        public Object getRealTime() {
            return realTime;
        }

        public void setRealTime(Object realTime) {
            this.realTime = realTime;
        }

        public Object getTaskType() {
            return taskType;
        }

        public void setTaskType(Object taskType) {
            this.taskType = taskType;
        }

        public int getInspected() {
            return inspected;
        }

        public void setInspected(int inspected) {
            this.inspected = inspected;
        }

        public Object getInspectedState() {
            return inspectedState;
        }

        public void setInspectedState(Object inspectedState) {
            this.inspectedState = inspectedState;
        }

        public Object getStopReason() {
            return stopReason;
        }

        public void setStopReason(Object stopReason) {
            this.stopReason = stopReason;
        }

        public Object getIsDelay() {
            return isDelay;
        }

        public void setIsDelay(Object isDelay) {
            this.isDelay = isDelay;
        }

        public Object getDelayReason() {
            return delayReason;
        }

        public void setDelayReason(Object delayReason) {
            this.delayReason = delayReason;
        }

        public String getAddNickName() {
            return addNickName;
        }

        public void setAddNickName(String addNickName) {
            this.addNickName = addNickName;
        }

        public String getReceiveNickName() {
            return receiveNickName;
        }

        public void setReceiveNickName(String receiveNickName) {
            this.receiveNickName = receiveNickName;
        }

        public String getAuditName() {
            return auditName;
        }

        public void setAuditName(String auditName) {
            this.auditName = auditName;
        }

        public String getReceiveDept() {
            return receiveDept;
        }

        public void setReceiveDept(String receiveDept) {
            this.receiveDept = receiveDept;
        }

        public List<SysFilesSponsorBean> getSysFilesSponsor() {
            return sysFilesSponsor;
        }

        public void setSysFilesSponsor(List<SysFilesSponsorBean> sysFilesSponsor) {
            this.sysFilesSponsor = sysFilesSponsor;
        }

        public List<?> getSysFilesReceive() {
            return sysFilesReceive;
        }

        public void setSysFilesReceive(List<?> sysFilesReceive) {
            this.sysFilesReceive = sysFilesReceive;
        }

        public static class ParamsBean {
        }

        public static class SysFilesSponsorBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : 5DDB741CC1D54A36B75FECC965DB185C
             * url : /profile/upload/2020/06/30/c085bcd48bae50c24506bd5a07decda8.docx
             * name : 1、欢迎使用 WPS Office.docx
             * fileSize : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanX params;
            private String id;
            private String url;
            private String name;
            private Object fileSize;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
                this.params = params;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getFileSize() {
                return fileSize;
            }

            public void setFileSize(Object fileSize) {
                this.fileSize = fileSize;
            }

            public static class ParamsBeanX {
            }
        }
    }
}
