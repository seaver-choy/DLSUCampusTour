package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TutorialScreen extends Activity {
    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private Intent i;
    Handler handler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_screen);
        mCustomPagerAdapter = new CustomPagerAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        handler = new Handler();

       mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               Log.i("pager", mViewPager.getCurrentItem() + "");

               if(mViewPager.getCurrentItem() == mCustomPagerAdapter.getCount() - 1)
               {
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           i = new Intent(getBaseContext(), ActivitySplashScreen.class);
                           finish();
                           startActivity(i);
                       }
                   }, 500);


               }

           }

           @Override
           public void onPageSelected(int position) {

           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {
        //do whatever you want the 'Back' button to do

        this.startActivity(new Intent(this, ActivitySplashScreen.class));
        finish();

        return;
    }
}
