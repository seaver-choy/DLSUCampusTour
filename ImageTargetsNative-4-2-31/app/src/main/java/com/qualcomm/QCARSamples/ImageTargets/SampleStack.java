package com.qualcomm.QCARSamples.ImageTargets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.qualcomm.QCARSamples.ImageTargets.R;

import java.util.ArrayList;

public class SampleStack extends Activity {
    StackView sv;
    @SuppressLint("NewApi")
    @Override

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_stack);
        StackView stk = (StackView)this.findViewById(R.id.stackView);
        ArrayList<StackItem> items = new ArrayList<StackItem>();
        items.add(new StackItem("text1", this.getResources().getDrawable(R.drawable.menulocations )));
        items.add(new StackItem("text2", this.getResources().getDrawable(R.drawable.menuhelp)));
        items.add(new StackItem("text3", this.getResources().getDrawable(R.drawable.menulocations)));
        items.add(new StackItem("text4", this.getResources().getDrawable(R.drawable.menuhelp)));
        items.add(new StackItem("text5", this.getResources().getDrawable(R.drawable.menulocations)));
        items.add(new StackItem("text6", this.getResources().getDrawable(R.drawable.menuhelp)));
        items.add(new StackItem("text7", this.getResources().getDrawable(R.drawable.menulocations)));
        items.add(new StackItem("text8", this.getResources().getDrawable(R.drawable.menuhelp)));
        items.add(new StackItem("text9", this.getResources().getDrawable(R.drawable.menulocations)));
        AdapterStack adapt = new AdapterStack(this, R.layout.activity_sample_stack, items);
        stk.setAdapter(adapt);
    }
    public class StackItem {
        public String itemText;
        public Drawable itemPhoto;
        public StackItem(String text,Drawable photo)
        {
            this.itemPhoto = photo;
            this.itemText = text;
        }
    }
    public class AdapterStack extends ArrayAdapter<StackItem> {
        private ArrayList<StackItem> items;
        private Context ctx;
        public AdapterStack(Context context, int textViewResourceId,
                            ArrayList<StackItem> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
            this.ctx = context;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.item, null);
            }
            StackItem m = items.get(position);
            if (m != null) {
                TextView text = (TextView) v.findViewById(R.id.textview);
                ImageView img = (ImageView)v.findViewById(R.id.imagteview);
                if (text != null) {
                    text.setText(m.itemText);
                    img.setImageDrawable(m.itemPhoto);
                }
            }
            return v;
        }
    }
}

