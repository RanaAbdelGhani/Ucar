package com.elephant.ucar.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.pm.PermissionInfoCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.ViewPager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Activities.CarActivity;
import com.elephant.ucar.Activities.CompanyDetailsActivity;
import com.elephant.ucar.Activities.ZoomingActivity;
import com.elephant.ucar.Adapters.CarDetailsSliderAdapter;
import com.elephant.ucar.Adapters.DetailsListViewAdapter;
import com.elephant.ucar.Models.ResponseCars;
import com.elephant.ucar.Models.ResponseType;
import com.elephant.ucar.MyUtils.Helper;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.security.Provider;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarDetailsFragment extends Fragment {

    TextView motor, price, year, gear, warranty, engineCapacity, maxSpeed, HP, Speeds, rate, cylinder, fuel, carClass, maxprice, minprice, midprice, usedtext, WeelBase, Torque, FuelTankC, Seats, TS, Air, Acc, Power, Insurance, Dashboard, DriverSeat;
    LinearLayout usedLinear, motorL, yearL, gearL, warrantyL, engineCapacityL, maxSpeedL, HPL, SpeedsL, rateL, cylinderL, fuelL, WeelBaseL, TorqueL, FuelTankCL, SeatsL, TSL, AirL, AccL, PowerL, DashboardL, DriverSeatL;
    List<String> images = new ArrayList<>();
    List<String> Fit = new ArrayList<>();
    String link = MyUtils.CarID;
    ViewPager viewPager;
    DetailsListViewAdapter adapter;
    ListView listView;
    ScrollView scrollView;
    DecimalFormat formatter = new DecimalFormat("#,###,###");


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car_details, container, false);

        Definitions(v);
        initiateData();


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                Intent i = new Intent(getContext(), CompanyDetailsActivity.class);
                startActivity(i);

                CarActivity carActivity = new CarActivity();
                images.clear();
                carActivity.finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback((LifecycleOwner) requireContext(), callback);

        return v;
    }

    private void initiateData() {
        //For Car Data
        {
            AndroidNetworking.get(link)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(JSONObject response) {

                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            ResponseCars array = gson.fromJson(response.toString(), ResponseCars.class);

                            if (array.getData().get(0).getMotor() == null || array.getData().get(0).getMotor().equals("null"))
                                motorL.setVisibility(View.GONE);
                            else
                                motor.setText(array.getData().get(0).getMotor());


                            if (array.getData().get(0).getPrice() == null || array.getData().get(0).getPrice().equals("null"))
                                price.setVisibility(View.GONE);
                            else {
                                int num = Integer.parseInt(array.getData().get(0).getPrice());
                                String incText = formatter.format(num);
                                price.setText(incText + "  EGP");
                            }

                            if (array.getData().get(0).getYear() == null || array.getData().get(0).getYear().equals("null"))
                                yearL.setVisibility(View.GONE);
                            else
                                year.setText(array.getData().get(0).getYear());


                            if (array.getData().get(0).getGearType() == null || array.getData().get(0).getGearType().equals("null"))
                                gearL.setVisibility(View.GONE);
                            else
                                gear.setText(array.getData().get(0).getGearType());


                            if (array.getData().get(0).getWarranty() == null || array.getData().get(0).getWarranty().equals("null"))
                                warrantyL.setVisibility(View.GONE);
                            else
                                warranty.setText(array.getData().get(0).getWarranty());


                            if (array.getData().get(0).getEngineCapacity() == null || array.getData().get(0).getEngineCapacity().equals("null"))
                                engineCapacityL.setVisibility(View.GONE);
                            else
                                engineCapacity.setText(array.getData().get(0).getEngineCapacity());


                            if (array.getData().get(0).getMaximumSpeed() == null || array.getData().get(0).getMaximumSpeed().equals("null"))
                                maxSpeedL.setVisibility(View.GONE);
                            else
                                maxSpeed.setText(array.getData().get(0).getMaximumSpeed());


                            if (array.getData().get(0).getHosrePower() == null || array.getData().get(0).getHosrePower().equals("null"))
                                HPL.setVisibility(View.GONE);
                            else
                                HP.setText(array.getData().get(0).getHosrePower());


                            if (array.getData().get(0).getSpeeds() == null || array.getData().get(0).getSpeeds().equals("null"))
                                SpeedsL.setVisibility(View.GONE);
                            else
                                Speeds.setText(array.getData().get(0).getSpeeds());


                            if (array.getData().get(0).getConsumptionRate() == null || array.getData().get(0).getConsumptionRate().equals("null"))
                                rateL.setVisibility(View.GONE);
                            else
                                rate.setText(array.getData().get(0).getConsumptionRate());


                            if (array.getData().get(0).getCylinder() == null || array.getData().get(0).getCylinder().equals("null"))
                                cylinderL.setVisibility(View.GONE);
                            else
                                cylinder.setText(array.getData().get(0).getCylinder());


                            if (array.getData().get(0).getFuel_tank_capacity() == null || array.getData().get(0).getFuel_tank_capacity().equals("null"))
                                FuelTankCL.setVisibility(View.GONE);
                            else
                                FuelTankC.setText(array.getData().get(0).getFuel_tank_capacity());


                            if (array.getData().get(0).getWheels_tire() == null || array.getData().get(0).getWheels_tire().equals("null"))
                                WeelBaseL.setVisibility(View.GONE);
                            else
                                WeelBase.setText(array.getData().get(0).getWheels_tire());


                            if (array.getData().get(0).getSeats_number() == null || array.getData().get(0).getSeats_number().equals("null"))
                                SeatsL.setVisibility(View.GONE);
                            else
                                Seats.setText(array.getData().get(0).getSeats_number());


                            if (array.getData().get(0).getFuel() == null || array.getData().get(0).getFuel().equals("null"))
                                fuelL.setVisibility(View.GONE);
                            else
                                fuel.setText(array.getData().get(0).getFuel());


                            if (array.getData().get(0).getTouch_screen() == null || array.getData().get(0).getTouch_screen().equals("null"))
                                TSL.setVisibility(View.GONE);
                            else
                                TS.setText(array.getData().get(0).getTouch_screen());


                            if (array.getData().get(0).getAir_condition() == null || array.getData().get(0).getAir_condition().equals("null"))
                                AirL.setVisibility(View.GONE);
                            else
                                Air.setText(array.getData().get(0).getAir_condition());


                            if (array.getData().get(0).getAcceleration() == null || array.getData().get(0).getAcceleration().equals("null"))
                                AccL.setVisibility(View.GONE);
                            else
                                Acc.setText(array.getData().get(0).getAcceleration());


                            if (array.getData().get(0).getPower_assisted() == null || array.getData().get(0).getPower_assisted().equals("null"))
                                PowerL.setVisibility(View.GONE);
                            else
                                Power.setText(array.getData().get(0).getPower_assisted());


                            if (array.getData().get(0).getPassenger_seat() == null || array.getData().get(0).getPassenger_seat().equals("null"))
                                TorqueL.setVisibility(View.GONE);
                            else
                                Torque.setText(array.getData().get(0).getPassenger_seat());


                            if (array.getData().get(0).getCarclass() == null || array.getData().get(0).getCarclass().equals("null"))
                                carClass.setVisibility(View.GONE);
                            else
                                carClass.setText(array.getData().get(0).getCarclass());


                            if (array.getData().get(0).getDashboard() == null || array.getData().get(0).getDashboard().equals("null"))
                                DashboardL.setVisibility(View.GONE);
                            else
                                Dashboard.setText(array.getData().get(0).getDashboard());


                            if (array.getData().get(0).getDriver_seat() == null || array.getData().get(0).getDriver_seat().equals("null"))
                                DriverSeatL.setVisibility(View.GONE);
                            else
                                DriverSeat.setText(array.getData().get(0).getDriver_seat());


                            for (int i = 0; i < array.getData().get(0).getFeatures().size(); i++) {
                                Fit.add(array.getData().get(0).getFeatures().get(i).getTitle());
                            }

                            int num = (int) (Integer.parseInt(array.getData().get(0).getPrice()) * 0.025);
                            String incText = formatter.format(num);
                            Insurance.setText(Insurance.getText() + incText + "  EGP");


                            //Slider Data
                            images.addAll(array.getData().get(0).getImagescar());
                            viewPager.setAdapter(new CarDetailsSliderAdapter(getActivity(), images));

                            //ListView Data
                            adapter = new DetailsListViewAdapter(requireContext(), R.layout.details_item, Fit);
                            listView.setAdapter(adapter);
                            Helper.getListViewSize(listView);

                            //Used Prices Data
                            {
                                AndroidNetworking.get("https://ucars.me/api/ApplicationUcar/carcompany/" + MyUtils.CompanyID)
                                        .setPriority(Priority.HIGH)
                                        .build()
                                        .getAsJSONObject(new JSONObjectRequestListener() {
                                            @SuppressLint("SetTextI18n")
                                            @Override
                                            public void onResponse(JSONObject response) {

                                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                                ResponseType array = gson.fromJson(response.toString(), ResponseType.class);

                                                if (array.getData().get(0).getMaxUsedprice() == null || array.getData().get(0).getMaxUsedprice().equals("null"))
                                                    maxprice.setVisibility(View.GONE);

                                                else
                                                    maxprice.setText(maxprice.getText() + array.getData().get(0).getMaxUsedprice());


                                                if (array.getData().get(0).getMinUsedprice() == null || array.getData().get(0).getMinUsedprice().equals("null"))
                                                    minprice.setVisibility(View.GONE);

                                                else
                                                    minprice.setText(minprice.getText() + array.getData().get(0).getMinUsedprice());

                                                if (array.getData().get(0).getMidUsedprice() == null || array.getData().get(0).getMidUsedprice().equals("null"))
                                                    midprice.setVisibility(View.GONE);

                                                else
                                                    midprice.setText(midprice.getText() + array.getData().get(0).getMidUsedprice());

                                                if (array.getData().get(0).getMidUsedprice() == null && array.getData().get(0).getMinUsedprice() == null && array.getData().get(0).getMaxUsedprice() == null)
                                                    usedLinear.setVisibility(View.GONE);


                                                scrollView.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onError(ANError anError) {
                                                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                                                MyUtils.handleError(getContext(), anError.getErrorBody(), anError.getErrorCode());

                                            }
                                        });
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                            MyUtils.handleError(getContext(), anError.getErrorBody(), anError.getErrorCode());

                        }
                    });
        }
    }

    private void Definitions(View v) {

        //TextView
        {
            Insurance = v.findViewById(R.id.InsuranceAmount);
            motor = v.findViewById(R.id.Motor);
            price = v.findViewById(R.id.price);
            year = v.findViewById(R.id.Year);
            gear = v.findViewById(R.id.GearType);
            warranty = v.findViewById(R.id.warranty);
            engineCapacity = v.findViewById(R.id.EngineCapacity);
            maxSpeed = v.findViewById(R.id.MaxSpeed);
            HP = v.findViewById(R.id.HorsePower);
            Speeds = v.findViewById(R.id.Speeds);
            rate = v.findViewById(R.id.consumptionRate);
            cylinder = v.findViewById(R.id.Cylinders);
            fuel = v.findViewById(R.id.Fuel);
            carClass = v.findViewById(R.id.CarClass);
            maxprice = v.findViewById(R.id.MaxUsedPrice);
            minprice = v.findViewById(R.id.MinUsedPrice);
            midprice = v.findViewById(R.id.MidUsedPrice);
            usedtext = v.findViewById(R.id.usedtext);
            FuelTankC = v.findViewById(R.id.FuelTankCapacity);
            WeelBase = v.findViewById(R.id.Wheel);
            Torque = v.findViewById(R.id.Torque);
            Seats = v.findViewById(R.id.Seats);
            TS = v.findViewById(R.id.TouchScreen);
            Air = v.findViewById(R.id.air_condition);
            Power = v.findViewById(R.id.power_assisted);
            Acc = v.findViewById(R.id.Acceleration);
            Dashboard = v.findViewById(R.id.Dashboard);
            DriverSeat = v.findViewById(R.id.DriverSeat);

        }


        //Layouts
        {
            scrollView = v.findViewById(R.id.DetailsScroll);
            motorL = v.findViewById(R.id.motorL);
            yearL = v.findViewById(R.id.ModelL);
            gearL = v.findViewById(R.id.gearL);
            warrantyL = v.findViewById(R.id.warrantyL);
            engineCapacityL = v.findViewById(R.id.EngineL);
            maxSpeedL = v.findViewById(R.id.maxspeedL);
            HPL = v.findViewById(R.id.horseL);
            SpeedsL = v.findViewById(R.id.speedsL);
            rateL = v.findViewById(R.id.consumptionRateL);
            cylinderL = v.findViewById(R.id.CylindersL);
            fuelL = v.findViewById(R.id.FuelL);
            usedLinear = v.findViewById(R.id.usedLinear);
            FuelTankCL = v.findViewById(R.id.TankCL);
            WeelBaseL = v.findViewById(R.id.WheelL);
            TorqueL = v.findViewById(R.id.TorqueL);
            SeatsL = v.findViewById(R.id.SeatsL);
            TSL = v.findViewById(R.id.TouchScreenL);
            AirL = v.findViewById(R.id.air_conditionL);
            PowerL = v.findViewById(R.id.power_assistedL);
            AccL = v.findViewById(R.id.AccelerationL);
            DashboardL = v.findViewById(R.id.DashboardL);
            DriverSeatL = v.findViewById(R.id.DriverSeatL);


        }


        //ListViews
        {
            viewPager = v.findViewById(R.id.CarDetailsSlider);
            listView = v.findViewById(R.id.DetailsList);
        }

    }

}