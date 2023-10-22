package com.elephant.ucar.Models;

import java.util.List;

public class ResponseParts {
    private List<DataItem> data;

    public List<DataItem> getData() {
        return data;
    }

    public static class DataItem {
        private int price;
        private String id;
        private String avatar;
        private String title;
        private String maintenance;

        public int getPrice() {
            return price;
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

        public String getMaintenance() {
            return maintenance;
        }
    }

}