package com.elephant.ucar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Adapters.DiscountListViewAdapter;
import com.elephant.ucar.Models.ResponseWorkShop;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class DiscountActivity extends AppCompatActivity {

    ListView listView;
    DiscountListViewAdapter adapter;
    TextView text;
    List<ResponseWorkShop.DataItem> dis_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        listView=findViewById(R.id.DiscountList);
        text=findViewById(R.id.OfferText);
        initiateData();

    }

    private void initiateData() {
        AndroidNetworking.get("https://ucars.me/api/ApplicationUcar/allworkshops")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ResponseWorkShop array = gson.fromJson(response.toString(), ResponseWorkShop.class);

                        for(int i=0;i<array.getData().size();i++) {

                            if (array.getData().get(i).getOffer().equals("1"))
                                dis_items.add(array.getData().get(i));
                        }

                        if(dis_items.size()==0)
                            text.setVisibility(View.VISIBLE);

                        adapter=new DiscountListViewAdapter(DiscountActivity.this, R.layout.discount_item,dis_items);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                MyUtils.WorkshopID=dis_items.get(position).getId()+"";
                                Intent i=new Intent(DiscountActivity.this, WorkshopActivity.class);
                                startActivity(i);

                            }
                        });


                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(DiscountActivity.this, "error", Toast.LENGTH_SHORT).show();
                        MyUtils.handleError(DiscountActivity.this, anError.getErrorBody() , anError.getErrorCode());

                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}