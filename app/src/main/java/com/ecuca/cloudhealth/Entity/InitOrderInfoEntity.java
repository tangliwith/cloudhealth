package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class InitOrderInfoEntity {


    /**
     * data : {"order_id":"9","is_free":0}
     * code : 200
     * msg : 创建订单成功!
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
         * order_id : 9
         * is_free : 0
         */

        private int order_id;
        private int is_free;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }
    }
}
