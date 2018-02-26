package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2017/12/12.
 */

public class MsgCodeEntity {


    /**
     * data : {}
     * code : 200
     * msg : 短信发送成功
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
