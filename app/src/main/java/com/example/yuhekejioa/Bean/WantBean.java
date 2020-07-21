package com.example.yuhekejioa.Bean;

import java.util.List;

public class WantBean {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"createBy":"caohuili","createTime":"2020-06-28 17:29","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":103,"title":"未命名-标题","taskNo":"2020062814060004","sponsor":"YH00001","sponsorIdentity":102,"wantFinishTiem":"2020-07-06 18:30","taskDescribe":"1, 招商手册内容的编辑 \n主要内容：\n① 我们是谁\n②我们是做什么的\n③我们所做的新零售的长远规划\n④我们定的客户群体是什么 \n⑤客户所面临的痛点是什么\n⑥我们将会为他解决什么问题\n⑦用什么方法  （列举）\nps：涉及到和客户见面的形式和洽谈的话术 ","options":"","auditComments":0,"state":null,"taskStatus":13,"auditStatus":1,"setBacks":100,"receive":"YHSC001","auditBy":null,"realTime":"2020-07-09 11:55","taskType":null,"inspected":null,"inspectedState":"不符合验收标准","stopReason":null,"isDelay":2,"delayReason":null,"inspectedUpdateTime":"2020-07-09 12:00","sysFilesSponsor":[{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"197E9BC002394EDE8C510DF3A88DBA85","url":"/profile/upload/2020/07/09/招商手册PPT.pdf","name":"招商手册PPT.pdf","fileSize":null},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"597D357323154F6DAFAFB9DD858B7C54","url":"/profile/upload/2020/07/09/合伙人招商流程.doc","name":"合伙人招商流程.doc","fileSize":null}],"sysFilesReceive":[],"addNickName":"曹会力","receiveNickName":"郎竞赛","auditName":null,"isFixed":null,"isUpdate":0,"receiveDept":"市场中心","deptId":104,"result":"修改项：\n把给客户展示用的PPT里，\n1、\u201c给合伙人股权\u201d项去掉了\n2、把痛点分析中的\u201c无人驾驶技术\u201d还有几个其他痛点又单独摘出来重点分析了，阐述带来的影响；\n\n在招商流程中强化了招商话术思路的八个阶段\n并详细写了对话话术和思路\n但是其中涉及到的具体的推广细则，仓运营运营细则，把问题都提炼出来了，待企划出详细的规则制定后填充进去","canDelay":1,"canUpdate":1,"canUpdateReceive":1,"statusStr":"验收未通过","identity":null,"taskDelay":null}
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
         * createBy : caohuili
         * createTime : 2020-06-28 17:29
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 103
         * title : 未命名-标题
         * taskNo : 2020062814060004
         * sponsor : YH00001
         * sponsorIdentity : 102
         * wantFinishTiem : 2020-07-06 18:30
         * taskDescribe : 1, 招商手册内容的编辑
         主要内容：
         ① 我们是谁
         ②我们是做什么的
         ③我们所做的新零售的长远规划
         ④我们定的客户群体是什么
         ⑤客户所面临的痛点是什么
         ⑥我们将会为他解决什么问题
         ⑦用什么方法  （列举）
         ps：涉及到和客户见面的形式和洽谈的话术
         * options :
         * auditComments : 0
         * state : null
         * taskStatus : 13
         * auditStatus : 1
         * setBacks : 100
         * receive : YHSC001
         * auditBy : null
         * realTime : 2020-07-09 11:55
         * taskType : null
         * inspected : null
         * inspectedState : 不符合验收标准
         * stopReason : null
         * isDelay : 2
         * delayReason : null
         * inspectedUpdateTime : 2020-07-09 12:00
         * sysFilesSponsor : [{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"197E9BC002394EDE8C510DF3A88DBA85","url":"/profile/upload/2020/07/09/招商手册PPT.pdf","name":"招商手册PPT.pdf","fileSize":null},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":"597D357323154F6DAFAFB9DD858B7C54","url":"/profile/upload/2020/07/09/合伙人招商流程.doc","name":"合伙人招商流程.doc","fileSize":null}]
         * sysFilesReceive : []
         * addNickName : 曹会力
         * receiveNickName : 郎竞赛
         * auditName : null
         * isFixed : null
         * isUpdate : 0
         * receiveDept : 市场中心
         * deptId : 104
         * result : 修改项：
         把给客户展示用的PPT里，
         1、“给合伙人股权”项去掉了
         2、把痛点分析中的“无人驾驶技术”还有几个其他痛点又单独摘出来重点分析了，阐述带来的影响；

         在招商流程中强化了招商话术思路的八个阶段
         并详细写了对话话术和思路
         但是其中涉及到的具体的推广细则，仓运营运营细则，把问题都提炼出来了，待企划出详细的规则制定后填充进去
         * canDelay : 1
         * canUpdate : 1
         * canUpdateReceive : 1
         * statusStr : 验收未通过
         * identity : null
         * taskDelay : null
         */

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
        private String realTime;
        private Object taskType;
        private int inspected;
        private String inspectedState;
        private Object stopReason;
        private int isDelay;
        private Object delayReason;
        private String inspectedUpdateTime;
        private String addNickName;
        private String receiveNickName;
        private Object auditName;
        private Object isFixed;
        private int isUpdate;
        private String receiveDept;
        private int deptId;
        private String result;
        private int canDelay;
        private int canUpdate;
        private int canUpdateReceive;
        private String statusStr;
        private Object identity;
        private Object taskDelay;
        private List<SysFilesSponsorBean> sysFilesSponsor;
        private List<?> sysFilesReceive;

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

        public String getRealTime() {
            return realTime;
        }

        public void setRealTime(String realTime) {
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

        public String getInspectedState() {
            return inspectedState;
        }

        public void setInspectedState(String inspectedState) {
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

        public String getInspectedUpdateTime() {
            return inspectedUpdateTime;
        }

        public void setInspectedUpdateTime(String inspectedUpdateTime) {
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

        public Object getIsFixed() {
            return isFixed;
        }

        public void setIsFixed(Object isFixed) {
            this.isFixed = isFixed;
        }

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
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

        public Object getIdentity() {
            return identity;
        }

        public void setIdentity(Object identity) {
            this.identity = identity;
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
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : 197E9BC002394EDE8C510DF3A88DBA85
             * url : /profile/upload/2020/07/09/招商手册PPT.pdf
             * name : 招商手册PPT.pdf
             * fileSize : null
             */

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
