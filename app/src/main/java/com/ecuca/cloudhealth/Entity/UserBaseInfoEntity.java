package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class UserBaseInfoEntity {


    /**
     * data : {"uid":159,"user_name":"18523158103","nick_name":"bbb","money":0,"total_news_number":0,"child_no":0,"follow_no":2,"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/e80ac9a90b04b9874d5efb221a7b4187.png","group":"普通用户","is_vip":0}
     * code : 200
     * msg : 获取成功
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
         * user_name : 18523158103
         * nick_name : bbb
         * money : 0
         * total_news_number : 0
         * child_no : 0
         * follow_no : 2
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/e80ac9a90b04b9874d5efb221a7b4187.png
         * group : 普通用户
         * is_vip : 0
         */

        private int uid;
        private String user_name;
        private String nick_name;
        private double money;
        private int total_news_number;
        private int child_no;
        private int follow_no;
        private String avatar_url;
        private String group;
        private int is_vip;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getTotal_news_number() {
            return total_news_number;
        }

        public void setTotal_news_number(int total_news_number) {
            this.total_news_number = total_news_number;
        }

        public int getChild_no() {
            return child_no;
        }

        public void setChild_no(int child_no) {
            this.child_no = child_no;
        }

        public int getFollow_no() {
            return follow_no;
        }

        public void setFollow_no(int follow_no) {
            this.follow_no = follow_no;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }
    }
}
