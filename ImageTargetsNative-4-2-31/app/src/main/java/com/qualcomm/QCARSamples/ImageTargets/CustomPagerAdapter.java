package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Goldwin on 9/24/2015.
 */

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;
        View itemView;
        ImageView imageView;
        TypedArray mResources;
        int count;
        int[] ids;

    public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mResources = mContext.getResources().obtainTypedArray(R.array.tutorial_images);
            count = mResources.length();
            ids = new int[count];
            for(int i=0;i<ids.length;i++) {
                ids[i] = mResources.getResourceId(i, 0);
            }
        }

        @Override
        public int getCount() {
            return mResources.length();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(ids[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
