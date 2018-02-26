package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/26.
 */

public class MyConductOrderEntity {


    /**
     * data : [{"id":13,"status":2,"with_time":"1516020567","total_money":"50.00","diagnosis_type":1,"hospital_name":"中山大学孙逸仙纪念医院(北院)"},{"id":5,"status":9,"with_time":"1516020042","total_money":"99.00","diagnosis_type":1,"hospital_name":"成都市人民医院"}]
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
         * id : 13
         * status : 2
         * with_time : 1516020567
         * total_money : 50.00
         * diagnosis_type : 1
         * hospital_name : 中山大学孙逸仙纪念医院(北院)
         */

        private int id;
        private int status;
        private String with_times;
        private String total_money;
        private int diagnosis_type;
        private String hospital_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getWith_times() {
            return with_times;
        }

        public void setWith_times(String with_times) {
            this.with_times = with_times;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public int getDiagnosis_type() {
            return diagnosis_type;
        }

        public void setDiagnosis_type(int diagnosis_type) {
            this.diagnosis_type = diagnosis_type;
        }

        public String getHospital_name() {
            return hospital_name;
        }

        public void setHospital_name(String hospital_name) {
            this.hospital_name = hospital_name;
        }
    }
}
