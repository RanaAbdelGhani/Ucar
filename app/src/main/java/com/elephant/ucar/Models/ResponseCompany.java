package com.elephant.ucar.Models;

import java.util.List;

public class ResponseCompany {
    private List<DataItemCompany> data;

    public List<DataItemCompany> getData() {
        return data;
    }

    public static class DataItemCompany {
        private int id;
        private String avatar;
        private String title;

        public int getId() {
            return id;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getTitle() {
            return title;
        }
    }


}