package com.elephant.ucar.Models;

import java.util.List;

public class ResponseCars {
    private List<DataItem> data;

    public List<DataItem> getData() {
        return data;
    }


    public static class FeaturesItem {
        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class DataItem {
        private int has_features;
        private String Motor;
        private String year;
        private String fuel;
        private List<String> imagescar;
        private String avatar;
        private String maximum_speed;
        private String hosre_power;
        private List<FeaturesItem> features;
        private String Gear_Type;
        private String price;
        private String warranty;
        private String transmission_Type;
        private String consumption_rate;
        private String carclass;
        private String cylinder;
        private String id;
        private String speeds;
        private String engine_capacity;
        private String fuel_tank_capacity;
        private String wheels_tire;
        private String touch_screen;
        private String Acceleration;
        private String power_assisted;
        private String airbags_number;
        private String air_condition;
        private String seats_number;
        private String driver_seat;
        private String passenger_seat;
        private String dashboard;

        public int getHasFeatures() {
            return has_features;
        }

        public String getMotor() {
            return Motor;
        }

        public String getYear() {
            return year;
        }

        public String getFuel() {
            return fuel;
        }

        public List<String> getImagescar() {
            return imagescar;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getMaximumSpeed() {
            return maximum_speed;
        }

        public String getHosrePower() {
            return hosre_power;
        }

        public List<FeaturesItem> getFeatures() {
            return features;
        }

        public String getGearType() {
            return Gear_Type;
        }

        public String getPrice() {
            return price;
        }

        public String getWarranty() {
            return warranty;
        }

        public String getTransmissionType() {
            return transmission_Type;
        }

        public String getConsumptionRate() {
            return consumption_rate;
        }

        public String getCarclass() {
            return carclass;
        }

        public String getCylinder() {
            return cylinder;
        }

        public String getId() {
            return id;
        }

        public String getSpeeds() {
            return speeds;
        }

        public String getEngineCapacity() {
            return engine_capacity;
        }

        public String getFuel_tank_capacity() {
            return fuel_tank_capacity;
        }

        public String getWheels_tire() {
            return wheels_tire;
        }

        public String getTouch_screen() {
            return touch_screen;
        }

        public String getAcceleration() {
            return Acceleration;
        }

        public String getPower_assisted() {
            return power_assisted;
        }

        public String getAir_condition() {
            return air_condition;
        }

        public String getSeats_number() {
            return seats_number;
        }

        public String getDriver_seat() {
            return driver_seat;
        }

        public String getPassenger_seat() {
            return passenger_seat;
        }

        public String getDashboard() {
            return dashboard;
        }
    }
}