package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Context;
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

        int[] mResources = {
                R.drawable.welcome1,
                R.drawable.mainmenu1,
                R.drawable.camera1,
                R.drawable.locationlist1,
                R.drawable.map1,
                R.drawable.map2,
                R.drawable.map3,
                R.drawable.direstions1,
                R.drawable.poster1,
                R.drawable.camera2,
                R.drawable.locationlist2,
                R.drawable.locationdetails,
                R.drawable.closingslide,
                R.drawable.lastslide
        };

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {

            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }