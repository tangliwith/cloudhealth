package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class ContactInfoEntity {


    /**
     * data : {"address":"PPTV默默","nick_name":"看看","card_type":2,"card_no":"515745863589844","sex":1,"birthday":"2014-01-01","province":{"id":23,"name_chs":"四川"},"city":{"id":127,"name_chs":"金华"},"area":{"id":127,"name_chs":"金华"},"user_name":"18523158103","is_default":1,"mobile":"18523158103","avatar_url":"http://192.168.0.15:10037/upload/avatar_file/","full_information":0}
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
         * address : PPTV默默
         * nick_name : 看看
         * card_type : 2
         * card_no : 515745863589844
         * sex : 1
         * birthday : 2014-01-01
         * province : {"id":23,"name_chs":"四川"}
         * city : {"id":127,"name_chs":"金华"}
         * area : {"id":127,"name_chs":"金华"}
         * user_name : 18523158103
         * is_default : 1
         * mobile : 18523158103
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/
         * full_information : 0
         */

        private String address;
        private String nick_name;
        private int card_type;
        private String card_no;
        private int sex;
        private String birthday;
        private ProvinceBean province;
        private CityBean city;
        private AreaBean area;
        private String user_name;
        private int is_default;
        private String mobile;
        private String avatar_url;
        private int full_information;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public AreaBean getArea() {
            return area;
        }

        public void setArea(AreaBean area) {
            this.area = area;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getFull_information() {
            return full_information;
        }

        public void setFull_information(int full_information) {
            this.full_information = full_information;
        }

        public static class ProvinceBean {
            /**
             * id : 23
             * name_chs : 四川
             */

            private int id;
            private String name_chs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName_chs() {
                return name_chs;
            }

            public void setName_chs(String name_chs) {
                this.name_chs = name_chs;
            }
        }

        public static class CityBean {
            /**
             * id : 127
             * name_chs : 金华
             */

            private int id;
            private String name_chs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName_chs() {
                return name_chs;
            }

            public void setName_chs(String name_chs) {
                this.name_chs = name_chs;
            }
        }

        public static class AreaBean {
            /**
             * id : 127
             * name_chs : 金华
             */

            private int id;
            private String name_chs;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName_chs() {
                return name_chs;
            }

            public void setName_chs(String name_chs) {
                this.name_chs = name_chs;
            }
        }
    }
}
