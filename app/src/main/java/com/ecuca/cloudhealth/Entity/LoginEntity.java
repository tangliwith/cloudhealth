package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2017/12/6.
 */

public class LoginEntity {


    /**
     * data : {"uid":159,"sex":1,"user_name":"18523158103","mobile":"18523158103","avatar":"/upload/image/default/avatar/max.png?1516333447","token":"6d54876f6f4e92b07fe20a5770b8c39c"}
     * code : 200
     * msg : 登录成功
     */

    private DataBean data;
    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * uid : 159
         * sex : 1
         * user_name : 18523158103
         * mobile : 18523158103
         * avatar : /upload/image/default/avatar/max.png?1516333447
         * token : 6d54876f6f4e92b07fe20a5770b8c39c
         */

        private int uid;
        private int sex;
        private String user_name;
        private String mobile;
        private String avatar;
        private String token;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
