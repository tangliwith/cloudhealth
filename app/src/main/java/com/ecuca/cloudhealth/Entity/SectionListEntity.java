package com.ecuca.cloudhealth.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuhualong on 2018/1/20.
 */

public class SectionListEntity {

    /**
     * data : [{"id":144,"title":"儿科"},{"id":148,"title":"妇科"},{"id":149,"title":"产科"},{"id":150,"title":"皮肤科"},{"id":159,"title":"中医科"}]
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
         * id : 144
         * title : 儿科
         */

        private int id;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
