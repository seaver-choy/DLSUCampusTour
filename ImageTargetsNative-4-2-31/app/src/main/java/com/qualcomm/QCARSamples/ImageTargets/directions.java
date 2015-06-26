package com.qualcomm.QCARSamples.ImageTargets;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class directions extends ActionBarActivity {

    private RecyclerView recyclerView;
    private directions_stepsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.locations_recyclerview);
        adapter = new directions_stepsAdapter(this,getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<step> getData(){
        List<step> stepList = new ArrayList<>();

        step steps = new step("dfdsf","asdasdasdasd",1);
        stepList.add(steps);
        steps = new step("dfdsf","asdasdasdasd",1);
        stepList.add(steps);
        steps = new step("dfdsf","asdasdasdasd",1);
        stepList.add(steps);
        steps = new step("dfdsf","asdasdasdasd",1);
        stepList.add(steps);
        steps = new step("dfdsf","asdasdasdasd",1);
        stepList.add(steps);
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
