package com.elephant.ucar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.elephant.ucar.R;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.List;

public class CarDetailsSliderAdapterZooming extends PagerAdapter {

    private final Context context;
    private final List<String> images;

    public CarDetailsSliderAdapterZooming(Context context, List<String> images) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.zooming_slider_item, null);

        PhotoView imageView = (PhotoView) view.findViewById(R.id.imageViewCarDetailsZooming);

        Glide.with(context).load(images.get(position)).into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}