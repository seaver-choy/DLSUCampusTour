/*===============================================================================
Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of QUALCOMM Incorporated, registered in the United States 
and other countries. Trademarks of QUALCOMM Incorporated are used with permission.
===============================================================================*/

package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.qualcomm.QCARSamples.ImageTargets.model.DatabaseHelper;
import com.qualcomm.QCARSamples.ImageTargets.model.Target;


public class ActivitySplashScreen extends Activity
{
    
    private static long SPLASH_MILLIS = 4000;
    private ImageButton mstart;
    private ImageButton mlocations;
    private ImageButton mhelp;
    Intent i;
    private DatabaseHelper databaseHelper;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(
            R.layout.splash_screen, null, false);
        
        addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT));

        mstart = (ImageButton) findViewById(R.id.mstartbutton);
        mlocations = (ImageButton) findViewById(R.id.mlocationsbutton);
        mhelp = (ImageButton) findViewById(R.id.mhelpbutton);

        databaseHelper = DatabaseHelper.getInstance(this.getApplicationContext());
        databaseHelper.initializeDatabase();

        mstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(ActivitySplashScreen.this, ImageTargets.class);

                startActivity(i);

            }
        });

        mhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(ActivitySplashScreen.this, ViewDetails.class);

                startActivity(i);
            }
        });



//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable()
//        {
//
//            @Override
//            public void run()
//            {
//
//                Intent intent = new Intent(ActivitySplashScreen.this,
//                   MainMenu.class);
////                intent.putExtra("ACTIVITY_TO_LAUNCH", "ImageTargets");
////                intent.putExtra("ABOUT_TEXT_TITLE", "Image Targets");
////                intent.putExtra("ABOUT_TEXT", "IT_about.html");
//                startActivity(intent);
//
//            }
//
//        }, SPLASH_MILLIS);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
