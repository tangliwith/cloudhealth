package com.ecuca.cloudhealth.Entity;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class ContactFullInfoEntity {
    /**
     * data : {"uid":2,"medical_history":null,"allergic_history":null,"laboratory_sheet_url":"http://192.168.0.15:10037/upload/laboratory_sheet/default.jpg","medical_imaging_url":"http://192.168.0.15:10037/upload/medical_imaging/default.jpg"}
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
         * uid : 2
         * medical_history : null
         * allergic_history : null
         * laboratory_sheet_url : http://192.168.0.15:10037/upload/laboratory_sheet/default.jpg
         * medical_imaging_url : http://192.168.0.15:10037/upload/medical_imaging/default.jpg
         */

        private int uid;
        private String medical_history;
        private String allergic_history;
        private String laboratory_sheet_url;
        private String medical_imaging_url;
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getLaboratory_sheet_url() {
            return laboratory_sheet_url;
        }

        public void setLaboratory_sheet_url(String laboratory_sheet_url) {
            this.laboratory_sheet_url = laboratory_sheet_url;
        }

        public String getMedical_imaging_url() {
            return medical_imaging_url;
        }

        public void setMedical_imaging_url(String medical_imaging_url) {
            this.medical_imaging_url = medical_imaging_url;
        }
    }
}
