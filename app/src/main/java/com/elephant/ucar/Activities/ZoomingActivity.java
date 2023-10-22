package com.elephant.ucar.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Adapters.CarDetailsSliderAdapter;
import com.elephant.ucar.Adapters.CarDetailsSliderAdapterZooming;
import com.elephant.ucar.Adapters.DetailsListViewAdapter;
import com.elephant.ucar.Fragments.CarDetailsFragment;
import com.elephant.ucar.Models.ResponseCars;
import com.elephant.ucar.Models.ResponseType;
import com.elephant.ucar.MyUtils.Helper;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZoomingActivity extends AppCompatActivity {

    PhotoView zoomageView;
    ViewPager viewPager;
    List<String> zoomImage = new ArrayList<>();
    ImageView close;
    String imagelink = MyUtils.CarID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zooming);

        zoomageView=findViewById(R.id.imageViewCarDetailsZooming);
        viewPager=findViewById(R.id.ZoomingSlider);
        close=findViewById(R.id.close_icon);

        getData();

        close.setOnClickListener(view -> {
            zoomImage.clear();
            finish();

        });

    }
public void getData(){
    {
        AndroidNetworking.get(imagelink)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ResponseCars array = gson.fromJson(response.toString(), ResponseCars.class);

                        zoomImage.addAll(array.getData().get(0).getImagescar());
                        viewPager.setAdapter(new CarDetailsSliderAdapterZooming(ZoomingActivity.this,zoomImage));

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ZoomingActivity.this, "error", Toast.LENGTH_SHORT).show();
                        MyUtils.handleError(ZoomingActivity.this, anError.getErrorBody(), anError.getErrorCode());

                    }
                });
    }

}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        zoomImage.clear();
        finish();
    }

}