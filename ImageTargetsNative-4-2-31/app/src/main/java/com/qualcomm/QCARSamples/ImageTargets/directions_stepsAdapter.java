package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Interns on 6/10/2015.
 */
public class directions_stepsAdapter extends RecyclerView.Adapter<directions_stepsAdapter.directionsVH>{
    private LayoutInflater inflater;
    List<step> directionsData = Collections.emptyList();

    public  directions_stepsAdapter(Context context,List<step> directionsData){
        inflater = LayoutInflater.from(context);
        this.directionsData = directionsData;
    }
    @Override
    public directionsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.location_directions,parent,false);
        directionsVH holder = new directionsVH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(directionsVH holder, int position) {
        step currentStep = directionsData.get(position);
        holder.stepNum.setText(currentStep.stepNum);
        holder.stepDesc.setText(currentStep.stepDesc);
    }



    @Override
    public int getItemCount() {
        return directionsData.size();
    }

    class directionsVH extends RecyclerView.ViewHolder{
        TextView stepNum;
        TextView stepDesc;
        ImageView picture;

        public directionsVH(View itemView) {
            super(itemView);

            stepNum = (TextView) itemView.findViewById(R.id.step_number);
            stepDesc = (TextView) itemView.findViewById(R.id.step_description);
          //  picture = (ImageView) itemView.findViewById(R.id.step_image);

        }
    }
}
