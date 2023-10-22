package com.elephant.ucar.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.elephant.ucar.Fragments.CarsFragment;
import com.elephant.ucar.Fragments.FixFragment;
import com.elephant.ucar.R;

public class CompanyDetailsActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentTransaction transaction;
    Button cars,fix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        startFragment();
        Definition();
    }

    private void Definition(){
        cars=findViewById(R.id.cars);
        fix=findViewById(R.id.fix);
    }

    private void startFragment(){
        fragment = new CarsFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentLayout, fragment, "MainFragment");
        transaction.commitNow();

    }

    public void carsButton(View view) {

        cars.setBackgroundResource(R.drawable.button_design);
        cars.setEnabled(false);

        fix.setBackgroundResource(R.color.transparent);
        fix.setEnabled(true);

        fragment = new CarsFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentLayout, fragment, "CarsFragment");
        transaction.commitNow();

    }

    public void FixButton(View view){
        cars.setBackgroundResource(R.color.transparent);
        cars.setEnabled(true);

        fix.setBackgroundResource(R.drawable.button_design);
        fix.setEnabled(false);

        CarsFragment.expandableListTitle.clear();
        CarsFragment.expandableListImage.clear();
        CarsFragment.expandableListPrice.clear();
        CarsFragment.expandableListDetail.clear();

        fragment = new FixFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentLayout, fragment, "FixFragment");
        transaction.commitNow();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}