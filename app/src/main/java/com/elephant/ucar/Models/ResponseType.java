package com.elephant.ucar.Models;

import java.util.List;

public class ResponseType {
    private List<DataItem> data;

    public List<DataItem> getData() {
        return data;
    }


    public static class DataItem {
        private String mid_usedprice;
        private List<CarsItem> cars;
        private String max_usedprice;
        private String id;
        private String avatar;
        private String title;
        private String min_usedprice;
        private int start_price;
        private String last_price;

        public String getMidUsedprice() {
            return mid_usedprice;
        }

        public List<CarsItem> getCars() {
            return cars;
        }

        public String getMaxUsedprice() {
            return max_usedprice;
        }

        public String getId() {
            return id;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getTitle() {
            return title;
        }

        public String getMinUsedprice() {
            return min_usedprice;
        }

        public int getStartPrice() {
            return start_price;
        }

        public String getLastPrice() {
            return last_price;
        }
    }

    public static class CarsItem {
        private String link;
        private String carclass;
        private String id;

        public String getLink() {
            return link;
        }

        public String getCarclass() {
            return carclass;
        }

        public String getId() {
            return id;
        }

    }

}