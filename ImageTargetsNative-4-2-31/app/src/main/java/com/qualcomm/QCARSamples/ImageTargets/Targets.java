package com.qualcomm.QCARSamples.ImageTargets;

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

public class Targets {

    private ArrayList<String[]> listOfElements = new ArrayList();

    //converts the input stream given from assetmanager to listOfElements
    public Targets( InputStream is )
    {
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader textReader = new BufferedReader(isr);
            String line;
            String[] elements;

            while ((line = textReader.readLine()) != null){
                elements = line.split(" ");
                listOfElements.add(elements);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //returns the entire list
    public ArrayList<String[]> getTargetListElements(){
        return this.listOfElements;
    }
    //returns size of list
    public int getTargetListSize(){
        return this.listOfElements.size();
    }
    //gets all the targetImages
    public String[] getAllTargetImages()
    {
        ArrayList<String> temp = new ArrayList();
        for(int i = 0; i < listOfElements.size(); i++)
        {
            temp.add(listOfElements.get(i)[1]);
        }

        String[] tempArray = new String[temp.size()];
        tempArray = temp.toArray(tempArray);
        return tempArray;
    }

    //gets all the targetNames
    public String[] getAllTargetNames()
    {
        ArrayList<String> temp = new ArrayList();
        for(int i = 0; i < listOfElements.size(); i++)
        {
            temp.add(listOfElements.get(i)[0]);
        }

        String[] tempArray = new String[temp.size()];
        tempArray = temp.toArray(tempArray);
        return tempArray;
    }
}
