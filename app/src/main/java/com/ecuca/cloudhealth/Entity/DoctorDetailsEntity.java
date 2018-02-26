package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/21.
 */

public class DoctorDetailsEntity {


    /**
     * data : {"nick_name":"test3","personal_profile":"123213213","section":{"title":"中医科"},"profession":"中医内科,中医外科,中医妇产科,中医儿科","avatar_url":"http://192.168.0.15:10037/upload/avatar/d22e473650dffe708b676f4bc24480e6.jpg","hospital":{"hospital_name":"成都市人民医院"},"cate":{"title":"普通医生"}}
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
         * nick_name : test3
         * personal_profile : 123213213
         * section : {"title":"中医科"}
         * profession : 中医内科,中医外科,中医妇产科,中医儿科
         * avatar_url : http://192.168.0.15:10037/upload/avatar/d22e473650dffe708b676f4bc24480e6.jpg
         * hospital : {"hospital_name":"成都市人民医院"}
         * cate : {"title":"普通医生"}
         */

        private String nick_name;
        private String personal_profile;
        private SectionBean section;
        private String profession;
        private String avatar_url;
        private HospitalBean hospital;
        private CateBean cate;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPersonal_profile() {
            return personal_profile;
        }

        public void setPersonal_profile(String personal_profile) {
            this.personal_profile = personal_profile;
        }

        public SectionBean getSection() {
            return section;
        }

        public void setSection(SectionBean section) {
            this.section = section;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public HospitalBean getHospital() {
            return hospital;
        }

        public void setHospital(HospitalBean hospital) {
            this.hospital = hospital;
        }

        public CateBean getCate() {
            return cate;
        }

        public void setCate(CateBean cate) {
            this.cate = cate;
        }

        public static class SectionBean {
            /**
             * title : 中医科
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class HospitalBean {
            /**
             * hospital_name : 成都市人民医院
             */

            private String hospital_name;

            public String getHospital_name() {
                return hospital_name;
            }

            public void setHospital_name(String hospital_name) {
                this.hospital_name = hospital_name;
            }
        }

        public static class CateBean {
            /**
             * title : 普通医生
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
