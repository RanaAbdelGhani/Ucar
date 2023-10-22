package com.elephant.ucar.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elephant.ucar.Activities.CompanyDetailsActivity;
import com.elephant.ucar.Activities.MainActivity;
import com.elephant.ucar.Models.ResponseCompany;
import com.elephant.ucar.MyUtils.MyUtils;
import com.elephant.ucar.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ExampleViewHolder> {
    private List<ResponseCompany.DataItemCompany> mExampleList;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {

        void intent_to_detales(int pos, ImageView view);

    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        private ImageView image;


        public ExampleViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);

            image = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_name);


            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyUtils.CompanyID = mExampleList.get(getAdapterPosition()).getId() + "";
                    Intent i = new Intent(itemView.getContext(), CompanyDetailsActivity.class);
                    itemView.getContext().startActivity(i);
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.finish();
                }
            });
        }
    }

    public RecyclerViewAdapter(Context applicationContext, List<ResponseCompany.DataItemCompany> exampleList) {
        mExampleList = exampleList;
        context = applicationContext;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_resycler_listview_home, viewGroup, false);

        return new ExampleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder exampleViewHolder, final int position) {

        Glide.with(context).load(mExampleList.get(position).getAvatar()).into(exampleViewHolder.image);
        exampleViewHolder.title.setText(mExampleList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


}
