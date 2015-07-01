package com.qualcomm.QCARSamples.ImageTargets.model;

/**
 * Created by admin on 7/1/2015.
 */
public class Building extends Location {

    private String map_image;

    public Building (int loc_id, String name, String description, String icon_name, boolean hasVisited, String map_image)
    {
        super(loc_id, name, description, icon_name, hasVisited);
        this.map_image = map_image;
    }

    public Building (String name, String description, String icon_name, boolean hasVisited, String map_image)
    {
        super(name, description, icon_name, hasVisited);
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
}
