package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


/**
 * Created by Interns on 5/29/2015.
 */
public class listLocations_Recyclerview_Adapter extends RecyclerView.Adapter<listLocations_Recyclerview_Adapter.myViewHolder> {
    private LayoutInflater inflater;
    List<location> locationData = Collections.emptyList();

    public listLocations_Recyclerview_Adapter(Context context, List<location> locationData) {
        inflater = LayoutInflater.from(context);
        this.locationData = locationData;

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
        location currentLoc = locationData.get(position);
        holder.title.setText(currentLoc.name);
        holder.icon.setImageResource(currentLoc.icon);
        holder.description.setText(currentLoc.description);
    }

    @Override
    public int getItemCount() {
        return locationData.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        TextView description;

        public myViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.location_imageview);
            description = (TextView) itemView.findViewById(R.id.description);

        }
    }
}
