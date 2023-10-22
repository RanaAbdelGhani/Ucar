package com.elephant.ucar.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.elephant.ucar.R;

import java.util.List;

public class DetailsListViewAdapter extends ArrayAdapter {
    List<String> items;
    private Context mcontext;

    public DetailsListViewAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.items = objects;
        mcontext = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View v = inflater.inflate(R.layout.details_item, parent, false);
        TextView PartName = v.findViewById(R.id.DetailsFit);

        PartName.setText(items.get(position));


        return v;
    }

}