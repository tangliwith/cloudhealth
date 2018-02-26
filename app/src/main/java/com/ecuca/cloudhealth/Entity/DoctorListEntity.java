package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/20.
 */

public class DoctorListEntity {


    /**
     * data : [{"uid":155,"user_name":"13543212313","nick_name":"test3","evaluate":"0.0","personal_profile":"123213213","video_price":0,"voice_price":0,"img_price":12,"section":{"title":"中医科"},"invite_count":0,"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg","hospital":{"hospital_name":"成都市人民医院"},"cate":{"title":"普通医生"},"profession":"中医内科,中医外科,中医妇产科,中医儿科"},{"uid":154,"user_name":"13612345210","nick_name":"test2","evaluate":"0.0","personal_profile":"123213213","video_price":0,"voice_price":12,"img_price":10,"section":{"title":"儿科"},"invite_count":0,"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/18e59fe834853a9948289d76cc211445.jpg","hospital":{"hospital_name":"成都市人民医院"},"cate":{"title":"普通医生"},"profession":"中医内科,中医外科,中医妇产科,中医儿科,新生儿专科,小儿内科"},{"uid":153,"user_name":"15693621023","nick_name":"werew","evaluate":"0.0","personal_profile":"卡山东矿机阿萨德建行卡上","video_price":0,"voice_price":50,"img_price":0,"section":{"title":"中医儿科"},"invite_count":0,"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/avatar1.jpg","hospital":{"hospital_name":"成都市武警总医院"},"cate":{"title":"专家"}},{"uid":151,"user_name":"15845620000","nick_name":"航三1","evaluate":"2.5","personal_profile":"哈克斯带回家按实际","video_price":12,"voice_price":12,"img_price":10,"section":{"title":"儿科"},"invite_count":0,"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/17e5a880e5c532cc7c743eb18ddc6c25.jpg","hospital":{"hospital_name":"成都市武警总医院"},"cate":{"title":"知名教授"},"profession":"中医内科,中医外科,中医妇产科,中医儿科,新生儿专科,小儿内科,新生儿专科,小儿内科"}]
     * code : 200
     * msg : ok
     */

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
         * uid : 155
         * user_name : 13543212313
         * nick_name : test3
         * evaluate : 0.0
         * personal_profile : 123213213
         * video_price : 0
         * voice_price : 0
         * img_price : 12
         * section : {"title":"中医科"}
         * invite_count : 0
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg
         * hospital : {"hospital_name":"成都市人民医院"}
         * cate : {"title":"普通医生"}
         * profession : 中医内科,中医外科,中医妇产科,中医儿科
         */

        private int uid;
        private String user_name;
        private String nick_name;
        private double evaluate;
        private String personal_profile;
        private double video_price;
        private double voice_price;
        private double img_price;
        private SectionBean section;
        private int invite_count;
        private String avatar_url;
        private HospitalBean hospital;
        private CateBean cate;
        private String profession;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public double getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(double evaluate) {
            this.evaluate = evaluate;
        }

        public String getPersonal_profile() {
            return personal_profile;
        }

        public void setPersonal_profile(String personal_profile) {
            this.personal_profile = personal_profile;
        }

        public double getVideo_price() {
            return video_price;
        }

        public void setVideo_price(double video_price) {
            this.video_price = video_price;
        }

        public double getVoice_price() {
            return voice_price;
        }

        public void setVoice_price(double voice_price) {
            this.voice_price = voice_price;
        }

        public double getImg_price() {
            return img_price;
        }

        public void setImg_price(double img_price) {
            this.img_price = img_price;
        }

        public SectionBean getSection() {
            return section;
        }

        public void setSection(SectionBean section) {
            this.section = section;
        }

        public int getInvite_count() {
            return invite_count;
        }

        public void setInvite_count(int invite_count) {
            this.invite_count = invite_count;
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

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
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
