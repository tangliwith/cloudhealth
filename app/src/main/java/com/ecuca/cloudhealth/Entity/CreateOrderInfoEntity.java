package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/24.
 */

public class CreateOrderInfoEntity {

    /**
     * data : {"order_sn":"20180124623119684577","total_money":"12.00","goods_list":[{"goods_name":"图文咨询","goods_money":"12.00"}],"pay_money":"12.00"}
     * code : 200
     * msg : 获取成功
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
         * order_sn : 20180124623119684577
         * total_money : 12.00
         * goods_list : [{"goods_name":"图文咨询","goods_money":"12.00"}]
         * pay_money : 12.00
         */

        private String order_sn;
        private String total_money;
        private String pay_money;
        private List<GoodsListBean> goods_list;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_name : 图文咨询
             * goods_money : 12.00
             */

            private String goods_name;
            private String goods_money;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_money() {
                return goods_money;
            }

            public void setGoods_money(String goods_money) {
                this.goods_money = goods_money;
            }
        }
    }
}
