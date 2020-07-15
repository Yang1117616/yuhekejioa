package com.example.yuhekejioa.Bean;

import java.util.List;

public class MeInitiateBean {


    /**
     * msg : 操作成功
     * code : 200
     * data : [{"searchValue":null,"createBy":"yangyinglin","createTime":"2020-07-14 11:20","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":296,"title":"未命名-标题","taskNo":"2020071411160002","sponsor":"YHYF008","sponsorIdentity":101,"wantFinishTiem":"2020-07-14 11:20","taskDescribe":"测试","options":"76AC53D1B353416F8507DB01177D78E5","auditComments":0,"state":null,"taskStatus":2,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":null,"taskType":null,"inspected":2,"inspectedState":null,"stopReason":null,"isDelay":0,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":null,"receiveNickName":"张帆","auditName":null,"receiveDept":null,"deptId":null,"result":null,"canDelay":0,"canUpdate":1,"canUpdateReceive":1,"statusStr":"待确认","taskDelay":null},{"searchValue":null,"createBy":"yangyinglin","createTime":"2020-07-13 10:48","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":286,"title":"未命名-标题","taskNo":"2020071309250005","sponsor":"YHYF008","sponsorIdentity":101,"wantFinishTiem":"2020-07-13 10:48","taskDescribe":"gfffd","options":null,"auditComments":0,"state":null,"taskStatus":2,"auditStatus":1,"setBacks":0,"receive":"YHYF025","auditBy":null,"realTime":null,"taskType":null,"inspected":2,"inspectedState":null,"stopReason":null,"isDelay":0,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":null,"receiveNickName":"南梦华","auditName":null,"receiveDept":null,"deptId":null,"result":null,"canDelay":0,"canUpdate":1,"canUpdateReceive":1,"statusStr":"待确认","taskDelay":null},{"searchValue":null,"createBy":"yangyinglin","createTime":"2020-07-13 10:13","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":285,"title":"未命名-标题","taskNo":"2020071309250004","sponsor":"YHYF008","sponsorIdentity":101,"wantFinishTiem":"2020-07-13 10:13","taskDescribe":"测试一下","options":null,"auditComments":0,"state":null,"taskStatus":18,"auditStatus":1,"setBacks":0,"receive":"YHYF025","auditBy":null,"realTime":null,"taskType":null,"inspected":2,"inspectedState":null,"stopReason":null,"isDelay":0,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":null,"receiveNickName":"南梦华","auditName":null,"receiveDept":null,"deptId":null,"result":null,"canDelay":0,"canUpdate":0,"canUpdateReceive":1,"statusStr":"修改待确认","taskDelay":null}]
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
         * sysFilesSponsor : null
         * sysFilesReceive : null
         * addNickName : null
         * receiveNickName : 张帆
         * auditName : null
         * receiveDept : null
         * deptId : null
         * result : null
         * canDelay : 0
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
        private Object sysFilesSponsor;
        private Object sysFilesReceive;
        private Object addNickName;
        private String receiveNickName;
        private Object auditName;
        private Object receiveDept;
        private Object deptId;
        private Object result;
        private int canDelay;
        private int canUpdate;
        private int canUpdateReceive;
        private String statusStr;
        private Object taskDelay;

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

        public Object getSysFilesSponsor() {
            return sysFilesSponsor;
        }

        public void setSysFilesSponsor(Object sysFilesSponsor) {
            this.sysFilesSponsor = sysFilesSponsor;
        }

        public Object getSysFilesReceive() {
            return sysFilesReceive;
        }

        public void setSysFilesReceive(Object sysFilesReceive) {
            this.sysFilesReceive = sysFilesReceive;
        }

        public Object getAddNickName() {
            return addNickName;
        }

        public void setAddNickName(Object addNickName) {
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

        public Object getReceiveDept() {
            return receiveDept;
        }

        public void setReceiveDept(Object receiveDept) {
            this.receiveDept = receiveDept;
        }

        public Object getDeptId() {
            return deptId;
        }

        public void setDeptId(Object deptId) {
            this.deptId = deptId;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
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

        public static class ParamsBean {
        }
    }
}
