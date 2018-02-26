package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class CallDoctorDetailsEntity {


    /**
     * data : {"personal_profile":"123213213","img_price":12,"section":{"title":"中医科"},"avatar_url":"http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg","hospital":{"hospital_name":"成都市人民医院"},"cate":{"title":"普通医生"},"profession":"中医内科,中医外科,中医妇产科,中医儿科","description":"<p><span style=\"color: rgb(14, 23, 38); font-family: \" pingfang=\"\" sc\",=\"\" -apple-system,=\"\" blinkmacsystemfont,=\"\" roboto,=\"\" \"helvetica=\"\" neue\",=\"\" helvetica,=\"\" arial,=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" \"source=\"\" han=\"\" sans\",=\"\" \"noto=\"\" cjk=\"\" \"microsoft=\"\" yahei\",=\"\" jhenghei\",=\"\" sans-serif;=\"\" background-color:=\"\" rgb(244,=\"\" 247,=\"\" 253);\"=\"\">1.本次咨询为限时付费咨询，您可向医生发送8条（24小时内）信息。医生将在24小时内回复您的问题。<\/span><\/p><p><span style=\"color: rgb(14, 23, 38); font-family: \" pingfang=\"\" sc\",=\"\" -apple-system,=\"\" blinkmacsystemfont,=\"\" roboto,=\"\" \"helvetica=\"\" neue\",=\"\" helvetica,=\"\" arial,=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" \"source=\"\" han=\"\" sans\",=\"\" \"noto=\"\" cjk=\"\" \"microsoft=\"\" yahei\",=\"\" jhenghei\",=\"\" sans-serif;=\"\" background-color:=\"\" rgb(244,=\"\" 247,=\"\" 253);\"=\"\">\r\n2.为珍惜您的咨询时间，请全部围绕病情进行沟通，避免无关内容。<\/span><\/p><p><span style=\"color: rgb(14, 23, 38); font-family: \" pingfang=\"\" sc\",=\"\" -apple-system,=\"\" blinkmacsystemfont,=\"\" roboto,=\"\" \"helvetica=\"\" neue\",=\"\" helvetica,=\"\" arial,=\"\" \"hiragino=\"\" sans=\"\" gb\",=\"\" \"source=\"\" han=\"\" sans\",=\"\" \"noto=\"\" cjk=\"\" \"microsoft=\"\" yahei\",=\"\" jhenghei\",=\"\" sans-serif;=\"\" background-color:=\"\" rgb(244,=\"\" 247,=\"\" 253);\"=\"\">\r\n3.如果付费24小时后，医师没有进行答复，订单将自动取消并全部退款至您的账户。<\/span><br><\/p>","promise":"<p><span style=\"color: rgb(14, 23, 38); font-family: \"PingFang SC\", -apple-system, BlinkMacSystemFont, Roboto, \"Helvetica Neue\", Helvetica, Arial, \"Hiragino Sans GB\", \"Source Han Sans\", \"Noto Sans CJK Sc\", \"Microsoft YaHei\", \"Microsoft Jhenghei\", sans-serif; background-color: rgb(244, 247, 253);\">1.如果医生24小时内没有回复，或者超过24小时才回复，我们将全额退还您所支付的费用；<\/span><\/p><p><span style=\"color: rgb(14, 23, 38); font-family: \"PingFang SC\", -apple-system, BlinkMacSystemFont, Roboto, \"Helvetica Neue\", Helvetica, Arial, \"Hiragino Sans GB\", \"Source Han Sans\", \"Noto Sans CJK Sc\", \"Microsoft YaHei\", \"Microsoft Jhenghei\", sans-serif; background-color: rgb(244, 247, 253);\">\r\n2.如果您对医生回复不满意，可向我司客服投诉，若情况属实，我们将本次费用全额退款给您；\r\n3.我们将竭诚为您服务，保障您的权益。<\/span><br><\/p>"}
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
         * personal_profile : 123213213
         * img_price : 12
         * section : {"title":"中医科"}
         * avatar_url : http://192.168.0.15:10037/upload/avatar_file/d22e473650dffe708b676f4bc24480e6.jpg
         * hospital : {"hospital_name":"成都市人民医院"}
         * cate : {"title":"普通医生"}
         * profession : 中医内科,中医外科,中医妇产科,中医儿科
         * description : <p><span style="color: rgb(14, 23, 38); font-family: " pingfang="" sc",="" -apple-system,="" blinkmacsystemfont,="" roboto,="" "helvetica="" neue",="" helvetica,="" arial,="" "hiragino="" sans="" gb",="" "source="" han="" sans",="" "noto="" cjk="" "microsoft="" yahei",="" jhenghei",="" sans-serif;="" background-color:="" rgb(244,="" 247,="" 253);"="">1.本次咨询为限时付费咨询，您可向医生发送8条（24小时内）信息。医生将在24小时内回复您的问题。</span></p><p><span style="color: rgb(14, 23, 38); font-family: " pingfang="" sc",="" -apple-system,="" blinkmacsystemfont,="" roboto,="" "helvetica="" neue",="" helvetica,="" arial,="" "hiragino="" sans="" gb",="" "source="" han="" sans",="" "noto="" cjk="" "microsoft="" yahei",="" jhenghei",="" sans-serif;="" background-color:="" rgb(244,="" 247,="" 253);"="">
         2.为珍惜您的咨询时间，请全部围绕病情进行沟通，避免无关内容。</span></p><p><span style="color: rgb(14, 23, 38); font-family: " pingfang="" sc",="" -apple-system,="" blinkmacsystemfont,="" roboto,="" "helvetica="" neue",="" helvetica,="" arial,="" "hiragino="" sans="" gb",="" "source="" han="" sans",="" "noto="" cjk="" "microsoft="" yahei",="" jhenghei",="" sans-serif;="" background-color:="" rgb(244,="" 247,="" 253);"="">
         3.如果付费24小时后，医师没有进行答复，订单将自动取消并全部退款至您的账户。</span><br></p>
         * promise : <p><span style="color: rgb(14, 23, 38); font-family: "PingFang SC", -apple-system, BlinkMacSystemFont, Roboto, "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "Source Han Sans", "Noto Sans CJK Sc", "Microsoft YaHei", "Microsoft Jhenghei", sans-serif; background-color: rgb(244, 247, 253);">1.如果医生24小时内没有回复，或者超过24小时才回复，我们将全额退还您所支付的费用；</span></p><p><span style="color: rgb(14, 23, 38); font-family: "PingFang SC", -apple-system, BlinkMacSystemFont, Roboto, "Helvetica Neue", Helvetica, Arial, "Hiragino Sans GB", "Source Han Sans", "Noto Sans CJK Sc", "Microsoft YaHei", "Microsoft Jhenghei", sans-serif; background-color: rgb(244, 247, 253);">
         2.如果您对医生回复不满意，可向我司客服投诉，若情况属实，我们将本次费用全额退款给您；
         3.我们将竭诚为您服务，保障您的权益。</span><br></p>
         */

        private String nick_name;
        private String personal_profile;
        private int price;
        private SectionBean section;
        private String avatar_url;
        private HospitalBean hospital;
        private CateBean cate;
        private String profession;
        private String description;
        private String promise;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPromise() {
            return promise;
        }

        public void setPromise(String promise) {
            this.promise = promise;
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
