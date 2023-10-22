package com.elephant.ucar.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.elephant.ucar.MyUtils.CustomDialogClass;
import com.elephant.ucar.R;
import java.util.ArrayList;
import java.util.List;

public class ContactUs extends AppCompatActivity {

    TextView mail,num,share,face,insta;
    List<String> Nums=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        mail=findViewById(R.id.ContactMail);
        num=findViewById(R.id.ContactNum);
        share=findViewById(R.id.ContactShare);
        face=findViewById(R.id.ContactFacebook);
        insta=findViewById(R.id.ContactInstagram);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Try Ucar App and take care about your car..! \n Google Play: shorturl.at/kCIMR");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Try Ucar Now..!");
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ucaregypt@gmail.com"});
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nums.clear();
                Nums.add("+201098955428");
                Nums.add("+201014451446");
                CustomDialogClass cdd=new CustomDialogClass(ContactUs.this,Nums);
                cdd.show();
            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UCAR-104564728578301/"));
                startActivity(browserIntent);
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/ucar.eg/"));
                startActivity(browserIntent);
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}