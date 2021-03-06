package com.nanchen.rxjava2examples.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-30  15:38
 */

public class MobileAddress {

    /**
     * error_code : 0
     * reason : Succes
     * result : {"mobilenumber":"1302167","mobilearea":"山东 青岛市","mobiletype":"联通如意通卡","areacode":"0532","postcode":"266000"}
     * 聚合api：{"resultcode":"200","reason":"Return Successd!","result":{"province":"浙江","city":"杭州","areacode":"0571","zip":"310000","company":"移动","card":""},"error_code":0}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MobileAddress{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }

    public static class ResultBean {
        /**
         * 聚合api： {"province":"浙江","city":"杭州","areacode":"0571","zip":"310000","company":"移动","card":""}
         *
         * mobilenumber : 1302167
         * mobilearea : 山东 青岛市
         * mobiletype : 联通如意通卡
         * areacode : 0532
         * postcode : 266000
         */

        private String mobilenumber;
        private String mobilearea;
        @SerializedName("company")
        private String mobiletype;
        private String areacode;
        private String postcode;

//        聚合api
        private String province;
        private String city;

        public String getMobilenumber() {
            return mobilenumber;
        }

        public void setMobilenumber(String mobilenumber) {
            this.mobilenumber = mobilenumber;
        }

        public String getMobilearea() {
//            return mobilearea;
            return  province + city ;
        }

        public void setMobilearea(String mobilearea) {
            this.mobilearea = mobilearea;
        }

        public String getMobiletype() {
            return mobiletype;
        }

        public void setMobiletype(String mobiletype) {
            this.mobiletype = mobiletype;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }


        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "mobilenumber='" + mobilenumber + '\'' +
                    ", mobilearea='" + mobilearea + '\'' +
                    ", mobiletype='" + mobiletype + '\'' +
                    ", areacode='" + areacode + '\'' +
                    ", postcode='" + postcode + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}

