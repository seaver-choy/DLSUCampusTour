package com.qualcomm.QCARSamples.ImageTargets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.qualcomm.QCARSamples.ImageTargets.model.Building;
import com.qualcomm.QCARSamples.ImageTargets.model.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Interns on 5/29/2015.
 */
public class ListLocationsRecyclerViewAdapter extends RecyclerView.Adapter<ListLocationsRecyclerViewAdapter.myViewHolder> {
    private LayoutInflater inflater;
    List<Location> locationData = Collections.emptyList();
    Context context;
    ClickListener clickListener;

    public ListLocationsRecyclerViewAdapter(Context context, List<Location> locationData) {
        inflater = LayoutInflater.from(context);
        this.locationData = locationData;
        this.context = context;
//        Resources res = context.getResources();
//        locations = res.getStringArray(R.array.locations);

        //warning.setDuration(Toast.LENGTH_LONG);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.test_layout, parent, false);

        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        final Location currentLoc = locationData.get(position);
        holder.title.setText(currentLoc.getName());
        holder.icon.setImageResource(currentLoc.getIcon());

        //LOOK AT THIS FOR THE LOCK BUTTON ITS PSEUDOCODE


        if(currentLoc.isHasVisited()){
            holder.btn_lock.setVisibility(View.INVISIBLE);
            Log.e("LOCKBUTTON",currentLoc.getName() + " is named unlocked");
        }else{
            holder.btn_lock.setVisibility(View.VISIBLE);
        }



        holder.btn_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(currentLoc.isHasVisited() == true){

                if(currentLoc instanceof Building)
                {
                    Building building = (Building) currentLoc;
                    Log.e("ImageName", building.getMapImage());
                    int icon = context.getResources().getIdentifier(building.getMapImage(), "drawable", context.getPackageName());
                    building.setMapIcon(icon);
                    Intent intent = new Intent(context, map.class);
                    intent.putExtra("mapIcon", building.getMapIcon());
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(context, directions.class);
                    Log.e("loc_id", currentLoc.getLocId() + "");
                    intent.putExtra("location_id", currentLoc.getLocId());
                    context.startActivity(intent);
                }
//                }

//                else
//                    Toast.makeText(context.getApplicationContext(),"LOCATION LOCKED", Toast.LENGTH_SHORT).show();


            }
        });
        //  holder.description.setText(currentLoc.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentLoc.isHasVisited() == true) {
                    Intent intent = new Intent(context, ViewDetails.class);
                    intent.putExtra("description", locationData.get(position).getDescription());
                    intent.putExtra("name", locationData.get(position).getName());

                    ArrayList<Integer> imageIconsList = new ArrayList<Integer>();
                    Integer[] imageIcons = locationData.get(position).getImageIcons();

                    for(int i = 0; i < imageIcons.length; i++)
                    {
                        imageIconsList.add(imageIcons[i]);
                    }

                    intent.putIntegerArrayListExtra("imageIcons", imageIconsList);

                    context.startActivity(intent);
                }

                else
                    showAlert();

            }
        });
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return locationData.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        ImageButton btn_loc;
        ImageView btn_lock;
        TextView description;

        public myViewHolder(View itemView) {
            super(itemView);
            ;
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.location_imageview);
            btn_loc = (ImageButton) itemView.findViewById(R.id.loc_button);
            btn_lock = (ImageView) itemView.findViewById(R.id.lockbutton);

            //  description = (TextView) itemView.findViewById(R.id.description);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, ViewDetails.class);
            intent.putExtra("description", "TANGINA MO");
            Log.e("TAG", "TANGINA MO");

            context.startActivity(intent);

            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }

        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }

    public void showAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context, R.style.layout_alert);

        // set title
        alertDialogBuilder.setTitle("LOCATION LOCKED");




        // set dialog message
        alertDialogBuilder
                .setMessage("Please press the get location button instead.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // show it
        alertDialog.show();

        int titleDividerId = context.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = alertDialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009f3c")));
    }


}