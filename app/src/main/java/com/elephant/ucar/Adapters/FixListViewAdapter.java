package com.elephant.ucar.Adapters;

import android.annotation.SuppressLint;
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

public class FixListViewAdapter extends ArrayAdapter {
    List<ResponseWorkShop.DataItem> items;
    Context mcontext;

    public FixListViewAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.items = objects;
        mcontext = context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fix_list_item, parent, false);

        ImageView placeImage = v.findViewById(R.id.placeImage);
        TextView placeName = v.findViewById(R.id.placeName);
        TextView Address = v.findViewById(R.id.Address);

        placeName.setText(items.get(position).getTitle());

        if (items.get(position).getAddress() == null)
            Address.setVisibility(View.GONE);
        else
            Address.setText(Address.getText() + items.get(position).getAddress());

        Glide.with(mcontext).load(items.get(position).getAvatar()).into(placeImage);


        return v;
    }
}
