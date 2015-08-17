package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewDetails extends ActionBarActivity{

    String s = null;
    String name = null;
    ArrayList<Integer> imageIcons = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        TextView description = (TextView) findViewById(R.id.location_description);
        TextView txtviewName = (TextView) findViewById(R.id.TVname);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            s = (String) extras.getString("description");
            name = (String) extras.getString("name");
            imageIcons = extras.getIntegerArrayList("imageIcons");
        }
        Log.e("TAG", s + " should be something before me");
        description.setText(s);
        txtviewName.setText(name);
        imageView4.setImageResource(imageIcons.get(0).intValue());
        imageView5.setImageResource(imageIcons.get(1).intValue());
        imageView6.setImageResource(imageIcons.get(2).intValue());

    }
}