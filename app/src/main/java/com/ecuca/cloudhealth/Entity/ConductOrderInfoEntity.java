package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/26.
 */

public class ConductOrderInfoEntity {


    /**
     * data : {"with_time":"1516020042","user_name":"koer","total_money":"99.00","mobile":"15896321452","diagnosis_type":1,"province_name":"四川省","city_name":"成都市","hospital_name":"成都市人民医院"}
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
         * with_time : 1516020042
         * user_name : koer
         * total_money : 99.00
         * mobile : 15896321452
         * diagnosis_type : 1
         * province_name : 四川省
         * city_name : 成都市
         * hospital_name : 成都市人民医院
         */

        private String with_time;
        private String user_name;
        private String total_money;
        private String mobile;
        private int diagnosis_type;
        private String province_name;
        private String city_name;
        private String hospital_name;
        private int status;
        private int order_id;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getWith_time() {
            return with_time;
        }

        public void setWith_time(String with_time) {
            this.with_time = with_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getDiagnosis_type() {
            return diagnosis_type;
        }

        public void setDiagnosis_type(int diagnosis_type) {
            this.diagnosis_type = diagnosis_type;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getHospital_name() {
            return hospital_name;
        }

        public void setHospital_name(String hospital_name) {
            this.hospital_name = hospital_name;
        }
    }
}
