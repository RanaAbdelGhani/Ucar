package com.elephant.ucar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Activities.CarActivity;
import com.elephant.ucar.Activities.CompanyDetailsActivity;
import com.elephant.ucar.Activities.MainActivity;
import com.elephant.ucar.Adapters.CustomExpandableListAdapter;
import com.elephant.ucar.Models.ResponseType;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarsFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<ResponseType.DataItem> arraydata = new ArrayList<>();
    public static List<String> expandableListTitle = new ArrayList<>();
    public static List<Integer> expandableListPrice = new ArrayList<>();
    public static List<String> expandableListImage = new ArrayList<>();
    public static HashMap<String, List<String>> expandableListDetail = new HashMap<>();
    String typelink;
    String Carid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cars, container, false);


        typelink = "https://ucars.me/api/ApplicationUcar/carcompany/" + MyUtils.CompanyID;
        expandableListView = v.findViewById(R.id.CarsList);

        initiateData();
        onClick();


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
                expandableListTitle.clear();
                expandableListImage.clear();
                expandableListPrice.clear();
                expandableListDetail.clear();
                CompanyDetailsActivity companyDetailsActivity = new CompanyDetailsActivity();
                companyDetailsActivity.finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);


        return v;
    }

    public void onClick() {
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Carid = arraydata.get(groupPosition).getCars().get(childPosition).getLink() + "";
                MyUtils.CarID = Carid;
                MyUtils.TypeID = arraydata.get(groupPosition).getId();
                expandableListTitle.clear();
                expandableListImage.clear();
                expandableListPrice.clear();
                expandableListDetail.clear();
                Intent i = new Intent(getContext(), CarActivity.class);
                startActivity(i);


                return false;
            }
        });
    }


    private void initiateData() {
        AndroidNetworking.get(typelink)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ResponseType array = gson.fromJson(response.toString(), ResponseType.class);
                        arraydata = array.getData();

                        for (int i = 0; i < arraydata.size(); i++) {
                            expandableListTitle.add(arraydata.get(i).getTitle());
                            expandableListImage.add(arraydata.get(i).getAvatar());
                            expandableListPrice.add(arraydata.get(i).getStartPrice());

                        }

                        for (int i = 0; i < expandableListTitle.size(); i++) {
                            List<String> cars = new ArrayList<>();
                            for (int j = 0; j < array.getData().get(i).getCars().size(); j++) {
                                cars.add(array.getData().get(i).getCars().get(j).getCarclass());
                            }
                            expandableListDetail.put(expandableListTitle.get(i), cars);

                        }

                        expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail, expandableListPrice, expandableListImage);
                        expandableListView.setAdapter(expandableListAdapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        MyUtils.handleError(getContext(), anError.getErrorBody(), anError.getErrorCode());

                    }
                });

    }
}