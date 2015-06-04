package com.qualcomm.QCARSamples.ImageTargets;

import android.graphics.drawable.Drawable;

/**
 * Created by admin on 6/3/2015.
 */
public class StackItem {

    public String text;
    public Drawable img;

    public StackItem(String text,Drawable photo)
    {
        this.img = photo;
        this.text = text;
    }
}
