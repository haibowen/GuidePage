package com.ekwing.mycustomguidedemo.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class PhoneViewPagerAdapter extends PagerAdapter {

    public PhoneViewPagerAdapter(List<View> mDataView) {
        this.mDataView = mDataView;
    }

    private List<View> mDataView;


    @Override
    public int getCount() {
        return mDataView.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object==view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        container.addView(mDataView.get(position));

        return mDataView.get(position);
    }
}
