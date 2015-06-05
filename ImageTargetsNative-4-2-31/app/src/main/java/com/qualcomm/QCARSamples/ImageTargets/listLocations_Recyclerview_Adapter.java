package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qualcomm.QCARSamples.ImageTargets.model.Location;

import java.util.Collections;
import java.util.List;


/**
 * Created by Interns on 5/29/2015.
 */
public class listLocations_Recyclerview_Adapter extends RecyclerView.Adapter<listLocations_Recyclerview_Adapter.myViewHolder> {
    private LayoutInflater inflater;
    List<Location> locationData = Collections.emptyList();
    Context context;
    ClickListener clickListener;

    public listLocations_Recyclerview_Adapter(Context context, List<Location> locationData) {
        inflater = LayoutInflater.from(context);
        this.locationData = locationData;
        this.context = context;
//        Resources res = context.getResources();
//        locations = res.getStringArray(R.array.locations);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.test_layout, parent, false);

        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Location currentLoc = locationData.get(position);
        holder.title.setText(currentLoc.getName());
        holder.icon.setImageResource(currentLoc.getIcon());
        holder.description.setText(currentLoc.getDescription());
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
        TextView description;

        public myViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.location_imageview);
            description = (TextView) itemView.findViewById(R.id.description);

        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, ViewDetails.class));

            if(clickListener!=null){
                clickListener.itemClicked(v,getPosition());
            }

        }
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }
}
