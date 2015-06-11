package com.qualcomm.QCARSamples.ImageTargets.model;

/**
 * Created by admin on 6/11/2015.
 */
public class Image {
    private String image_name;
    private int loc_id;
    private int image_id;
    private int image;

    public Image(int loc_id, String image_name) {
        this.loc_id = loc_id;
        this.image_name = image_name;
    }


    public Image(int image_id, String image_name, int loc_id) {
        this.image_id = image_id;
        this.image_name = image_name;
        this.loc_id = loc_id;
    }

    //Setters
    public void setImageId(int image_id) {
        this.image_id = image_id;
    }

    public void setImageName(String image_name) {
        this.image_name = image_name;
    }

    public void setLocId(int loc_id) {
        this.loc_id = loc_id;
    }

    public void setImage(int image) {
        this.image = image;
    }

    //Getters
    public int getImageId() {
        return image_id;
    }

    public String getImageName() {
        return image_name;
    }

    public int getLocId() {
        return loc_id;
    }

    public int getImage() {
        return image;
    }
}
