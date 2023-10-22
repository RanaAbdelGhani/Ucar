package com.elephant.ucar.Models;

import java.util.List;

public class ResponseWorkShop {
    private List<DataItem> data;

    public List<DataItem> getData() {
        return data;
    }

    public class DataItem {
        private List<String> images;
        private String discription;
        private String id;
        private String avatar;
        private String title;
        private String phone;
        private String location;
        private String dis_offer;
        private String offer;
        private String address;

        public List<String> getImages() {
            return images;
        }

        public String getDiscription() {
            return discription;
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

        public String getLocation() {
            return location;
        }

        public String getPhone() {
            return phone;
        }

        public String getOffer() {
            return offer;
        }

        public String getDis_offer() {
            return dis_offer;
        }

        public String getAddress() {
            return address;
        }
    }
}