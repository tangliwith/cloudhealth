package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class UserInfoEntity {


    /**
     * data : {"uid":159,"sex":1,"nick_name":"bbb","address":"","birthday":315504000,"province":{"id":1,"name_chs":"北京"},"city":{"id":375,"name_chs":"北京"},"area":{"id":379,"name_chs":"东城区"},"medical_history":"","allergic_history":"","message":"","medical_imaging":null,"laboratory_sheet":null,"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/87235e5d3629404edac099beb20e7242.png"}
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
         * uid : 159
         * sex : 1
         * nick_name : bbb
         * address :
         * birthday : 315504000
         * province : {"id":1,"name_chs":"北京"}
         * city : {"id":375,"name_chs":"北京"}
         * area : {"id":379,"name_chs":"东城区"}
         * medical_history :
         * allergic_history :
         * message :
         * medical_imaging : null
         * laboratory_sheet : null
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/87235e5d3629404edac099beb20e7242.png
         */

        private int uid;
        private int sex;
        private String nick_name;
        private String address;
        private String birthday;
        private ProvinceBean province;
        private CityBean city;
        private AreaBean area;
        private String medical_history;
        private String allergic_history;
        private String message;
        private String medical_imaging_url;
        private String laboratory_sheet_url;
        private String avatar_url;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getMedical_history() {
            return medical_history;
        }

        public void setMedical_history(String medical_history) {
            this.medical_history = medical_history;
        }

        public String getAllergic_history() {
            return allergic_history;
        }

        public void setAllergic_history(String allergic_history) {
            this.allergic_history = allergic_history;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMedical_imaging_url() {
            return medical_imaging_url;
        }

        public void setMedical_imaging_url(String medical_imaging_url) {
            this.medical_imaging_url = medical_imaging_url;
        }

        public String getLaboratory_sheet_url() {
            return laboratory_sheet_url;
        }

        public void setLaboratory_sheet_url(String laboratory_sheet_url) {
            this.laboratory_sheet_url = laboratory_sheet_url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public static class ProvinceBean {
            /**
             * id : 1
             * name_chs : 北京
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
             * id : 375
             * name_chs : 北京
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
             * id : 379
             * name_chs : 东城区
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
