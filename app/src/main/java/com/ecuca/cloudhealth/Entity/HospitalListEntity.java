package com.ecuca.cloudhealth.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuhualong on 2018/1/19.
 */

public class HospitalListEntity {


    /**
     * data : [{"id":9,"province":23,"city":269,"address":"成都市新都区宝光大道278号","county":null,"hospital_name":"成都医学院第一附属医院","hospital_img":"20d5f187ed7a05f1ca78b6d56fb7540b.jpg"},{"id":8,"province":23,"city":269,"address":"顺城大街308号","county":null,"hospital_name":"成都御生堂中医馆","hospital_img":"0a052a30c03b3b8d47080bd7aa23c8e0.jpg"},{"id":7,"province":23,"city":269,"address":"成都市一环路西二段32号","county":null,"hospital_name":"中国人民解放军成都军区总医院","hospital_img":"b94d111b504ca23fc40bcdf8b059c797.jpg"},{"id":3,"province":23,"city":269,"address":"成都市双流区xxxxx","county":null,"hospital_name":"成都市武警总医院","hospital_img":"35b8da45eba14f1fda9a8eca10fa1f1f.jpg"},{"id":2,"province":23,"city":269,"address":"成都市武侯区xxxx","county":null,"hospital_name":"成都市人民医院","hospital_img":"f34fddc4ba3ec509e424b066ad3945f5.jpg"}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 9
         * province : 23
         * city : 269
         * address : 成都市新都区宝光大道278号
         * county : null
         * hospital_name : 成都医学院第一附属医院
         * hospital_img : 20d5f187ed7a05f1ca78b6d56fb7540b.jpg
         */

        private int id;
        private int province;
        private int city;
        private String address;
        private String county;
        private String hospital_name;
        private String hospital_img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getHospital_name() {
            return hospital_name;
        }

        public void setHospital_name(String hospital_name) {
            this.hospital_name = hospital_name;
        }

        public String getHospital_img() {
            return hospital_img;
        }

        public void setHospital_img(String hospital_img) {
            this.hospital_img = hospital_img;
        }
    }
}
