package com.example.yuhekejioa.Bean;

import java.util.List;

public class SearchforBean {


    /**
     * msg : 操作成功
     * code : 200
     * data : {"total":3,"list":[{"createBy":"lvbohan","createTime":"2020-07-24 08:56","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"title":"10","taskNo":"2020072408490011","sponsor":"YHYF001","sponsorIdentity":100,"wantFinishTiem":"2020-07-24 11:05","taskDescribe":"10","options":null,"auditComments":0,"state":null,"taskStatus":18,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":null,"taskType":null,"inspected":null,"inspectedState":null,"stopReason":null,"isDelay":2,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":"吕博翰","receiveNickName":"张帆","auditName":null,"isFixed":0,"isUpdate":1,"receiveDept":null,"deptId":null,"result":null,"canDelay":1,"canUpdate":0,"canUpdateReceive":0,"statusStr":"修改待确认","identity":2,"isUrgent":0,"urgentTime":"2020-07-24 10:06","taskDelay":null},{"createBy":"lvbohan","createTime":"2020-07-24 08:56","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"title":"11","taskNo":"2020072408490012","sponsor":"YHYF001","sponsorIdentity":100,"wantFinishTiem":"2020-07-26 08:56","taskDescribe":"11","options":null,"auditComments":0,"state":null,"taskStatus":13,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":"2020-07-24 09:07","taskType":null,"inspected":1,"inspectedState":"the ","stopReason":null,"isDelay":2,"delayReason":null,"inspectedUpdateTime":"2020-07-24 11:07","sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":"吕博翰","receiveNickName":"张帆","auditName":null,"isFixed":0,"isUpdate":0,"receiveDept":null,"deptId":null,"result":null,"canDelay":1,"canUpdate":1,"canUpdateReceive":0,"statusStr":"验收未通过","identity":2,"isUrgent":0,"urgentTime":null,"taskDelay":null},{"createBy":"lvbohan","createTime":"2020-07-24 08:50","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"title":"1","taskNo":"2020072408490002","sponsor":"YHYF001","sponsorIdentity":100,"wantFinishTiem":"2020-07-24 09:49","taskDescribe":"1","options":null,"auditComments":0,"state":null,"taskStatus":0,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":"2020-07-24 08:58","taskType":null,"inspected":0,"inspectedState":"wanc ","stopReason":null,"isDelay":2,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":"吕博翰","receiveNickName":"张帆","auditName":null,"isFixed":0,"isUpdate":0,"receiveDept":null,"deptId":null,"result":null,"canDelay":1,"canUpdate":1,"canUpdateReceive":0,"statusStr":"已完成","identity":2,"isUrgent":0,"urgentTime":null,"taskDelay":null}],"pageNum":1,"pageSize":20,"size":3,"startRow":1,"endRow":3,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
         * total : 3
         * list : [{"createBy":"lvbohan","createTime":"2020-07-24 08:56","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":11,"title":"10","taskNo":"2020072408490011","sponsor":"YHYF001","sponsorIdentity":100,"wantFinishTiem":"2020-07-24 11:05","taskDescribe":"10","options":null,"auditComments":0,"state":null,"taskStatus":18,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":null,"taskType":null,"inspected":null,"inspectedState":null,"stopReason":null,"isDelay":2,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":"吕博翰","receiveNickName":"张帆","auditName":null,"isFixed":0,"isUpdate":1,"receiveDept":null,"deptId":null,"result":null,"canDelay":1,"canUpdate":0,"canUpdateReceive":0,"statusStr":"修改待确认","identity":2,"isUrgent":0,"urgentTime":"2020-07-24 10:06","taskDelay":null},{"createBy":"lvbohan","createTime":"2020-07-24 08:56","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":12,"title":"11","taskNo":"2020072408490012","sponsor":"YHYF001","sponsorIdentity":100,"wantFinishTiem":"2020-07-26 08:56","taskDescribe":"11","options":null,"auditComments":0,"state":null,"taskStatus":13,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":"2020-07-24 09:07","taskType":null,"inspected":1,"inspectedState":"the ","stopReason":null,"isDelay":2,"delayReason":null,"inspectedUpdateTime":"2020-07-24 11:07","sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":"吕博翰","receiveNickName":"张帆","auditName":null,"isFixed":0,"isUpdate":0,"receiveDept":null,"deptId":null,"result":null,"canDelay":1,"canUpdate":1,"canUpdateReceive":0,"statusStr":"验收未通过","identity":2,"isUrgent":0,"urgentTime":null,"taskDelay":null},{"createBy":"lvbohan","createTime":"2020-07-24 08:50","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"title":"1","taskNo":"2020072408490002","sponsor":"YHYF001","sponsorIdentity":100,"wantFinishTiem":"2020-07-24 09:49","taskDescribe":"1","options":null,"auditComments":0,"state":null,"taskStatus":0,"auditStatus":1,"setBacks":0,"receive":"YHYF024","auditBy":null,"realTime":"2020-07-24 08:58","taskType":null,"inspected":0,"inspectedState":"wanc ","stopReason":null,"isDelay":2,"delayReason":null,"inspectedUpdateTime":null,"sysFilesSponsor":null,"sysFilesReceive":null,"addNickName":"吕博翰","receiveNickName":"张帆","auditName":null,"isFixed":0,"isUpdate":0,"receiveDept":null,"deptId":null,"result":null,"canDelay":1,"canUpdate":1,"canUpdateReceive":0,"statusStr":"已完成","identity":2,"isUrgent":0,"urgentTime":null,"taskDelay":null}]
         * pageNum : 1
         * pageSize : 20
         * size : 3
         * startRow : 1
         * endRow : 3
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * firstPage : 1
         * lastPage : 1
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int firstPage;
        private int lastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * createBy : lvbohan
             * createTime : 2020-07-24 08:56
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * id : 11
             * title : 10
             * taskNo : 2020072408490011
             * sponsor : YHYF001
             * sponsorIdentity : 100
             * wantFinishTiem : 2020-07-24 11:05
             * taskDescribe : 10
             * options : null
             * auditComments : 0
             * state : null
             * taskStatus : 18
             * auditStatus : 1
             * setBacks : 0
             * receive : YHYF024
             * auditBy : null
             * realTime : null
             * taskType : null
             * inspected : null
             * inspectedState : null
             * stopReason : null
             * isDelay : 2
             * delayReason : null
             * inspectedUpdateTime : null
             * sysFilesSponsor : null
             * sysFilesReceive : null
             * addNickName : 吕博翰
             * receiveNickName : 张帆
             * auditName : null
             * isFixed : 0
             * isUpdate : 1
             * receiveDept : null
             * deptId : null
             * result : null
             * canDelay : 1
             * canUpdate : 0
             * canUpdateReceive : 0
             * statusStr : 修改待确认
             * identity : 2
             * isUrgent : 0
             * urgentTime : 2020-07-24 10:06
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
            private Object options;
            private int auditComments;
            private Object state;
            private int taskStatus;
            private int auditStatus;
            private int setBacks;
            private String receive;
            private Object auditBy;
            private Object realTime;
            private Object taskType;
            private Object inspected;
            private Object inspectedState;
            private Object stopReason;
            private int isDelay;
            private Object delayReason;
            private Object inspectedUpdateTime;
            private Object sysFilesSponsor;
            private Object sysFilesReceive;
            private String addNickName;
            private String receiveNickName;
            private Object auditName;
            private int isFixed;
            private int isUpdate;
            private Object receiveDept;
            private Object deptId;
            private Object result;
            private int canDelay;
            private int canUpdate;
            private int canUpdateReceive;
            private String statusStr;
            private int identity;
            private int isUrgent;
            private String urgentTime;
            private Object taskDelay;

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

            public Object getOptions() {
                return options;
            }

            public void setOptions(Object options) {
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

            public Object getInspected() {
                return inspected;
            }

            public void setInspected(Object inspected) {
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

            public int getIsFixed() {
                return isFixed;
            }

            public void setIsFixed(int isFixed) {
                this.isFixed = isFixed;
            }

            public int getIsUpdate() {
                return isUpdate;
            }

            public void setIsUpdate(int isUpdate) {
                this.isUpdate = isUpdate;
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

            public int getIdentity() {
                return identity;
            }

            public void setIdentity(int identity) {
                this.identity = identity;
            }

            public int getIsUrgent() {
                return isUrgent;
            }

            public void setIsUrgent(int isUrgent) {
                this.isUrgent = isUrgent;
            }

            public String getUrgentTime() {
                return urgentTime;
            }

            public void setUrgentTime(String urgentTime) {
                this.urgentTime = urgentTime;
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
}
