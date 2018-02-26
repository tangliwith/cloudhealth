package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2017/12/18.
 */

public class DateSignInEntity {


    /**
     * data : {"res":true,"yesterday_sign":{"id":4,"uid":38,"continue_days":1,"add_time":1513011600},"history_list":[{"y":"2017","m":"12","d":"13","is_sign":false},{"y":"2017","m":"12","d":"12","is_sign":true}]}
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
         * res : true
         * yesterday_sign : {"id":4,"uid":38,"continue_days":1,"add_time":1513011600}
         * history_list : [{"y":"2017","m":"12","d":"13","is_sign":false},{"y":"2017","m":"12","d":"12","is_sign":true}]
         */

        private boolean res;
        private YesterdaySignBean yesterday_sign;
        private List<HistoryListBean> history_list;

        public boolean isRes() {
            return res;
        }

        public void setRes(boolean res) {
            this.res = res;
        }

        public YesterdaySignBean getYesterday_sign() {
            return yesterday_sign;
        }

        public void setYesterday_sign(YesterdaySignBean yesterday_sign) {
            this.yesterday_sign = yesterday_sign;
        }

        public List<HistoryListBean> getHistory_list() {
            return history_list;
        }

        public void setHistory_list(List<HistoryListBean> history_list) {
            this.history_list = history_list;
        }

        public static class YesterdaySignBean {
            /**
             * id : 4
             * uid : 38
             * continue_days : 1
             * add_time : 1513011600
             */

            private int id;
            private int uid;
            private int continue_days;
            private int add_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getContinue_days() {
                return continue_days;
            }

            public void setContinue_days(int continue_days) {
                this.continue_days = continue_days;
            }

            public int getAdd_time() {
                return add_time;
            }

            public void setAdd_time(int add_time) {
                this.add_time = add_time;
            }
        }

        public static class HistoryListBean {
            /**
             * y : 2017
             * m : 12
             * d : 13
             * is_sign : false
             */

            private String y;
            private String m;
            private String d;
            private boolean is_sign;

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getM() {
                return m;
            }

            public void setM(String m) {
                this.m = m;
            }

            public String getD() {
                return d;
            }

            public void setD(String d) {
                this.d = d;
            }

            public boolean isIs_sign() {
                return is_sign;
            }

            public void setIs_sign(boolean is_sign) {
                this.is_sign = is_sign;
            }
        }
    }
}
