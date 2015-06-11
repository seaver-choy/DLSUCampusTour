package com.qualcomm.QCARSamples.ImageTargets.model;

/**
 * Created by admin on 5/29/2015.
 */

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.FileReader;
import java.io. BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Target {

    private int loc_id;
    private String target_name;
    private String image_name;
    private int target_id;

    public Target()
    {
    }

    public Target(int loc_id, String target_name, String image_name, int target_id) {
        this.loc_id = loc_id;
        this.target_name = target_name;
        this.image_name = image_name;
        this.target_id = target_id;
    }

    public Target(int loc_id, String target_name, String image_name) {
        this.loc_id = loc_id;
        this.target_name = target_name;
        this.image_name = image_name;
    }

    public Target(String target_name, String image_name) {
        this.target_name = target_name;
        this.image_name = image_name;
    }

    //Setters
    public void setLocID(int loc_id) {
        this.loc_id = loc_id;
    }

    public void setImageName(String image_name) {
        this.image_name = image_name;
    }

    public void setTargetName(String target_name) {
        this.target_name = target_name;
    }

    public void setTargetID(int target_id) {
        this.target_id = target_id;
    }

    //Getters
    public int getLocID() {
        return loc_id;

    }

    public String getImageName() {
        return image_name;
    }

    public String getTargetName() {
        return target_name;
    }

    public int getTargetID() {
        return target_id;
    }
}
