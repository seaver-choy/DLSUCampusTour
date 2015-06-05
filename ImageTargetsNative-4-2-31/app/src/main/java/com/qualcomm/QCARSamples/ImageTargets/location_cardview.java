package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.qualcomm.QCARSamples.ImageTargets.model.DatabaseHelper;
import com.qualcomm.QCARSamples.ImageTargets.model.Location;

import java.util.List;


public class location_cardview extends Activity {
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
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<Location> getIconData(){

        for(int i = 0; i<locations.size();i++){
            Context context = recyclerView.getContext();
            int icon = context.getResources().getIdentifier(locations.get(i).getIconName(), "drawable", context.getPackageName());
            locations.get(i).setIcon(icon);
            //current.name = locations[i];
            //current.description = descriptions[i];
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
}
