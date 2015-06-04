package com.qualcomm.QCARSamples.ImageTargets;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class location_cardview extends Activity {
    private RecyclerView recyclerView;
    private listLocations_Recyclerview_Adapter adapter;
    String[]locations;
    String[]descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        locations = getResources().getStringArray(R.array.locations);
        descriptions = getResources().getStringArray(R.array.descriptions);
        recyclerView = (RecyclerView) findViewById(R.id.locations_recyclerview);
        adapter = new listLocations_Recyclerview_Adapter(this,getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<location> getData(){
        List<location> data = new ArrayList<>();
        int icon = R.drawable.pink_trees;
        String desc = "Description amputa";

        for(int i = 0; i<locations.length;i++){
            location current = new location();
            current.name = locations[i];
            current.icon = icon;
            current.description = descriptions[i];

            data.add(current);
        }

        return data;
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
