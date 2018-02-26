package com.ecuca.cloudhealth.Entity;

import java.util.List;

/**
 * Created by tuhualong on 2018/1/22.
 */

public class DoctorHomePageEntity {


    /**
     * data : {"uid":151,"user_name":"15845620000","nick_name":"李四","evaluate":"2.5","personal_profile":"哈克斯带回家按实际","section":{"title":"儿科"},"reception":0,"invite_count":0,"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/17e5a880e5c532cc7c743eb18ddc6c25.jpg","hospital":{"hospital_name":"成都市武警总医院"},"cate":{"title":"知名教授"},"profession":"新生儿专科,小儿内科","effect_score":4,"attitude_score":2.5,"evaluate_list":[{"uid":{"user_name":"15693621023","nick":"张*"},"effect":3,"attitude":0,"suggest":"服务差","add_time":"1516607891"},{"uid":{"user_name":"15693621023","nick":"张*"},"effect":5,"attitude":5,"suggest":"服务差","add_time":"1516607803"}]}
     * code : 200
     * msg : ok!
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
         * uid : 151
         * user_name : 15845620000
         * nick_name : 李四
         * evaluate : 2.5
         * personal_profile : 哈克斯带回家按实际
         * section : {"title":"儿科"}
         * reception : 0
         * invite_count : 0
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/17e5a880e5c532cc7c743eb18ddc6c25.jpg
         * hospital : {"hospital_name":"成都市武警总医院"}
         * cate : {"title":"知名教授"}
         * profession : 新生儿专科,小儿内科
         * effect_score : 4
         * attitude_score : 2.5
         * evaluate_list : [{"uid":{"user_name":"15693621023","nick":"张*"},"effect":3,"attitude":0,"suggest":"服务差","add_time":"1516607891"},{"uid":{"user_name":"15693621023","nick":"张*"},"effect":5,"attitude":5,"suggest":"服务差","add_time":"1516607803"}]
         */

        private int uid;
        private String user_name;
        private String nick_name;
        private double evaluate;
        private String personal_profile;
        private SectionBean section;
        private int reception;
        private int invite_count;
        private String avatar_url;
        private HospitalBean hospital;
        private CateBean cate;
        private String profession;
        private double effect_score;

        private double attitude_score;
        private List<EvaluateListBean> evaluate_list;

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

        public SectionBean getSection() {
            return section;
        }

        public void setSection(SectionBean section) {
            this.section = section;
        }

        public int getReception() {
            return reception;
        }

        public void setReception(int reception) {
            this.reception = reception;
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

        public double getEffect_score() {
            return effect_score;
        }

        public void setEffect_score(double effect_score) {
            this.effect_score = effect_score;
        }

        public double getAttitude_score() {
            return attitude_score;
        }

        public void setAttitude_score(double attitude_score) {
            this.attitude_score = attitude_score;
        }

        public List<EvaluateListBean> getEvaluate_list() {
            return evaluate_list;
        }

        public void setEvaluate_list(List<EvaluateListBean> evaluate_list) {
            this.evaluate_list = evaluate_list;
        }

        public static class SectionBean {
            /**
             * title : 儿科
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
             * hospital_name : 成都市武警总医院
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
             * title : 知名教授
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class EvaluateListBean {
            /**
             * uid : {"user_name":"15693621023","nick":"张*"}
             * effect : 3
             * attitude : 0
             * suggest : 服务差
             * add_time : 1516607891
             */

            private UidBean uid;
            private double effect;
            private double attitude;
            private String suggest;
            private String add_time;

            public UidBean getUid() {
                return uid;
            }

            public void setUid(UidBean uid) {
                this.uid = uid;
            }

            public double getEffect() {
                return effect;
            }

            public void setEffect(double effect) {
                this.effect = effect;
            }

            public double getAttitude() {
                return attitude;
            }

            public void setAttitude(double attitude) {
                this.attitude = attitude;
            }

            public String getSuggest() {
                return suggest;
            }

            public void setSuggest(String suggest) {
                this.suggest = suggest;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public static class UidBean {
                /**
                 * user_name : 15693621023
                 * nick : 张*
                 */

                private String user_name;
                private String nick;

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getNick() {
                    return nick;
                }

                public void setNick(String nick) {
                    this.nick = nick;
                }
            }
        }
    }
}
