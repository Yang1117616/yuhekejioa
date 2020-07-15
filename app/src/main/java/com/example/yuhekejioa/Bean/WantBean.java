package com.example.yuhekejioa.Bean;

import java.util.List;

public class WantBean {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":"yangyinglin","createTime":"2020-07-14 11:20","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":296,"title":"未命名-标题","taskNo":"2020071411160002","sponsor":"YHYF008","sponsorIdentity":101,"wantFinishTiem":"2020-07-14 11:20","taskDescribe":"测试","options":"76AC53D1B353416F8507DB01177D78E5","auditComments":0,"state":null,"taskStatus":2,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":null,"taskType":null,"inspected":2,"inspectedState":null,"stopReason":null,"isDelay":0,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"76AC53D1B353416F8507DB01177D78E5","url":"/profile/upload/2020/07/14/a75a1c1b87bf16b7436542925e6efa53.docx","name":"2020年7月个人目标和以及自我培训计划.docx","fileSize":"20.38KB"}],"sysFilesReceive":[],"addNickName":"杨英林","receiveNickName":"张帆","auditName":null,"receiveDept":"研发中心","deptId":103,"result":"","canDelay":1,"canUpdate":1,"canUpdateReceive":1,"statusStr":"待确认","taskDelay":null}
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
         * createTime : 2020-07-14 11:20
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 296
         * title : 未命名-标题
         * taskNo : 2020071411160002
         * sponsor : YHYF008
         * sponsorIdentity : 101
         * wantFinishTiem : 2020-07-14 11:20
         * taskDescribe : 测试
         * options : 76AC53D1B353416F8507DB01177D78E5
         * auditComments : 0
         * state : null
         * taskStatus : 2
         * auditStatus : 1
         * setBacks : 0
         * receive : YHYF024
         * auditBy : null
         * realTime : null
         * taskType : null
         * inspected : 2
         * inspectedState : null
         * stopReason : null
         * isDelay : 0
         * delayReason : null
         * inspectedUpdateTime : null
         * sysFilesSponsor : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"76AC53D1B353416F8507DB01177D78E5","url":"/profile/upload/2020/07/14/a75a1c1b87bf16b7436542925e6efa53.docx","name":"2020年7月个人目标和以及自我培训计划.docx","fileSize":"20.38KB"}]
         * sysFilesReceive : []
         * addNickName : 杨英林
         * receiveNickName : 张帆
         * auditName : null
         * receiveDept : 研发中心
         * deptId : 103
         * result :
         * canDelay : 1
         * canUpdate : 1
         * canUpdateReceive : 1
         * statusStr : 待确认
         * taskDelay : null
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int id;
        private String title;
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
        private int isDelay;
        private Object delayReason;
        private Object inspectedUpdateTime;
        private String addNickName;
        private String receiveNickName;
        private Object auditName;
        private String receiveDept;
        private int deptId;
        private String result;
        private int canDelay;
        private int canUpdate;
        private int canUpdateReceive;
        private String statusStr;
        private Object taskDelay;
        private List<SysFilesSponsorBean> sysFilesSponsor;
        private List<?> sysFilesReceive;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getIsDelay() {
            return isDelay;
        }

        public void setIsDelay(int isDelay) {
            this.isDelay = isDelay;
        }

        public Object getDelayReason() {
            return delayReason;
        }

        public void setDelayReason(Object delayReason) {
            this.delayReason = delayReason;
        }

        public Object getInspectedUpdateTime() {
            return inspectedUpdateTime;
        }

        public void setInspectedUpdateTime(Object inspectedUpdateTime) {
            this.inspectedUpdateTime = inspectedUpdateTime;
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

        public Object getAuditName() {
            return auditName;
        }

        public void setAuditName(Object auditName) {
            this.auditName = auditName;
        }

        public String getReceiveDept() {
            return receiveDept;
        }

        public void setReceiveDept(String receiveDept) {
            this.receiveDept = receiveDept;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getCanDelay() {
            return canDelay;
        }

        public void setCanDelay(int canDelay) {
            this.canDelay = canDelay;
        }

        public int getCanUpdate() {
            return canUpdate;
        }

        public void setCanUpdate(int canUpdate) {
            this.canUpdate = canUpdate;
        }

        public int getCanUpdateReceive() {
            return canUpdateReceive;
        }

        public void setCanUpdateReceive(int canUpdateReceive) {
            this.canUpdateReceive = canUpdateReceive;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }

        public Object getTaskDelay() {
            return taskDelay;
        }

        public void setTaskDelay(Object taskDelay) {
            this.taskDelay = taskDelay;
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
             * id : 76AC53D1B353416F8507DB01177D78E5
             * url : /profile/upload/2020/07/14/a75a1c1b87bf16b7436542925e6efa53.docx
             * name : 2020年7月个人目标和以及自我培训计划.docx
             * fileSize : 20.38KB
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
            private String fileSize;

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

            public String getFileSize() {
                return fileSize;
            }

            public void setFileSize(String fileSize) {
                this.fileSize = fileSize;
            }

            public static class ParamsBeanX {
            }
        }
    }
}
