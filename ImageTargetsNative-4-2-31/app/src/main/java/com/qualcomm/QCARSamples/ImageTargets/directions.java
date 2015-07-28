package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.qualcomm.QCARSamples.ImageTargets.model.DatabaseHelper;
import com.qualcomm.QCARSamples.ImageTargets.model.Location;
import com.qualcomm.QCARSamples.ImageTargets.model.Step;

import java.util.ArrayList;
import java.util.List;


public class directions extends ActionBarActivity {

    private RecyclerView recyclerView;
    private directions_stepsAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        databaseHelper = DatabaseHelper.getInstance(this);
        recyclerView = (RecyclerView) findViewById(R.id.locations_recyclerview);
        adapter = new directions_stepsAdapter(this,getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Step> getData(){
        List<Step> stepList;
        stepList = databaseHelper.getAllStepsOfLocation(getIntent().getIntExtra("location_id", -999));
        for(int i = 0; i < stepList.size();i++){
            Context context = recyclerView.getContext();
            int icon = context.getResources().getIdentifier(stepList.get(i).getPictureName(), "drawable", context.getPackageName());
            Log.e("Tag", icon + " " + stepList.get(i).getPictureName());
            stepList.get(i).setPicture(icon);
        }
        return stepList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_directions, menu);
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
}
