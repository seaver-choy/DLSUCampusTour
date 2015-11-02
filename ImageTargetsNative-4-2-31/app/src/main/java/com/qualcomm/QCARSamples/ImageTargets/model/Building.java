package com.qualcomm.QCARSamples.ImageTargets.model;

/**
 * Created by admin on 7/1/2015.
 */
public class Building extends Location {

    private String map_image;
    private int map_icon;

    public Building (int loc_id, String name, String description, String icon_name, boolean hasVisited, String map_image, String shortDescription)
    {
        super(loc_id, name, description, icon_name, hasVisited, shortDescription);
        this.map_image = map_image;
    }

    public Building (String name, String description, String icon_name, boolean hasVisited, String map_image, String shortDescription)
    {
        super(name, description, icon_name, hasVisited, shortDescription);
        this.map_image = map_image;
    }

    public Building()
    {

    }

    public String getMapImage() {
        return map_image;
    }

    public void setMapImage(String map_image) {
        this.map_image = map_image;
    }

    public int getMapIcon() {
        return map_icon;
    }

    public void setMapIcon(int map_icon) {
        this.map_icon = map_icon;
    }
}
