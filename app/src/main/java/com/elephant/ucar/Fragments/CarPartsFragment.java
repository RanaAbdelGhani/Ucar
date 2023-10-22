package com.elephant.ucar.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Activities.CarActivity;
import com.elephant.ucar.Activities.CompanyDetailsActivity;
import com.elephant.ucar.Adapters.PartsListViewAdapter;
import com.elephant.ucar.Adapters.PeriodicListViewAdapter;
import com.elephant.ucar.Models.ResponseParts;
import com.elephant.ucar.MyUtils.Helper;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarPartsFragment extends Fragment {

    TextView NoPartsText, PartsText, MaintenanceText;

    ListView PartListView, PListView;

    PartsListViewAdapter PartsAdapter;

    PeriodicListViewAdapter MaintenanceAdapter;

    ScrollView scrollView;

    LinearLayout NotAvailableText;

    List<ResponseParts.DataItem> PartsList = new ArrayList<>();
    List<ResponseParts.DataItem> MaintenanceList = new ArrayList<>();

    private DecimalFormat formatter = new DecimalFormat("#,###,###");

    String link = "https://ucars.me/api/ApplicationUcar/typeparts/" + MyUtils.TypeID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_car_parts, container, false);


        PartListView = v.findViewById(R.id.PartsList);
        PListView = v.findViewById(R.id.PList);
        NoPartsText = v.findViewById(R.id.NoPartsText);
        PartsText = v.findViewById(R.id.PartsText);
        MaintenanceText = v.findViewById(R.id.PText);
        scrollView = v.findViewById(R.id.PartsScroll);
        NotAvailableText = v.findViewById(R.id.textlay);

        initiateData();

        MaintenanceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaintenanceText.setEnabled(false);
                PartsText.setEnabled(true);
                PListView.setVisibility(View.VISIBLE);
                PartListView.setVisibility(View.GONE);
                MaintenanceText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.down_arrow, 0, 0, 0);
                PartsText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.car_list_narrow, 0, 0, 0);
                PartsText.setBackgroundResource(R.color.lightgrey);
                MaintenanceText.setBackgroundResource(R.color.grey);
            }
        });

        PartsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaintenanceText.setEnabled(true);
                PartsText.setEnabled(false);
                PartListView.setVisibility(View.VISIBLE);
                PListView.setVisibility(View.GONE);
                PartsText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.down_arrow, 0, 0, 0);
                MaintenanceText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.car_list_narrow, 0, 0, 0);
                PartsText.setBackgroundResource(R.color.grey);
                MaintenanceText.setBackgroundResource(R.color.lightgrey);
            }
    });

    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {

            Intent i = new Intent(getContext(), CompanyDetailsActivity.class);
            startActivity(i);

            CarActivity carActivity = new CarActivity();
            carActivity.finish();
        }
    };

    requireActivity().

    getOnBackPressedDispatcher().

    addCallback((LifecycleOwner) requireContext(),callback);


        return v;
}

    private void initiateData() {
        AndroidNetworking.get(link)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ResponseParts array = gson.fromJson(response.toString(), ResponseParts.class);

                        for (int i = 0; i < array.getData().size(); i++) {
                            if (array.getData().get(i).getMaintenance().equals("0"))
                                PartsList.add(array.getData().get(i));


                            if (array.getData().get(i).getMaintenance().equals("1"))
                                MaintenanceList.add(array.getData().get(i));

                        }

                        if (PartsList.size() == 0) {
                            PartListView.setVisibility(View.GONE);
                            PartsText.setVisibility(View.GONE);
                        }

                        if (MaintenanceList.size() == 0) {
                            PListView.setVisibility(View.GONE);
                            MaintenanceText.setVisibility(View.GONE);

                        }

                        if (MaintenanceList.size() == 0 && PartsList.size() == 0) {
                            NoPartsText.setVisibility(View.VISIBLE);
                            PListView.setVisibility(View.GONE);
                            NotAvailableText.setVisibility(View.GONE);

                        }

                        MaintenanceAdapter = new PeriodicListViewAdapter(requireContext(), R.layout.maintenance, MaintenanceList);
                        PListView.setAdapter(MaintenanceAdapter);
                        Helper.getListViewSize(PListView);


                        PartsAdapter = new PartsListViewAdapter(requireContext(), R.layout.parts_item, PartsList);
                        PartListView.setAdapter(PartsAdapter);
                        Helper.getListViewSize(PartListView);

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