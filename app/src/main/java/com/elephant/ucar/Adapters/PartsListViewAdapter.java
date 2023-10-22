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
import com.elephant.ucar.Models.ResponseParts;
import com.elephant.ucar.R;
import java.text.DecimalFormat;
import java.util.List;

public class PartsListViewAdapter extends ArrayAdapter {
    List<ResponseParts.DataItem> Data;
    private DecimalFormat formatter = new DecimalFormat("#,###,###");

    private Context mcontext;

    public PartsListViewAdapter(@NonNull Context context, int resource , @NonNull List objects) {
        super(context, resource, objects);
        this.Data = objects ;
        mcontext=context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View v=inflater.inflate(R.layout.parts_item,parent,false);
        TextView PartName=v.findViewById(R.id.PartName);
        TextView PartBrice=v.findViewById(R.id.PartBrice);
        ImageView PartImage=v.findViewById(R.id.PartPic);

        Glide.with(mcontext).load(Data.get(position).getAvatar()).into(PartImage);
        PartName.setText(Data.get(position).getTitle());
        PartBrice.setText(formatter.format(Data.get(position).getPrice()) + "  EGP");


        return v;
    }

}
