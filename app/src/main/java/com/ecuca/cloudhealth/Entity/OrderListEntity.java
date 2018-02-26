package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/25.
 */

public class OrderListEntity {


    /**
     * data : [{"id":4,"order_sn":"20180125497034613156","status":9,"type":3,"doctor_name":"李四","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/17e5a880e5c532cc7c743eb18ddc6c25.jpg","cate_type":"知名教授","hospital_name":"成都市武警总医院","section_info":"儿科","profession":"新生儿专科,小儿内科","log":{"message":"突兀看看","username":"土龙"}},{"id":3,"order_sn":"20180125496846852399","status":9,"type":2,"doctor_name":"李四","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/17e5a880e5c532cc7c743eb18ddc6c25.jpg","cate_type":"知名教授","hospital_name":"成都市武警总医院","section_info":"儿科","profession":"新生儿专科,小儿内科,新生儿专科,小儿内科","log":{"message":"JJ具体他现在","username":"土龙"}},{"id":2,"order_sn":"20180125495748339557","status":1,"type":1,"doctor_name":"张三","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg","cate_type":"普通医生","hospital_name":"成都市人民医院","section_info":"中医科","profession":"新生儿专科,小儿内科,新生儿专科,小儿内科,中医内科,中医外科,中医妇产科,中医儿科","log":{"message":"厉害了无图","username":"土龙"}},{"id":1,"order_sn":"20180125491394730558","status":9,"type":1,"doctor_name":"张三","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg","cate_type":"普通医生","hospital_name":"成都市人民医院","section_info":"中医科","profession":"新生儿专科,小儿内科,新生儿专科,小儿内科,中医内科,中医外科,中医妇产科,中医儿科,中医内科,中医外科,中医妇产科,中医儿科","log":null}]
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
         * id : 4
         * order_sn : 20180125497034613156
         * status : 9
         * type : 3
         * doctor_name : 李四
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/17e5a880e5c532cc7c743eb18ddc6c25.jpg
         * cate_type : 知名教授
         * hospital_name : 成都市武警总医院
         * section_info : 儿科
         * profession : 新生儿专科,小儿内科
         * log : {"message":"突兀看看","username":"土龙"}
         */

        private int id;
        private String order_sn;
        private int status;
        private int type;
        private String doctor_name;
        private String avatar_url;
        private String cate_type;
        private String hospital_name;
        private String section_info;
        private String profession;
        private LogBean log;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public void setDoctor_name(String doctor_name) {
            this.doctor_name = doctor_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public LogBean getLog() {
            return log;
        }

        public void setLog(LogBean log) {
            this.log = log;
        }

        public static class LogBean {
            /**
             * message : 突兀看看
             * username : 土龙
             */

            private String message;
            private String username;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
