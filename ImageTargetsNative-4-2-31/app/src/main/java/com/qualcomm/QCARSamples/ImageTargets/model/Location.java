package com.qualcomm.QCARSamples.ImageTargets.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Interns on 5/29/2015.
 */
public class Location {
    private String name;
    private String description;
    private String shortDescription;
    private String icon_name;
    private int loc_id;
    private boolean hasVisited;
    private int icon;
    private String[] imageNames = null;
    private Integer[] imageIcons = null;

    public Location(int loc_id, String name, String description, String icon_name, boolean hasVisited, String shortDescription)
    {
        this.loc_id = loc_id;
        this.name = name;
        this.description = description;
        this.icon_name = icon_name;
        this.hasVisited = hasVisited;
        this.shortDescription = shortDescription;
    }

    public Location(String name, String description, String icon_name, boolean hasVisited, String shortDescription)
    {
        this.name = name;
        this.description = description;
        this.icon_name = icon_name;
        this.hasVisited = hasVisited;
        this.shortDescription = shortDescription;
    }

    public Location()
    {
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIconName(String icon_name) {
        this.icon_name = icon_name;
    }

    public void setLocId(int loc_id) {
        this.loc_id = loc_id;
    }

    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public void setImageNames(String[] imageNames) {
        this.imageNames = imageNames;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setImageIcons(Integer[] imageIcons) {
        this.imageIcons = imageIcons;
    }

    public void setImageIcons(Context context) {
        String[] imageNames = this.getImageNames();
        ArrayList<Integer> imageIconsList = new ArrayList<Integer>();
        for(int x = 0; x < imageNames.length;x++){
            int imageIcon = context.getResources().getIdentifier(imageNames[x], "drawable", context.getPackageName());
            imageIconsList.add(imageIcon);
        }

        this.setImageIcons(imageIconsList.toArray(new Integer[imageIconsList.size()]));
    }

    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconName() {
        return icon_name;
    }

    public int getIcon() {
        return icon;
    }

    public int getLocId() {
        return loc_id;
    }

    public boolean isHasVisited() {
        return hasVisited;
    }

    public String[] getImageNames() {
        return imageNames;
    }

    public Integer[] getImageIcons() {
        return imageIcons;
    }

    public String getShortDescription()
    {
        return this.shortDescription;
    }
    @Override
    public boolean equals(Object object)
    {
        Location obj = (Location) object;
        if(obj == null){
            return false;
        }else if(obj.getDescription() != this.getDescription()
                && obj.getIconName() != this.getIconName()
                && obj.getLocId() != this.getLocId()
                && obj.getName() != this.getName())
        {
            return false;
        }
        //Log.e("OBJ", obj.getDescription() + " " + obj.getIconName() + " " + obj.getLocId() + " " + obj.getName());
        //Log.e("THIS", this.getDescription() + " " + this.getIconName() + " " + this.getLocId() + " " + this.getName());
        return true;
    }
}
