package com.qualcomm.QCARSamples.ImageTargets.model;

import java.util.List;

/**
 * Created by Interns on 6/10/2015.
 */
public class Step {

    private int stepNum;
    private String stepDesc;
    private int picture;
    private String title;
    private int loc_id;
    private String pictureName;


    public Step (int loc_id, int stepNum, String stepDesc,String pictureName, String title){
        this.stepNum = stepNum;
        this.stepDesc = stepDesc;
        this.pictureName = pictureName;
        this.title = title;
        this.loc_id = loc_id;
    }

    public Step()
    {

    }
    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLocId() {
        return loc_id;
    }

    public void setLocId(int loc_id) {
        this.loc_id = loc_id;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
