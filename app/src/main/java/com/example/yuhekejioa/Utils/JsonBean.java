package com.example.yuhekejioa.Utils;



import java.util.List;

public class JsonBean {

    private String name;
    private String code;
    private List<CityBean> cityList;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", cityList=" + cityList +
                '}';
    }

    public static class CityBean {
        private String name;
        private String code;
        private List<City> cityList;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<City> getArea() {
            return cityList;
        }

        @Override
        public String toString() {
            return "CityBean{" +
                    "name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", cityList=" + cityList +
                    '}';
        }
    }

    public static class City {

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}
