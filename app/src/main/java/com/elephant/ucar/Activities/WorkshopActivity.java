package com.elephant.ucar.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.elephant.ucar.Adapters.WorkshopDetailsSliderAdapter;
import com.elephant.ucar.Models.ResponseWorkShop;
import com.elephant.ucar.MyUtils.CustomDialogClass;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkshopActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView call,location;
    String link="https://ucars.me/api/ApplicationUcar/workshop/"+ MyUtils.WorkshopID;
    TextView Title,Dis,offerDis,offerText;
    List<String> images=new ArrayList<>();
    String PhoneNum;
    String Map=null;
    List<String> nums=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop);

        viewPager = (ViewPager) findViewById(R.id.WorkshopDetailsSlider);
        call=findViewById(R.id.CallWorkshop);
        location=findViewById(R.id.LocationWorkshop);
        Title=findViewById(R.id.WorkshopTitle);
        Dis=findViewById(R.id.WorkshopDescription);
        offerDis=findViewById(R.id.OfferDescription);
        offerText=findViewById(R.id.OfferDescriptionText);


        initiateData();
        onClick();
    }

    private void initiateData() {
        AndroidNetworking.get(link)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ResponseWorkShop array = gson.fromJson(response.toString(), ResponseWorkShop.class);

                        Title.setText(array.getData().get(0).getTitle());
                        Dis.setText(array.getData().get(0).getDiscription());
                        Map = array.getData().get(0).getLocation();
                        PhoneNum=array.getData().get(0).getPhone();
                        offerDis.setText(array.getData().get(0).getDis_offer());

                        if (array.getData().get(0).getPhone() != null)
                            nums = Arrays.asList(array.getData().get(0).getPhone().split("-"));



                        if (!offerDis.getText() .equals("")){
                            offerDis.setVisibility(View.VISIBLE);
                            offerText.setVisibility(View.VISIBLE);
                        }

                        if(nums.size()!=0){
                            call.setVisibility(View.VISIBLE);
                        }

                        if(!array.getData().get(0).getLocation().equals("")){
                            location.setVisibility(View.VISIBLE);
                        }


                        if(array.getData().get(0).getImages().size()==0)
                            images.add(array.getData().get(0).getAvatar());
                        else
                            images.addAll(array.getData().get(0).getImages());

                        viewPager.setAdapter(new WorkshopDetailsSliderAdapter(WorkshopActivity.this , images));
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(WorkshopActivity.this, "error", Toast.LENGTH_SHORT).show();
                        MyUtils.handleError(WorkshopActivity.this, anError.getErrorBody() , anError.getErrorCode());

                    }
                });
    }

    public void onClick(){

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nums.size()==1) {

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +PhoneNum));
                    startActivity(intent);
                }

                else {

                    CustomDialogClass cdd=new CustomDialogClass(WorkshopActivity.this,nums);
                    cdd.show();
                }
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Map));
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}





