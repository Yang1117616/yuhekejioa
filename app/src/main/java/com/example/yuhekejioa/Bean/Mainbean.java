package com.example.yuhekejioa.Bean;

import java.util.List;

public class Mainbean {


    /**
     * msg : 操作成功
     * code : 200
     * data : [{"createBy":"YHYF025","createTime":"2020-07-21 11:56","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1169,"type":"加急任务单","receive":"YHYF008","content":"您有一条加急任务单需要紧急处理，请确认","isRead":0,"taskNo":"2020072110280014","taskId":742,"taskStatus":2,"taskTitle":"加急aji","isUrgent":1},{"createBy":"YHYF025","createTime":"2020-07-21 11:33","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1162,"type":"加急任务单","receive":"YHYF008","content":"您有一条加急任务单需要紧急处理，请确认","isRead":0,"taskNo":"2020072110280007","taskId":735,"taskStatus":2,"taskTitle":"加急标题","isUrgent":1},{"createBy":"YHYF024","createTime":"2020-07-20 18:18","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1151,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查看","isRead":0,"taskNo":"2020072009280013","taskId":725,"taskStatus":2,"taskTitle":"测试标题","isUrgent":0},{"createBy":"YHYF024","createTime":"2020-07-20 09:44","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1135,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查看","isRead":0,"taskNo":"2020072009280002","taskId":714,"taskStatus":2,"taskTitle":"测试","isUrgent":0},{"createBy":"YHYF005","createTime":"2020-07-18 17:46","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1111,"type":"验收不通过","receive":"YHYF008","content":"您的任务单验收不通过，请及时查看","isRead":0,"taskNo":"2020071809210043","taskId":708,"taskStatus":13,"taskTitle":"sdasdas","isUrgent":0},{"createBy":"YHYF025","createTime":"2020-07-18 15:05","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1065,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查看","isRead":1,"taskNo":"2020071809210030","taskId":695,"taskStatus":2,"taskTitle":"14","isUrgent":0},{"createBy":"YHYF025","createTime":"2020-07-18 15:04","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1064,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查看","isRead":0,"taskNo":"2020071809210029","taskId":694,"taskStatus":2,"taskTitle":"1","isUrgent":0},{"createBy":"YHYF025","createTime":"2020-07-18 15:04","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1063,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查看","isRead":0,"taskNo":"2020071809210028","taskId":693,"taskStatus":2,"taskTitle":"1","isUrgent":0},{"createBy":"YHYF025","createTime":"2020-07-18 15:01","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":1062,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查看","isRead":0,"taskNo":"2020071809210027","taskId":692,"taskStatus":2,"taskTitle":"1","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 16:32","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":926,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":1,"taskNo":"2020071709070120","taskId":591,"taskStatus":2,"taskTitle":"44","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 16:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":908,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":1,"taskNo":"2020071709070102","taskId":573,"taskStatus":2,"taskTitle":"。","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 16:22","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":896,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":1,"taskNo":"2020071709070089","taskId":561,"taskStatus":2,"taskTitle":"，","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 16:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":884,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":1,"taskNo":"2020071709070081","taskId":553,"taskStatus":2,"taskTitle":"，","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 15:56","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":860,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070082","taskId":554,"taskStatus":2,"taskTitle":"，","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 14:40","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":797,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070079","taskId":468,"taskStatus":2,"taskTitle":"测试z","isUrgent":0},{"createBy":"YHYF024","createTime":"2020-07-17 11:33","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":772,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070074","taskId":463,"taskStatus":2,"taskTitle":"测试K文件","isUrgent":0},{"createBy":"YH00001","createTime":"2020-07-17 10:58","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":753,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":1,"taskNo":"2020071709070064","taskId":453,"taskStatus":2,"taskTitle":"Q","isUrgent":0},{"createBy":"YH00001","createTime":"2020-07-17 10:58","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":752,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070063","taskId":452,"taskStatus":2,"taskTitle":"Q","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 10:44","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":742,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070053","taskId":442,"taskStatus":2,"taskTitle":"Wings\n","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 10:38","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":731,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":1,"taskNo":"2020071709070042","taskId":431,"taskStatus":2,"taskTitle":"1887","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 09:16","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":720,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070033","taskId":419,"taskStatus":2,"taskTitle":"Ffc ","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 09:13","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":708,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070021","taskId":407,"taskStatus":2,"taskTitle":"I\u2019m ","isUrgent":0},{"createBy":"YHYF001","createTime":"2020-07-17 09:07","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":696,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071709070009","taskId":395,"taskStatus":2,"taskTitle":"The ","isUrgent":0},{"createBy":"YH00001","createTime":"2020-07-16 19:35","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":678,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071619260071","taskId":377,"taskStatus":2,"taskTitle":"老板单发","isUrgent":0},{"createBy":"YH00001","createTime":"2020-07-16 19:30","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":652,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071619260045","taskId":351,"taskStatus":2,"taskTitle":"老板多选部门","isUrgent":0},{"createBy":"YH00001","createTime":"2020-07-16 19:28","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":626,"type":"新任务单","receive":"YHYF008","content":"您有新的任务单，请及时查收","isRead":0,"taskNo":"2020071619260019","taskId":325,"taskStatus":2,"taskTitle":"老板_群发","isUrgent":0},{"createBy":"YHYF004","createTime":"2020-07-14 18:36","updateBy":null,"updateTime":null,"remark":null,"params":{},"id":604,"type":"确认收到任务单","receive":"YHYF008","content":"您发起的任务单已确认接收","isRead":1,"taskNo":"2020071411160009","taskId":303,"taskStatus":21,"taskTitle":"宇赫123","isUrgent":0}]
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
         * createBy : YHYF025
         * createTime : 2020-07-21 11:56
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 1169
         * type : 加急任务单
         * receive : YHYF008
         * content : 您有一条加急任务单需要紧急处理，请确认
         * isRead : 0
         * taskNo : 2020072110280014
         * taskId : 742
         * taskStatus : 2
         * taskTitle : 加急aji
         * isUrgent : 1
         */

        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
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

        public static class ParamsBean {
        }
    }
}
