package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/24.
 */

public class FollowDoctorListEntity {


    /**
     * data : [{"uid":155,"nick_name":"张三","cate_type":"普通医生","hospital_name":"成都市人民医院","section_info":"中医科","profession":"中医内科,中医外科,中医妇产科,中医儿科","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg"},{"uid":151,"nick_name":"李四","cate_type":"知名教授","hospital_name":"成都市武警总医院","section_info":"儿科","profession":"中医内科,中医外科,中医妇产科,中医儿科,新生儿专科,小儿内科","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/17e5a880e5c532cc7c743eb18ddc6c25.jpg"}]
     * code : 200
     * msg : ok
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 155
         * nick_name : 张三
         * cate_type : 普通医生
         * hospital_name : 成都市人民医院
         * section_info : 中医科
         * profession : 中医内科,中医外科,中医妇产科,中医儿科
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg
         */

        private int uid;
        private String nick_name;
        private String cate_type;
        private String hospital_name;
        private String section_info;
        private String profession;
        private String avatar_url;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getCate_type() {
            return cate_type;
        }

        public void setCate_type(String cate_type) {
            this.cate_type = cate_type;
        }

        public String getHospital_name() {
            return hospital_name;
        }

        public void setHospital_name(String hospital_name) {
            this.hospital_name = hospital_name;
        }

        public String getSection_info() {
            return section_info;
        }

        public void setSection_info(String section_info) {
            this.section_info = section_info;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}
