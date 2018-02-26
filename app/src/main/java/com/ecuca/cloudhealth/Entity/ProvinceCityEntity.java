package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/13.
 */

public class ProvinceCityEntity {




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
         * id : 1
         * name_full : 北京市
         * name_chs : 北京
         * name_chp : bj
         * name_cht : beijing
         * name_abbr : 京
         * code : 110000
         * location_type : 1
         * parent_id : 0
         * sort : 1
         * city_list : [{"id":375,"name_full":"北京市","name_chs":"北京","name_chp":"bj","name_cht":"beijing","name_abbr":"","code":"110100","location_type":2,"parent_id":1,"sort":0}]
         */

        private int id;
        private String name_full;
        private String name_chs;
        private String name_chp;
        private String name_cht;
        private String name_abbr;
        private String code;
        private int location_type;
        private int parent_id;
        private int sort;
        private List<CityListBean> city_list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName_full() {
            return name_full;
        }

        public void setName_full(String name_full) {
            this.name_full = name_full;
        }

        public String getName_chs() {
            return name_chs;
        }

        public void setName_chs(String name_chs) {
            this.name_chs = name_chs;
        }

        public String getName_chp() {
            return name_chp;
        }

        public void setName_chp(String name_chp) {
            this.name_chp = name_chp;
        }

        public String getName_cht() {
            return name_cht;
        }

        public void setName_cht(String name_cht) {
            this.name_cht = name_cht;
        }

        public String getName_abbr() {
            return name_abbr;
        }

        public void setName_abbr(String name_abbr) {
            this.name_abbr = name_abbr;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getLocation_type() {
            return location_type;
        }

        public void setLocation_type(int location_type) {
            this.location_type = location_type;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<CityListBean> getCity_list() {
            return city_list;
        }

        public void setCity_list(List<CityListBean> city_list) {
            this.city_list = city_list;
        }

        public static class CityListBean {
            /**
             * id : 375
             * name_full : 北京市
             * name_chs : 北京
             * name_chp : bj
             * name_cht : beijing
             * name_abbr :
             * code : 110100
             * location_type : 2
             * parent_id : 1
             * sort : 0
             */

            private int id;
            private String name_full;
            private String name_chs;
            private String name_chp;
            private String name_cht;
            private String name_abbr;
            private String code;
            private int location_type;
            private int parent_id;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName_full() {
                return name_full;
            }

            public void setName_full(String name_full) {
                this.name_full = name_full;
            }

            public String getName_chs() {
                return name_chs;
            }

            public void setName_chs(String name_chs) {
                this.name_chs = name_chs;
            }

            public String getName_chp() {
                return name_chp;
            }

            public void setName_chp(String name_chp) {
                this.name_chp = name_chp;
            }

            public String getName_cht() {
                return name_cht;
            }

            public void setName_cht(String name_cht) {
                this.name_cht = name_cht;
            }

            public String getName_abbr() {
                return name_abbr;
            }

            public void setName_abbr(String name_abbr) {
                this.name_abbr = name_abbr;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getLocation_type() {
                return location_type;
            }

            public void setLocation_type(int location_type) {
                this.location_type = location_type;
            }

            public int getParent_id() {
                return parent_id;
            }

            public void setParent_id(int parent_id) {
                this.parent_id = parent_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
