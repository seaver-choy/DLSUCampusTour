/*===============================================================================
Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of QUALCOMM Incorporated, registered in the United States 
and other countries. Trademarks of QUALCOMM Incorporated are used with permission.
===============================================================================*/

package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.qualcomm.QCARSamples.ImageTargets.model.DatabaseHelper;


public class ActivitySplashScreen extends Activity
{
    private ImageButton mstart;
    private ImageButton mlocations;
    private ImageButton mhelp;
    private DatabaseHelper databaseHelper;
    private Intent i;


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


        boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun", true);

        if(firstRun) {
            databaseHelper = DatabaseHelper.getInstance(this.getApplicationContext());
            databaseHelper.initializeDatabaseFromXML();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE).
                    edit().
                    putBoolean("firstRun", false).
                    commit();


            i = new Intent(getBaseContext(), TutorialScreen.class);
            i.putExtra("FROM ACTIVITY", "mainmenu");
            startActivity(i);

            SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
            if(pref.getBoolean("activity_executed", false)){
                Intent intent = new Intent(this, TutorialScreen.class);
                startActivity(intent);

            } else {
                SharedPreferences.Editor ed = pref.edit();
                ed.putBoolean("activity_executed", true);
                ed.commit();
            }

        }

        mstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getBaseContext(), ImageTargets.class);
                i.putExtra("FROM ACTIVITY", "mainmenu");
                startActivity(i);

            }
        });

        mlocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getBaseContext(), LocationCardView.class);
                i.putExtra("FROM ACTIVITY", "mainmenu");
                startActivity(i);

            }
        });


        mhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getBaseContext(), TutorialScreen.class);
                i.putExtra("FROM ACTIVITY", "mainmenu");
                startActivity(i);
            }
        });



    }


}
