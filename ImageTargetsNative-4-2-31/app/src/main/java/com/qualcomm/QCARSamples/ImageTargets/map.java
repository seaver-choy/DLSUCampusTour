package com.qualcomm.QCARSamples.ImageTargets;

/**
 * Created by Interns on 6/18/2015.
 */
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class map extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.pink_trees);
        img.setMaxZoom(4f);
        setContentView(img);
    }
}