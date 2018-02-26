package com.ecuca.cloudhealth.Entity;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/7.
 */

public class AllAddressEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements IPickerViewData {
        /**
         * id : 1
         * name : 北京
         * citys : [{"id":375,"name":"北京","countys":[{"id":379,"name":"东城区"},{"id":380,"name":"西城区"},{"id":381,"name":"朝阳区"},{"id":382,"name":"丰台区"},{"id":383,"name":"石景山区"},{"id":384,"name":"海淀区"},{"id":385,"name":"门头沟区"},{"id":386,"name":"房山区"},{"id":387,"name":"通州区"},{"id":388,"name":"顺义区"},{"id":389,"name":"昌平区"},{"id":390,"name":"大兴区"},{"id":391,"name":"怀柔区"},{"id":392,"name":"平谷区"},{"id":393,"name":"密云区"},{"id":394,"name":"延庆区"}]}]
         */

        private int id;
        private String name;
        private List<CitysBean> citys;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CitysBean> getCitys() {
            return citys;
        }

        public void setCitys(List<CitysBean> citys) {
            this.citys = citys;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }

        public static class CitysBean {
            /**
             * id : 375
             * name : 北京
             * countys : [{"id":379,"name":"东城区"},{"id":380,"name":"西城区"},{"id":381,"name":"朝阳区"},{"id":382,"name":"丰台区"},{"id":383,"name":"石景山区"},{"id":384,"name":"海淀区"},{"id":385,"name":"门头沟区"},{"id":386,"name":"房山区"},{"id":387,"name":"通州区"},{"id":388,"name":"顺义区"},{"id":389,"name":"昌平区"},{"id":390,"name":"大兴区"},{"id":391,"name":"怀柔区"},{"id":392,"name":"平谷区"},{"id":393,"name":"密云区"},{"id":394,"name":"延庆区"}]
             */

            private int id;
            private String name;
            private List<CountysBean> countys;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CountysBean> getCountys() {
                return countys;
            }

            public void setCountys(List<CountysBean> countys) {
                this.countys = countys;
            }

            public static class CountysBean {
                /**
                 * id : 379
                 * name : 东城区
                 */

                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
