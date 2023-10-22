package com.elephant.ucar.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.elephant.ucar.Fragments.CarDetailsFragment;
import com.elephant.ucar.Fragments.CarPartsFragment;
import com.elephant.ucar.R;

public class CarActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentTransaction transaction;
    Button Details,Parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Definition();
        startFragment();

    }

    private void Definition(){
        Details=findViewById(R.id.details);
        Parts=findViewById(R.id.parts);
    }

    private void startFragment(){
        fragment = new CarDetailsFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.CarFragmentLayout, fragment, "MainFragment");
        transaction.commitNow();

    }

    public void DetailsButton(View view) {

        Details.setBackgroundResource(R.drawable.button_design);
        Details.setEnabled(false);

        Parts.setBackgroundResource(R.color.transparent);
        Parts.setEnabled(true);

        fragment = new CarDetailsFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.CarFragmentLayout, fragment, "CarDetailsFragment");
        transaction.commitNow();


    }

    public void PartsButton(View view){

        Details.setBackgroundResource(R.color.transparent);
        Details.setEnabled(true);

        Parts.setBackgroundResource(R.drawable.button_design);
        Parts.setEnabled(false);

        fragment = new CarPartsFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.CarFragmentLayout, fragment, "CarPartsFragment");
        transaction.commitNow();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}