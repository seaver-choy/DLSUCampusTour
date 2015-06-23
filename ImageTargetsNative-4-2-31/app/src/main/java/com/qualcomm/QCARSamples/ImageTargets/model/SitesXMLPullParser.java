package com.qualcomm.QCARSamples.ImageTargets.model;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
    private static final String KEY_LOCATION_ISBUILDING = "location_isBuilding";

    //Target keys
    private static final String KEY_TARGET = "Target";
    private static final String KEY_TARGET_ID = "target_id";
    private static final String KEY_TARGET_NAME = "target_name";
    private static final String KEY_TARGET_IMAGE_NAME = "target_image_name";
    private static final String KEY_TARGET_LOCATION_ID = "target_location_id";

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
            FileInputStream fis = ctxt.openFileInput("Locations.xml");
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
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION_ISBUILDING)) {
                            // if </location_isBuilding> use setIsBuilding() on currLocation
                            if(currText.contentEquals("true"))
                            {
                                currLocation.setIsBuilding(true);
                            }
                            else currLocation.setIsBuilding(false);
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
            FileInputStream fis = ctxt.openFileInput("Targets.xml");
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
}
