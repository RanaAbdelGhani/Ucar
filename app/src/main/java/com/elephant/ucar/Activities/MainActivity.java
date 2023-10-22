package com.elephant.ucar.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Adapters.RecyclerViewAdapter;
import com.elephant.ucar.Models.ResponseCompany;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    List<ResponseCompany.DataItemCompany> listaccess = new ArrayList<>();
    RecyclerView cars;
    RecyclerViewAdapter mAdapter;
    Button NavOpen , Discount;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    DrawerLayout drawer;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Get_Data();
        Definitions();
        RecyclerList();
        OnClick();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.NavAbout:
                Intent i=new Intent(MainActivity.this,AboutUs.class);
                startActivity(i);
                break;

            case R.id.NavContact:
                Intent c=new Intent(MainActivity.this,ContactUs.class);
                startActivity(c);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void Definitions(){
        cars=findViewById(R.id.Cars_Company);
        NavOpen=findViewById(R.id.NavBarButton);
        Discount=findViewById(R.id.DiscountsButton);
        navigationView = findViewById(R.id.NavViewer);
        drawer =  findViewById(R.id.drawer_layout);
    }

    private void OnClick(){

        Discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,DiscountActivity.class);
                startActivity(i);
            }
        });

        NavOpen.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.END);
            }
        });


    }

    private void RecyclerList(){

        cars.setHasFixedSize(true);

        mAdapter = new RecyclerViewAdapter(MainActivity.this, listaccess);

        RecyclerView.LayoutManager LayoutManager =
                new GridLayoutManager(this,3, GridLayoutManager.VERTICAL, false);
        cars.setLayoutManager(LayoutManager);
        cars.setAdapter(mAdapter);

    }

    private void Get_Data() {

        AndroidNetworking.get("https://ucars.me/api/ApplicationUcar/carcompanies")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ResponseCompany array = gson.fromJson(response.toString(), ResponseCompany.class);

                        listaccess = array.getData();
                        cars.setAdapter(new RecyclerViewAdapter(MainActivity.this,listaccess));

                    }

                    @Override
                    public void onError(ANError anError) {
                     MyUtils.handleError(MainActivity.this , anError.getErrorBody() , anError.getErrorCode());

                    }
                });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();

    }
}