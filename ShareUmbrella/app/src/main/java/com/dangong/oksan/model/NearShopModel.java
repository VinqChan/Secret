package com.dangong.oksan.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jian on 2018/9/27.
 */

public class NearShopModel implements Serializable {
    /**
     * code : 200
     * result : [{"address":"11111","city":"厦门市","latitude":24.487216,"userId":0,"province":"福建省","phone":"123","name":"忠仑A","id":11,"beginTime":"1970-01-01 01:52:00","endTime":"1970-01-01 01:52:00","region":"忠仑公园","avgCost":"123","longitude":118.156801},{"address":"大草原","province":"福建省","city":"厦门市","phone":"18959613022","latitude":24.487808,"name":"忠仑B","id":16,"beginTime":"1970-01-01 11:11:00","endTime":"1970-01-01 22:22:00","region":"忠仑公园","avgCost":"11","longitude":118.157376},{"address":"建设北街","city":"厦门市","latitude":24.489468,"userId":0,"province":"福建省","phone":"15606926617","name":"忠仑C","id":17,"beginTime":"1970-01-01 10:48:00","endTime":"1970-01-01 07:56:00","region":"忠仑公园","avgCost":"100","longitude":118.156316},{"address":"昂西","city":"泉州市","latitude":24.468635,"userId":0,"province":"福建省","phone":"15606926617","name":"南安店铺","id":18,"beginTime":"1970-01-01 14:41:00","endTime":"1970-01-01 20:41:00","region":"南安市","avgCost":"100","longitude":118.152597},{"address":"积极","city":"北京城区","latitude":24.468519,"userId":0,"province":"北京市","phone":"156000","name":"哈哈哈","id":19,"beginTime":"1970-01-01 23:13:00","endTime":"1970-01-01 01:11:00","region":"朝阳区","avgCost":"10","longitude":118.094207},{"address":"中心花园","city":"泉州市","latitude":24.461283,"userId":0,"province":"福建省","phone":"1564444","name":"商铺啊啊","id":20,"beginTime":"1970-01-01 00:15:00","endTime":"1970-01-01 03:18:00","region":"惠安县","avgCost":"125","longitude":118.113395},{"address":"吧","city":"唐山市","latitude":24.45681,"userId":0,"province":"河北省","phone":"15606926617","name":"南安商铺","id":24,"beginTime":"1970-01-01 11:53:00","endTime":"1970-01-01 17:53:00","region":"路北区","avgCost":"10","longitude":118.07236},{"address":"福建泉州市","city":"泉州市","latitude":24.974212,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺1","id":27,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.384856},{"address":"福建泉州市","city":"泉州市","latitude":24.972913,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺2","id":28,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.385529},{"address":"福建泉州市","city":"泉州市","latitude":24.979726,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺3","id":29,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.378702},{"address":"福建泉州市","city":"泉州市","latitude":24.970031,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺4","id":30,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.378415},{"address":"福建泉州市","city":"泉州市","latitude":24.96741,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺5","id":31,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.380714},{"address":"福建泉州市","city":"泉州市","latitude":24.973896,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺6","id":32,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.386823},{"address":"福建泉州市","city":"泉州市","latitude":24.973175,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺7","id":33,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.38711},{"address":"福建泉州市","city":"泉州市","latitude":24.975206,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺8","id":34,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.38402},{"address":"福建泉州市","city":"泉州市","latitude":24.97501,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺9","id":35,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.383661},{"address":"福建泉州市","city":"泉州市","latitude":24.973699,"userId":0,"province":"福建省","phone":"13459481003","name":"泉州南安汽车站商铺10","id":36,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"南安汽车站","avgCost":"100","longitude":118.383158},{"address":"福建厦门市","city":"厦门市","latitude":24.46697,"userId":0,"province":"福建省","phone":"13459481003","name":"厦门湖里万达01","id":37,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"湖里","avgCost":"100","longitude":118.086975},{"address":"福建厦门市","city":"厦门市","latitude":24.46762,"userId":0,"province":"福建省","phone":"13459481003","name":"厦门湖里万达02","id":38,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"湖里","avgCost":"100","longitude":118.085956},{"address":"福建厦门市","city":"厦门市","latitude":24.49532,"userId":0,"province":"福建省","phone":"13459481003","name":"厦门思明区软件园二期01","id":39,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"思明","avgCost":"100","longitude":118.187135},{"address":"福建厦门市","city":"厦门市","latitude":24.496669,"userId":0,"province":"福建省","phone":"13459481003","name":"厦门思明区软件园二期02","id":40,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"思明","avgCost":"100","longitude":118.187674},{"address":"福建厦门市","city":"厦门市","latitude":24.473529,"userId":0,"province":"福建省","phone":"13459481003","name":"厦门湖滨南路火车站01","id":41,"beginTime":"2018-04-03 12:18:50","endTime":"2018-04-03 12:18:50","region":"思明","avgCost":"100","longitude":118.122648},{"address":"北京市海淀区香山公园","city":"北京市","latitude":37.1111,"type":1,"userId":48,"province":"北京","putPosition":"香山路88号","name":"香山商铺","id":42,"beginTime":"2018-06-09 12:11:14","endTime":"2018-06-09 12:11:14","region":"海淀区","longitude":116.11116},{"address":"厦门","province":"福建","city":"厦门","latitude":39.6733704,"name":"测试1","id":49,"region":"福建","userId":1,"longitude":116.4111328}]
     */

    private int code;
    private List<ResultBean> result;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return code == 200;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 11111
         * city : 厦门市
         * latitude : 24.487216
         * userId : 0
         * province : 福建省
         * phone : 123
         * name : 忠仑A
         * id : 11
         * beginTime : 1970-01-01 01:52:00
         * endTime : 1970-01-01 01:52:00
         * region : 忠仑公园
         * avgCost : 123
         * longitude : 118.156801
         * type : 1
         * putPosition : 香山路88号
         */

        private String address;
        private String city;
        private double latitude;
        private int userId;
        private String province;
        private String phone;
        private String name;
        private int id;
        private String beginTime;
        private String endTime;
        private String region;
        private String avgCost;
        private double longitude;
        private int type;
        private String putPosition;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getAvgCost() {
            return avgCost;
        }

        public void setAvgCost(String avgCost) {
            this.avgCost = avgCost;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPutPosition() {
            return putPosition;
        }

        public void setPutPosition(String putPosition) {
            this.putPosition = putPosition;
        }
    }
}
