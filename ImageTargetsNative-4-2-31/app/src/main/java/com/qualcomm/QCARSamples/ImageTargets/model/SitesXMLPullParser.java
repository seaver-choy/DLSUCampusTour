package com.qualcomm.QCARSamples.ImageTargets.model;

import android.content.Context;
import android.content.res.AssetManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by admin on 6/11/2015.
 */
public class SitesXMLPullParser {

    //Location keys
    private static final String KEY_LOCATION = "Location";
    private static final String KEY_LOCATION_ID = "location_id";
    private static final String KEY_LOCATION_NAME = "location_name";
    private static final String KEY_LOCATION_DESCRIPTION = "location_description";
    private static final String KEY_LOCATION_ICON_NAME = "location_icon_name";
    private static final String KEY_LOCATION_HASVISITED = "location_hasVisited";

    //Building keys
    private static final String KEY_BUILDING = "Building";
    private static final String KEY_BUILDING_MAP_IMAGE = "building_image";

    //Target keys
    private static final String KEY_TARGET = "Target";
    private static final String KEY_TARGET_ID = "target_id";
    private static final String KEY_TARGET_NAME = "target_name";
    private static final String KEY_TARGET_IMAGE_NAME = "target_image_name";
    private static final String KEY_TARGET_LOCATION_ID = "target_location_id";

    //Step keys
    private static final String KEY_STEP = "Step";
    private static final String KEY_STEP_NUMBER = "step_number";
    private static final String KEY_STEP_DESCRIPTION = "step_description";
    private static final String KEY_STEP_PICTURE_NAME = "step_picture_name";
    private static final String KEY_STEP_TITLE = "step_title";

    public static Location[] getLocations(Context ctxt) {
        XmlPullParserFactory factory;
        ArrayList<Location> locations = new ArrayList<>();
        Location currLocation = null;
        String currText = "";
        try {

            //Get our factory and pull parser
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            AssetManager assetManager = ctxt.getAssets();
            InputStream fis = assetManager.open("Locations.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            //point parser to our file
            xpp.setInput(reader);

            //get initial eventType
            int eventType = xpp.getEventType();

            //loop until end of event
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_LOCATION)) {
                            // If we are starting a new <Location> block we need
                            //a new StackSite object to represent it
                            currLocation = new Location();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        currText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_LOCATION)) {
                            // if </Location> then we are done with current Location
                            // add it to the list.
                            locations.add(currLocation);
                        } else if(tagname.equalsIgnoreCase(KEY_LOCATION_ID)){
                            currLocation.setLocId(Integer.parseInt(currText));
                        }
                        else if (tagname.equalsIgnoreCase(KEY_LOCATION_NAME)) {
                            // if </location_name> use setName() on currLocation
                            currLocation.setName(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION_DESCRIPTION)) {
                            // if </location_description> use setDescription() on currLocation
                            currLocation.setDescription(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION_ICON_NAME)) {
                            // if </location_icon_name> use setIconName() on currLocation
                            currLocation.setIconName(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION_HASVISITED)) {
                            // if </location_hasVisited> use setHasVisited() on currLocation
                            if(currText.contentEquals("true"))
                            {
                                currLocation.setHasVisited(true);
                            }
                            else currLocation.setHasVisited(false);
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations.toArray(new Location[locations.size()]);
    }

    public static Building[] getBuildings(Context ctxt) {
        XmlPullParserFactory factory;
        ArrayList<Building> buildings = new ArrayList<>();
        Building currBuilding = null;
        String currText = "";
        try {

            //Get our factory and pull parser
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            AssetManager assetManager = ctxt.getAssets();
            InputStream fis = assetManager.open("Buildings.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            //point parser to our file
            xpp.setInput(reader);

            //get initial eventType
            int eventType = xpp.getEventType();

            //loop until end of event
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_BUILDING)) {
                            // If we are starting a new <Building> block we need
                            //a new StackSite object to represent it
                            currBuilding = new Building();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        currText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_BUILDING)) {
                            // if </Building> then we are done with current Building
                            // add it to the list.
                            buildings.add(currBuilding);
                        } else if(tagname.equalsIgnoreCase(KEY_LOCATION_ID)){
                            currBuilding.setLocId(Integer.parseInt(currText));
                        }
                        else if (tagname.equalsIgnoreCase(KEY_LOCATION_NAME)) {
                            // if </location_name> use setName() on currBuilding
                            currBuilding.setName(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION_DESCRIPTION)) {
                            // if </location_description> use setDescription() on currBuilding
                            currBuilding.setDescription(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION_ICON_NAME)) {
                            // if </location_icon_name> use setIconName() on currBuilding
                            currBuilding.setIconName(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION_HASVISITED)) {
                            // if </location_hasVisited> use setHasVisited() on currBuilding
                            if(currText.contentEquals("true"))
                            {
                                currBuilding.setHasVisited(true);
                            }
                            else currBuilding.setHasVisited(false);
                        } else if (tagname.equalsIgnoreCase(KEY_BUILDING_MAP_IMAGE)) {
                            currBuilding.setMapImage(currText);
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buildings.toArray(new Building[buildings.size()]);
    }

    public static Target[] getTargets(Context ctxt) {
        XmlPullParserFactory factory;
        ArrayList<Target> targets = new ArrayList<>();
        Target currTarget = null;
        String currText = "";
        try {

            //Get our factory and pull parser
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            AssetManager assetManager = ctxt.getAssets();
            InputStream fis = assetManager.open("Targets.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            //point parser to our file
            xpp.setInput(reader);

            //get initial eventType
            int eventType = xpp.getEventType();

            //loop until end of event
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_TARGET)) {
                            // If we are starting a new <Location> block we need
                            //a new StackSite object to represent it
                            currTarget = new Target();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        currText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_TARGET)) {
                            // if </Location> then we are done with current Location
                            // add it to the list.
                            targets.add(currTarget);
                        } else if(tagname.equalsIgnoreCase(KEY_TARGET_ID)){
                            currTarget.setTargetID(Integer.parseInt(currText));
                        }
                        else if (tagname.equalsIgnoreCase(KEY_TARGET_NAME)) {
                            // if </location_name> use setName() on currLocation
                            currTarget.setTargetName(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_TARGET_IMAGE_NAME)) {
                            // if </location_description> use setDescription() on currLocation
                            currTarget.setImageName(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_TARGET_LOCATION_ID)) {
                            // if </location_icon_name> use setIconName() on currLocation
                            currTarget.setLocID(Integer.parseInt(currText));
                        }
                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return targets.toArray(new Target[targets.size()]);
    }

    public static Step[] getSteps(Context ctxt) {
        XmlPullParserFactory factory;
        ArrayList<Step> steps = new ArrayList<>();
        Step currStep = null;
        String currText = "";
        try {

            //Get our factory and pull parser
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            AssetManager assetManager = ctxt.getAssets();
            InputStream fis = assetManager.open("Steps.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            //point parser to our file
            xpp.setInput(reader);

            //get initial eventType
            int eventType = xpp.getEventType();

            //loop until end of event
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_STEP)) {
                            // If we are starting a new <Step> block we need
                            currStep = new Step();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        currText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_STEP)) {
                            // if </Step> then we are done with current Step
                            // add it to the list.
                            steps.add(currStep);
                        } else if(tagname.equalsIgnoreCase(KEY_LOCATION_ID)){
                            currStep.setLocId(Integer.parseInt(currText));
                        }
                        else if (tagname.equalsIgnoreCase(KEY_STEP_NUMBER)) {
                            // if </step_name> use setName() on currStep
                            currStep.setStepNum(Integer.parseInt(currText));
                        } else if (tagname.equalsIgnoreCase(KEY_STEP_DESCRIPTION)) {
                            // if </step_description> use setDescription() on currStep
                            currStep.setStepDesc(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_STEP_PICTURE_NAME)) {
                            // if </step_picture_name> use setIconName() on currStep
                            currStep.setPictureName(currText);
                        } else if (tagname.equalsIgnoreCase(KEY_STEP_TITLE)) {
                            // if </step_title> use setHasVisited() on currLocation
                            currStep.setTitle(currText);
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return steps.toArray(new Step[steps.size()]);
    }
}
