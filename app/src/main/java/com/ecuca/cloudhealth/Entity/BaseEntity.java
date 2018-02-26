package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/22.
 */

public class BaseEntity {


    /**
     * data : {}
     * code : 200
     * msg : 提交成功!
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
    }
}
