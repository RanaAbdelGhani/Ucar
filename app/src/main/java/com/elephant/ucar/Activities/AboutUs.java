package com.elephant.ucar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.elephant.ucar.R;

public class AboutUs extends AppCompatActivity {

    ImageView share;
    TextView contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abou_us);

        share=findViewById(R.id.AboutShare);
        contact=findViewById(R.id.ContactButton);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Try Ucar App and take care about your car..! \n shorturl.at/kCIMR");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Try Ucar Now..!");
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AboutUs.this,ContactUs.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}