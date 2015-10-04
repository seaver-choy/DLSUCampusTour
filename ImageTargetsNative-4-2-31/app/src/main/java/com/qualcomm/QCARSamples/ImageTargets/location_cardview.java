package com.qualcomm.QCARSamples.ImageTargets;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.qualcomm.QCARSamples.ImageTargets.model.DatabaseHelper;
import com.qualcomm.QCARSamples.ImageTargets.model.Location;

import java.util.ArrayList;
import java.util.List;


public class location_cardview extends ActionBarActivity implements listLocations_Recyclerview_Adapter.ClickListener{
    private RecyclerView recyclerView;
    private listLocations_Recyclerview_Adapter adapter;
    private DatabaseHelper databaseHelper;
    private List<Location> locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        databaseHelper = DatabaseHelper.getInstance(this.getApplicationContext());
        locations = databaseHelper.getAllLocations();

        recyclerView = (RecyclerView) findViewById(R.id.locations_recyclerview);
        adapter = new listLocations_Recyclerview_Adapter(this, getIconData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009f3c")));
        //bar.setTitle(Html.fromHtml("<font color='#ffffff'>Location List</font>"));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public List<Location> getIconData(){

        for(int i = 0; i<locations.size();i++){
            Context context = recyclerView.getContext();
            int icon = context.getResources().getIdentifier(locations.get(i).getIconName(), "drawable", context.getPackageName());
            locations.get(i).setIcon(icon);

            String[] imageNames = locations.get(i).getImageNames();
            ArrayList<Integer> imageIconsList = new ArrayList<Integer>();
            for(int x = 0; x < imageNames.length;x++){
                int imageIcon = context.getResources().getIdentifier(imageNames[x], "drawable", context.getPackageName());
                imageIconsList.add(imageIcon);
            }

            locations.get(i).setImageIcons(imageIconsList.toArray(new Integer[imageIconsList.size()]));
        }
        return locations;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
     //   getMenuInflater().inflate(R.menu.menu_location_cardview, menu);
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

    @Override
    public void itemClicked(View view, int position) {
        startActivity(new Intent(this, ViewDetails.class));
    }

    public void onBackPressed()
    {
        //do whatever you want the 'Back' button to do
        Intent i = getIntent();
        String previous = i.getStringExtra("FROM ACTIVITY");

            this.finish();
            this.startActivity(new Intent(this, ActivitySplashScreen.class));

            return;
    }
}
