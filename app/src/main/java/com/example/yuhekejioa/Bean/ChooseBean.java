package com.example.yuhekejioa.Bean;

import java.util.List;

public class ChooseBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"params":{},"deptId":100,"parentId":null,"ancestors":null,"deptName":"宇赫科技","orderNum":null,"leader":null,"phone":null,"email":null,"status":null,"delFlag":null,"parentName":null,"children":[{"createBy":"admin","createTime":"2018-03-16 11:33","updateBy":null,"updateTime":null,"params":{},"deptId":101,"parentId":100,"ancestors":"0,100","deptName":"石家庄分公司","orderNum":"1","leader":"宇赫","phone":"15888888888","email":"yuhe@sina.com","status":"0","delFlag":"0","parentName":null,"children":[{"createBy":"admin","createTime":"2018-03-16 11:33","updateBy":null,"updateTime":null,"params":{},"deptId":103,"parentId":101,"ancestors":"0,100,101","deptName":"研发中心","orderNum":"3","leader":"YHYF001","phone":"15888888888","email":"ry@qq.com","status":"0","delFlag":"0","parentName":null,"children":[]}]}]}
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
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * params : {}
         * deptId : 100
         * parentId : null
         * ancestors : null
         * deptName : 宇赫科技
         * orderNum : null
         * leader : null
         * phone : null
         * email : null
         * status : null
         * delFlag : null
         * parentName : null
         * children : [{"createBy":"admin","createTime":"2018-03-16 11:33","updateBy":null,"updateTime":null,"params":{},"deptId":101,"parentId":100,"ancestors":"0,100","deptName":"石家庄分公司","orderNum":"1","leader":"宇赫","phone":"15888888888","email":"yuhe@sina.com","status":"0","delFlag":"0","parentName":null,"children":[{"createBy":"admin","createTime":"2018-03-16 11:33","updateBy":null,"updateTime":null,"params":{},"deptId":103,"parentId":101,"ancestors":"0,100,101","deptName":"研发中心","orderNum":"3","leader":"YHYF001","phone":"15888888888","email":"ry@qq.com","status":"0","delFlag":"0","parentName":null,"children":[]}]}]
         */

        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private ParamsBean params;
        private int deptId;
        private Object parentId;
        private Object ancestors;
        private String deptName;
        private Object orderNum;
        private Object leader;
        private Object phone;
        private Object email;
        private Object status;
        private Object delFlag;
        private Object parentName;
        private List<ChildrenBeanX> children;

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

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getAncestors() {
            return ancestors;
        }

        public void setAncestors(Object ancestors) {
            this.ancestors = ancestors;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public Object getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(Object orderNum) {
            this.orderNum = orderNum;
        }

        public Object getLeader() {
            return leader;
        }

        public void setLeader(Object leader) {
            this.leader = leader;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
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

        public Object getParentName() {
            return parentName;
        }

        public void setParentName(Object parentName) {
            this.parentName = parentName;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        public static class ParamsBean {
        }

        public static class ChildrenBeanX {
            /**
             * createBy : admin
             * createTime : 2018-03-16 11:33
             * updateBy : null
             * updateTime : null
             * params : {}
             * deptId : 101
             * parentId : 100
             * ancestors : 0,100
             * deptName : 石家庄分公司
             * orderNum : 1
             * leader : 宇赫
             * phone : 15888888888
             * email : yuhe@sina.com
             * status : 0
             * delFlag : 0
             * parentName : null
             * children : [{"createBy":"admin","createTime":"2018-03-16 11:33","updateBy":null,"updateTime":null,"params":{},"deptId":103,"parentId":101,"ancestors":"0,100,101","deptName":"研发中心","orderNum":"3","leader":"YHYF001","phone":"15888888888","email":"ry@qq.com","status":"0","delFlag":"0","parentName":null,"children":[]}]
             */

            private String createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private ParamsBeanX params;
            private int deptId;
            private int parentId;
            private String ancestors;
            private String deptName;
            private String orderNum;
            private String leader;
            private String phone;
            private String email;
            private String status;
            private String delFlag;
            private Object parentName;
            private List<ChildrenBean> children;

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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
                this.params = params;
            }

            public int getDeptId() {
                return deptId;
            }

            public void setDeptId(int deptId) {
                this.deptId = deptId;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getAncestors() {
                return ancestors;
            }

            public void setAncestors(String ancestors) {
                this.ancestors = ancestors;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public String getLeader() {
                return leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public Object getParentName() {
                return parentName;
            }

            public void setParentName(Object parentName) {
                this.parentName = parentName;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ParamsBeanX {
            }

            public static class ChildrenBean {
                /**
                 * createBy : admin
                 * createTime : 2018-03-16 11:33
                 * updateBy : null
                 * updateTime : null
                 * params : {}
                 * deptId : 103
                 * parentId : 101
                 * ancestors : 0,100,101
                 * deptName : 研发中心
                 * orderNum : 3
                 * leader : YHYF001
                 * phone : 15888888888
                 * email : ry@qq.com
                 * status : 0
                 * delFlag : 0
                 * parentName : null
                 * children : []
                 */

                private String createBy;
                private String createTime;
                private Object updateBy;
                private Object updateTime;
                private ParamsBeanXX params;
                private int deptId;
                private int parentId;
                private String ancestors;
                private String deptName;
                private String orderNum;
                private String leader;
                private String phone;
                private String email;
                private String status;
                private String delFlag;
                private Object parentName;
                private List<?> children;

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

                public ParamsBeanXX getParams() {
                    return params;
                }

                public void setParams(ParamsBeanXX params) {
                    this.params = params;
                }

                public int getDeptId() {
                    return deptId;
                }

                public void setDeptId(int deptId) {
                    this.deptId = deptId;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public String getAncestors() {
                    return ancestors;
                }

                public void setAncestors(String ancestors) {
                    this.ancestors = ancestors;
                }

                public String getDeptName() {
                    return deptName;
                }

                public void setDeptName(String deptName) {
                    this.deptName = deptName;
                }

                public String getOrderNum() {
                    return orderNum;
                }

                public void setOrderNum(String orderNum) {
                    this.orderNum = orderNum;
                }

                public String getLeader() {
                    return leader;
                }

                public void setLeader(String leader) {
                    this.leader = leader;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getDelFlag() {
                    return delFlag;
                }

                public void setDelFlag(String delFlag) {
                    this.delFlag = delFlag;
                }

                public Object getParentName() {
                    return parentName;
                }

                public void setParentName(Object parentName) {
                    this.parentName = parentName;
                }

                public List<?> getChildren() {
                    return children;
                }

                public void setChildren(List<?> children) {
                    this.children = children;
                }

                public static class ParamsBeanXX {
                }
            }
        }
    }
}
