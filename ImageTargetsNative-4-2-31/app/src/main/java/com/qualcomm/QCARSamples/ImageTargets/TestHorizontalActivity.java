package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TestHorizontalActivity extends ActionBarActivity {
    String s = null;
    String name = null;
    ArrayList<Integer> imageIcons = null;
    private LinearLayout linearLayout;
    private CustomHorizontalScrollView horizontalScrollView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        horizontalScrollView = new CustomHorizontalScrollView(this, 3,
                width);
        setContentView(R.layout.horizontal);
        linearLayout = (LinearLayout) findViewById(R.id.layer);
        linearLayout.addView(horizontalScrollView);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009f3c")));

        TextView description = (TextView) findViewById(R.id.location_description);
        TextView txtviewName = (TextView) findViewById(R.id.TVname);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            s = (String) extras.getString("description");
            name = (String) extras.getString("name");
            imageIcons = extras.getIntegerArrayList("imageIcons");
        }
        Log.e("TAG", s + " should be something before me");
        description.setText(s);
        txtviewName.setText(name);


        LinearLayout container = new LinearLayout(this);
        container.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        // container.setHeight(height);

        ImageView textView = new ImageView(this);
        textView.setImageResource(imageIcons.get(0).intValue());
        container.addView(textView);

        ImageView textView1 = new ImageView(this);

        textView1.setImageResource(imageIcons.get(1).intValue());
        container.addView(textView1);

        ImageView textView2 = new ImageView(this);

        textView2.setImageResource(imageIcons.get(2).intValue());
        container.addView(textView2);

        horizontalScrollView.addView(container);
    }

}
