package com.elephant.ucar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.elephant.ucar.Models.ResponseWorkShop;
import com.elephant.ucar.R;

import java.util.List;

public class DiscountListViewAdapter extends ArrayAdapter {
    List<ResponseWorkShop.DataItem> items;
    Context mcontext;

    public DiscountListViewAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.items = objects;
        mcontext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.discount_item, parent, false);

        ImageView placeImage = v.findViewById(R.id.WorkshopImage);
        TextView placeName = v.findViewById(R.id.WorkshopName);
        TextView DiscountText = v.findViewById(R.id.DiscountText);

        placeName.setText(items.get(position).getTitle());
        DiscountText.setText(items.get(position).getDis_offer());
        Glide.with(mcontext).load(items.get(position).getAvatar()).into(placeImage);


        return v;
    }
}