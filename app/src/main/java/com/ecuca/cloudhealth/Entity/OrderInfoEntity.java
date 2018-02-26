package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/25.
 */

public class OrderInfoEntity {


    /**
     * data : {"order_sn":"20180125495748339557","status":1,"doctor_id":155,"type":1,"total_money":"12.00","pay_time":1516849578,"add_time":1516849574,"finish_time":null,"doctor":{"nick_name":"张三","section":"中医科","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg","hospital":"成都市人民医院","cate":"普通医生","price":12},"is_reply":0,"log":{"message":"厉害了无图","add_time":"1516849602","reply_content":null,"username":"土龙","graphic":["http://192.168.0.15:10037/upload/graphic_consultation/b431ae48b78b311accb8c157cabcd5de.png","http://192.168.0.15:10037/upload/graphic_consultation/eb62c0e8d87214a55905ae37f57468ea.jpg"]},"order_id":2}
     * code : 200
     * msg : ok
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
         * order_sn : 20180125495748339557
         * status : 1
         * doctor_id : 155
         * type : 1
         * total_money : 12.00
         * pay_time : 1516849578
         * add_time : 1516849574
         * finish_time : null
         * doctor : {"nick_name":"张三","section":"中医科","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg","hospital":"成都市人民医院","cate":"普通医生","price":12}
         * is_reply : 0
         * log : {"message":"厉害了无图","add_time":"1516849602","reply_content":null,"username":"土龙","graphic":["http://192.168.0.15:10037/upload/graphic_consultation/b431ae48b78b311accb8c157cabcd5de.png","http://192.168.0.15:10037/upload/graphic_consultation/eb62c0e8d87214a55905ae37f57468ea.jpg"]}
         * order_id : 2
         */

        private String order_sn;
        private int status;
        private int doctor_id;
        private int type;
        private String total_money;
        private String pay_time;
        private String add_time;
        private String finish_time;
        private DoctorBean doctor;
        private int is_reply;
        private LogBean log;
        private int order_id;

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

        public int getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(int doctor_id) {
            this.doctor_id = doctor_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public DoctorBean getDoctor() {
            return doctor;
        }

        public void setDoctor(DoctorBean doctor) {
            this.doctor = doctor;
        }

        public int getIs_reply() {
            return is_reply;
        }

        public void setIs_reply(int is_reply) {
            this.is_reply = is_reply;
        }

        public LogBean getLog() {
            return log;
        }

        public void setLog(LogBean log) {
            this.log = log;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public static class DoctorBean {
            /**
             * nick_name : 张三
             * section : 中医科
             * avatar_url : http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg
             * hospital : 成都市人民医院
             * cate : 普通医生
             * price : 12
             */

            private String nick_name;
            private String section;
            private String avatar_url;
            private String hospital;
            private String cate;
            private int price;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getSection() {
                return section;
            }

            public void setSection(String section) {
                this.section = section;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getHospital() {
                return hospital;
            }

            public void setHospital(String hospital) {
                this.hospital = hospital;
            }

            public String getCate() {
                return cate;
            }

            public void setCate(String cate) {
                this.cate = cate;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }

        public static class LogBean {
            /**
             * message : 厉害了无图
             * add_time : 1516849602
             * reply_content : null
             * username : 土龙
             * graphic : ["http://192.168.0.15:10037/upload/graphic_consultation/b431ae48b78b311accb8c157cabcd5de.png","http://192.168.0.15:10037/upload/graphic_consultation/eb62c0e8d87214a55905ae37f57468ea.jpg"]
             */

            private String message;
            private String add_time;
            private String reply_content;
            private String username;
            private List<String> graphic;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getReply_content() {
                return reply_content;
            }

            public void setReply_content(String reply_content) {
                this.reply_content = reply_content;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public List<String> getGraphic() {
                return graphic;
            }

            public void setGraphic(List<String> graphic) {
                this.graphic = graphic;
            }
        }
    }
}
