package com.qualcomm.QCARSamples.ImageTargets;

/**
 * Created by Interns on 6/18/2015.
 */
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class map extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009f3c")));



        TouchImageView img = new TouchImageView(this);
        int icon = getIntent().getIntExtra("mapIcon", 0);
        if(icon != 0)
        {
            img.setImageResource(icon);
            img.setMaxZoom(4f);
        }
        setContentView(img);
    }
}