package com.example.yuhekejioa.Bean;

import java.util.List;

public class ChoosepersonBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":100,"deptId":null,"userName":"lvbohan","nickName":"吕博翰","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF001","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":101,"deptId":null,"userName":"lixutong","nickName":"李旭彤","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF002","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":102,"deptId":null,"userName":"qinchenbo","nickName":"秦晨博","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF010","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":103,"deptId":null,"userName":"lining","nickName":"李宁","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF011","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":113,"deptId":null,"userName":"yuyajing","nickName":"于亚静","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF003","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":118,"deptId":null,"userName":"lida","nickName":"李达","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF004","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":119,"deptId":null,"userName":"liangjunqi","nickName":"梁峻旗","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF005","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":120,"deptId":null,"userName":"dipeining","nickName":"邸佩宁","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF006","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":122,"deptId":null,"userName":"dihang","nickName":"邸航","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF007","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":125,"deptId":null,"userName":"zhangconghui","nickName":"张丛会","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF009","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":131,"deptId":null,"userName":"zhangfan","nickName":"张帆","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF024","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false},{"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":132,"deptId":null,"userName":"nanmenghua","nickName":"南梦华","email":null,"phonenumber":null,"sex":null,"avatar":null,"password":null,"salt":null,"status":null,"delFlag":null,"userNo":"YHYF025","loginIp":null,"loginDate":null,"dept":null,"roles":[],"roleIds":null,"postIds":null,"role":0,"admin":false}]
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
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * userId : 100
         * deptId : null
         * userName : lvbohan
         * nickName : 吕博翰
         * email : null
         * phonenumber : null
         * sex : null
         * avatar : null
         * password : null
         * salt : null
         * status : null
         * delFlag : null
         * userNo : YHYF001
         * loginIp : null
         * loginDate : null
         * dept : null
         * roles : []
         * roleIds : null
         * postIds : null
         * role : 0
         * admin : false
         */

        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private int userId;
        private Object deptId;
        private String userName;
        private String nickName;
        private Object email;
        private Object phonenumber;
        private Object sex;
        private Object avatar;
        private Object password;
        private Object salt;
        private Object status;
        private Object delFlag;
        private String userNo;
        private Object loginIp;
        private Object loginDate;
        private Object dept;
        private Object roleIds;
        private Object postIds;
        private int role;
        private boolean admin;
        private List<?> roles;

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

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getDeptId() {
            return deptId;
        }

        public void setDeptId(Object deptId) {
            this.deptId = deptId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(Object phonenumber) {
            this.phonenumber = phonenumber;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getSalt() {
            return salt;
        }

        public void setSalt(Object salt) {
            this.salt = salt;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
        }

        public Object getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(Object loginIp) {
            this.loginIp = loginIp;
        }

        public Object getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(Object loginDate) {
            this.loginDate = loginDate;
        }

        public Object getDept() {
            return dept;
        }

        public void setDept(Object dept) {
            this.dept = dept;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public Object getPostIds() {
            return postIds;
        }

        public void setPostIds(Object postIds) {
            this.postIds = postIds;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }

        public static class ParamsBean {
        }
    }
}
