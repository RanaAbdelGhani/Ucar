package com.elephant.ucar.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.elephant.ucar.Models.ResponseParts;
import com.elephant.ucar.R;

import java.text.DecimalFormat;
import java.util.List;

public class PeriodicListViewAdapter extends ArrayAdapter {
    List<ResponseParts.DataItem> Data;
    private DecimalFormat formatter = new DecimalFormat("#,###,###");


    private Context mcontext;

    public PeriodicListViewAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.Data = objects;

        mcontext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View v = inflater.inflate(R.layout.maintenance, parent, false);
        TextView PartName = v.findViewById(R.id.PName);
        TextView PartBrice = v.findViewById(R.id.PPrice);


        PartName.setText(Data.get(position).getTitle() + " Km");
        PartBrice.setText(formatter.format(Data.get(position).getPrice()) + " EGP");


        return v;
    }


}
