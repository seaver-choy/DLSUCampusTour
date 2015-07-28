package com.qualcomm.QCARSamples.ImageTargets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qualcomm.QCARSamples.ImageTargets.model.Step;

import java.util.Collections;
import java.util.List;

/**
 * Created by Interns on 6/10/2015.
 */
public class directions_stepsAdapter extends RecyclerView.Adapter<directions_stepsAdapter.directionsVH>{
    private LayoutInflater inflater;
    List<Step> directionsData = Collections.emptyList();

    public  directions_stepsAdapter(Context context,List<Step> directionsData){
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
        Step currentStep = directionsData.get(position);
        Log.e("TAG", currentStep.getStepNum()+ "");
        Log.e("TAG", currentStep.getStepDesc() + "");
        holder.stepNum.setText(currentStep.getTitle() + "");
        holder.stepDesc.setText(currentStep.getStepDesc());
        holder.picture.setImageResource(currentStep.getPicture());
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
            picture = (ImageView) itemView.findViewById(R.id.step_image);
        }
    }
}
