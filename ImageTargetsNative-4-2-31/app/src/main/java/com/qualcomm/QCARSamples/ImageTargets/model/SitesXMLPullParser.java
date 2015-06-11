package com.qualcomm.QCARSamples.ImageTargets.model;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;

/**
 * Created by admin on 6/11/2015.
 */
public class SitesXMLPullParser {

    static final String KEY_LOCATION_ID = "location_id";
    static final String KEY_LOCATION_NAME = "location_name";
    static final String KEY_LOCATION_DESCRIPTION = "location_description";
    static final String KEY_LOCATION_ICON_NAME = "location_icon_name";
    static final String KEY_LOCATION_HASVISITED = "location_hasVisited";

    public static Location[] getLocations(Context ctxt) {
        XmlPullParserFactory factory;
        ArrayList<Location> locations = new ArrayList<>();
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return locations.toArray(new Location[locations.size()]);
    }
}
