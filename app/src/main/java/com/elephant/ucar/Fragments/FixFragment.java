package com.elephant.ucar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Activities.CompanyDetailsActivity;
import com.elephant.ucar.Activities.MainActivity;
import com.elephant.ucar.Activities.WorkshopActivity;
import com.elephant.ucar.Adapters.FixListViewAdapter;
import com.elephant.ucar.Models.ResponseWorkShop;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FixFragment extends Fragment {

    ListView listView;
    List<ResponseWorkShop.DataItem> items = new ArrayList<>();
    FixListViewAdapter adapter;
    TextView address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fix, container, false);

        listView = v.findViewById(R.id.placesList);
        address = v.findViewById(R.id.Address);
        initiateData();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);

                CompanyDetailsActivity companyDetailsActivity = new CompanyDetailsActivity();
                companyDetailsActivity.finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);


        return v;
    }

    private void initiateData() {
        AndroidNetworking.get("https://ucars.me/api/ApplicationUcar/companyworkshop/" + MyUtils.CompanyID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ResponseWorkShop array = gson.fromJson(response.toString(), ResponseWorkShop.class);

                        items = array.getData();

                        adapter = new FixListViewAdapter(requireContext(), R.layout.fix_list_item, items);


                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                MyUtils.WorkshopID = items.get(position).getId() + "";
                                Intent i = new Intent(getContext(), WorkshopActivity.class);
                                startActivity(i);

                            }
                        });


                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        MyUtils.handleError(getContext(), anError.getErrorBody(), anError.getErrorCode());

                    }
                });

    }

}