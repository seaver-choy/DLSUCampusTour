package com.qualcomm.QCARSamples.ImageTargets.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 6/4/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static DatabaseHelper sInstance;
    private static final String LOG = "DatabaseHelper";
    private Context context;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "locationManager";

    // Table Names
    private static final String TABLE_LOCATION = "location";
    private static final String TABLE_TARGET = "target";
    private static final String TABLE_LOCATION_IMAGE = "loc_has_images";
    private static final String TABLE_BUILDING = "building";
    private static final String TABLE_STEP = "step";

    // Common column names
    private static final String KEY_ID = "id";

    // LOCATION Table - column names
    private static final String KEY_LOCATION_NAME = "location_name";
    private static final String KEY_HAS_VISITED = "hasVisited";
    private static final String KEY_DESCRIPTION = "location_description";
    private static final String KEY_ICON_NAME = "icon_name";
    private static final String KEY_SHORTDESCRIPTION = "location_shortDescription";

    // TARGETS Table - column names
    private static final String KEY_TARGET_NAME = "target_name";
    private static final String KEY_IMAGE_NAME = "image_name";
    private static final String KEY_LOCATION_ID = "location_id";

    // LOC_HAS_IMAGES Table - column names
    private static final String KEY_LOCATION_ID_IMAGES = "location_id";
    private static final String KEY_IMAGE_NAME_IMAGES = "image_name";

    // BUILDING Table - column names
    private static final String KEY_BUILDING_LOCATION_ID = "location_id";
    private static final String KEY_BUILDING_MAP_IMAGE = "building_map";

    // STEP Table - column names
    private static final String KEY_STEP_LOCATION_ID = "location_id";
    private static final String KEY_STEP_DESCRIPTION = "step_description";
    private static final String KEY_STEP_NUMBER = "step_number";
    private static final String KEY_STEP_PICTURE = "step_picture";
    private static final String KEY_STEP_TITLE = "step_title";

    // Table Create Statements
    // location table create statement
    private static final String CREATE_TABLE_LOCATION = "CREATE TABLE "
            + TABLE_LOCATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION_NAME
            + " TEXT," + KEY_HAS_VISITED + " INTEGER," + KEY_DESCRIPTION
            + " TEXT," + KEY_ICON_NAME +" TEXT," + KEY_SHORTDESCRIPTION + " TEXT" + ")";

    // targets table create statement
    private static final String CREATE_TABLE_TARGET = "CREATE TABLE " + TABLE_TARGET
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION_ID + " INTEGER, " + KEY_TARGET_NAME + " TEXT,"
            + KEY_IMAGE_NAME + " TEXT" + ")";

    // loc has images table create statement
    private static final String CREATE_TABLE_LOC_HAS_IMAGES = "CREATE TABLE "
            + TABLE_LOCATION_IMAGE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_LOCATION_ID_IMAGES + " INTEGER," + KEY_IMAGE_NAME_IMAGES + " TEXT" + ")";

    private static final String CREATE_TABLE_BUILDING = "CREATE TABLE " + TABLE_BUILDING
            + "(" + KEY_BUILDING_LOCATION_ID + " INTEGER PRIMARY KEY, " + KEY_BUILDING_MAP_IMAGE + " TEXT" + ")";

    private static final String CREATE_TABLE_STEP = "CREATE TABLE " + TABLE_STEP + "(" + KEY_STEP_LOCATION_ID
            + " INTEGER NOT NULL, " + KEY_STEP_NUMBER + " INTEGER NOT NULL, " + KEY_STEP_DESCRIPTION + " TEXT, "
            + KEY_STEP_TITLE + " TEXT, " + KEY_STEP_PICTURE + " TEXT, " + "PRIMARY KEY (" + KEY_STEP_LOCATION_ID + ", "
            + KEY_STEP_NUMBER + "))";
    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private Context getContext()
    {
        return this.context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_LOCATION);
        db.execSQL(CREATE_TABLE_TARGET);
        db.execSQL(CREATE_TABLE_LOC_HAS_IMAGES);
        db.execSQL(CREATE_TABLE_BUILDING);
        db.execSQL(CREATE_TABLE_STEP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TARGET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION_IMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUILDING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STEP);
        // create new tables
        onCreate(db);
    }

    public void initializeDatabaseFromXML()
    {
        Location locations[] = SitesXMLPullParser.getLocations(this.getContext());
        Log.e("TAG", "SUCCESSFULLY READ " + locations.length + " locations from Locations.xml");
        for(int i = 0; i < locations.length; i++)
        {
            this.createLocation(locations[i]);
        }

        Building buildings[] = SitesXMLPullParser.getBuildings(this.getContext());
        Log.e("TAG", "SUCCESSFULLY READ " + buildings.length + " buildings from Buildings.xml");
        for(int i = 0; i < buildings.length; i++)
        {
            this.createBuilding(buildings[i]);
        }

        Target targets[] = SitesXMLPullParser.getTargets(this.getContext());
        Log.e("TAG", "SUCCESSFULLY READ " + targets.length + " targets from Targets.xml");
        for(int i = 0; i < targets.length; i++)
        {
            this.createTarget(targets[i]);
        }

        Step steps[] = SitesXMLPullParser.getSteps(this.getContext());
        Log.e("TAG", "SUCCESSFULLY READ " + steps.length + " steps from Steps.xml");
        for(int i = 0; i < steps.length; i++)
        {
            this.createStep(steps[i]);
        }

    }

    //CRUD of location
    public Location getLocation(int loc_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_LOCATION + " WHERE "
                + KEY_ID + " = " + loc_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Location tl = new Location();
        tl.setLocId(c.getInt((c.getColumnIndex(KEY_ID))));
        tl.setDescription((c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
        tl.setIconName(c.getString(c.getColumnIndex(KEY_ICON_NAME)));
        tl.setName(c.getString(c.getColumnIndex(KEY_LOCATION_NAME)));
        tl.setHasVisited(c.getInt(c.getColumnIndex(KEY_HAS_VISITED)) == 1);
        tl.setShortDescription(c.getString(c.getColumnIndex(KEY_SHORTDESCRIPTION)));
        tl.setImageNames(this.getImagesOfLocation(tl.getLocId()));
        tl.setImageIcons(this.getContext());
        c.close();
        return tl;
    }
    
    public List<Location> getAllLocations() {
        List<Location> locationList = new ArrayList<Location>();


        List<Building> buildingList = this.getAllBuildings();
        locationList.addAll(buildingList);

        String selectQuery = "SELECT * FROM " + TABLE_LOCATION + " ORDER BY " + KEY_LOCATION_NAME;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Location tl = new Location();
                tl.setLocId(c.getInt((c.getColumnIndex(KEY_ID))));
                tl.setDescription((c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
                tl.setIconName(c.getString(c.getColumnIndex(KEY_ICON_NAME)));
                tl.setName(c.getString(c.getColumnIndex(KEY_LOCATION_NAME)));
                tl.setHasVisited(c.getInt(c.getColumnIndex(KEY_HAS_VISITED)) == 1);
                tl.setImageNames(this.getImagesOfLocation(tl.getLocId()));
                tl.setImageIcons(this.getContext());
                tl.setShortDescription(c.getString(c.getColumnIndex(KEY_SHORTDESCRIPTION)));

                // adding to todo list
                if(!locationList.contains(tl))
                locationList.add(tl);
            } while (c.moveToNext());
        }

        Collections.sort(locationList, new Comparator<Location>(){
            public int compare(Location l1, Location l2) {
                return l1.getName().compareToIgnoreCase(l2.getName());
            }
        });
        c.close();
        return locationList;
    }

    public long createLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, location.getLocId());
        values.put(KEY_LOCATION_NAME, location.getName());
        values.put(KEY_DESCRIPTION, location.getDescription());
        values.put(KEY_HAS_VISITED, location.isHasVisited());
        values.put(KEY_ICON_NAME, location.getIconName());
        values.put(KEY_SHORTDESCRIPTION, location.getShortDescription());

        // insert row
        long tag_id = db.insert(TABLE_LOCATION, null, values);

        String[] tempLocationImages = location.getImageNames();

        for(int i = 0; i < tempLocationImages.length; i++) {
            values = new ContentValues();
            values.put(KEY_LOCATION_ID_IMAGES, location.getLocId());
            values.put(KEY_IMAGE_NAME_IMAGES, tempLocationImages[i]);

            long temptag_id = db.insert(TABLE_LOCATION_IMAGE, null, values);
        }
        return tag_id;
    }

    public void changeLocationToVisited(int loc_id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HAS_VISITED, true);

        db.update(TABLE_LOCATION, values, KEY_ID + " = " + loc_id, null);

        Log.e("TAG", loc_id + " id has been changed to visited");
    }

    //CRUD of Target
    public List<Target> getAllTargets() {
        List<Target> targetList = new ArrayList<Target>();
        String selectQuery = "SELECT * FROM " + TABLE_TARGET;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Target tl = new Target();
                tl.setTargetID(c.getInt((c.getColumnIndex(KEY_ID))));
                tl.setImageName((c.getString(c.getColumnIndex(KEY_IMAGE_NAME))));
                tl.setTargetName(c.getString(c.getColumnIndex(KEY_TARGET_NAME)));
                tl.setLocID(c.getInt((c.getColumnIndex(KEY_LOCATION_ID))));

                // adding to todo list
                targetList.add(tl);
            } while (c.moveToNext());
        }
        c.close();
        return targetList;
    }

    public Target getTarget(int target_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TARGET + " WHERE "
                + KEY_ID + " = " + target_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Target tl = new Target();
        tl.setTargetID(c.getInt((c.getColumnIndex(KEY_ID))));
        tl.setImageName((c.getString(c.getColumnIndex(KEY_IMAGE_NAME))));
        tl.setTargetName(c.getString(c.getColumnIndex(KEY_TARGET_NAME)));
        tl.setLocID(c.getInt((c.getColumnIndex(KEY_LOCATION_ID))));
        c.close();
        return tl;
    }

    public String[] getTargetNames()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> targetNames = new ArrayList<String>();
        String selectQuery = "SELECT " + KEY_TARGET_NAME + " FROM " + TABLE_TARGET;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                targetNames.add(c.getString(c.getColumnIndex(KEY_TARGET_NAME)));
            } while (c.moveToNext());
        }
        c.close();
        return targetNames.toArray(new String[targetNames.size()]);
    }

    public String[] getImageNames()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> imageNames = new ArrayList<String>();
        String selectQuery = "SELECT " + KEY_IMAGE_NAME + " FROM " + TABLE_TARGET;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                imageNames.add(c.getString(c.getColumnIndex(KEY_IMAGE_NAME)));
            } while (c.moveToNext());
        }
        c.close();
        return imageNames.toArray(new String[imageNames.size()]);
    }

    public long createTarget(Target target) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, target.getTargetID());
        values.put(KEY_TARGET_NAME, target.getTargetName());
        values.put(KEY_IMAGE_NAME, target.getImageName());
        values.put(KEY_LOCATION_ID, target.getLocID());

        // insert row
        long tag_id = db.insert(TABLE_TARGET, null, values);

        return tag_id;
    }


    //CRUD of Building Table
    public long createBuilding(Building building) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, building.getLocId());
        values.put(KEY_LOCATION_NAME, building.getName());
        values.put(KEY_DESCRIPTION, building.getDescription());
        values.put(KEY_HAS_VISITED, building.isHasVisited());
        values.put(KEY_ICON_NAME, building.getIconName());
        values.put(KEY_SHORTDESCRIPTION, building.getShortDescription());

        // insert row
        long tag_id = db.insert(TABLE_LOCATION, null, values);

        String[] tempLocationImages = building.getImageNames();

        for(int i = 0; i < tempLocationImages.length; i++) {
            values = new ContentValues();
            values.put(KEY_LOCATION_ID_IMAGES, building.getLocId());
            values.put(KEY_IMAGE_NAME_IMAGES, tempLocationImages[i]);

            long temptag_id = db.insert(TABLE_LOCATION_IMAGE, null, values);
        }

        values = new ContentValues();
        values.put(KEY_BUILDING_LOCATION_ID, tag_id);
        values.put(KEY_BUILDING_MAP_IMAGE, building.getMapImage());

        tag_id = db.insert(TABLE_BUILDING, null, values);
        return tag_id;
    }

    public List<Building> getAllBuildings() {
        List<Building> buildingList = new ArrayList<Building>();
        String selectQuery = "SELECT * FROM " + TABLE_BUILDING + ", " + TABLE_LOCATION + " WHERE " + KEY_BUILDING_LOCATION_ID + " = " + KEY_ID + " ORDER BY " + KEY_LOCATION_NAME;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Building tl = new Building();
                tl.setLocId(c.getInt((c.getColumnIndex(KEY_BUILDING_LOCATION_ID))));
                tl.setDescription((c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
                tl.setIconName(c.getString(c.getColumnIndex(KEY_ICON_NAME)));
                tl.setName(c.getString(c.getColumnIndex(KEY_LOCATION_NAME)));
                tl.setHasVisited(c.getInt(c.getColumnIndex(KEY_HAS_VISITED)) == 1);
                tl.setMapImage(c.getString(c.getColumnIndex(KEY_BUILDING_MAP_IMAGE)));
                tl.setShortDescription(c.getString(c.getColumnIndex(KEY_SHORTDESCRIPTION)));

                tl.setImageNames(this.getImagesOfLocation(tl.getLocId()));
                // adding to todo list
                buildingList.add(tl);
            } while (c.moveToNext());
        }
        c.close();
        return buildingList;
    }

    //CRUD of Step Table
    public long createStep(Step step) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STEP_LOCATION_ID, step.getLocId());
        values.put(KEY_STEP_NUMBER, step.getStepNum());
        values.put(KEY_STEP_DESCRIPTION, step.getStepDesc());
        values.put(KEY_STEP_PICTURE, step.getPictureName());
        values.put(KEY_STEP_TITLE, step.getTitle());

        long tag_id = db.insert(TABLE_STEP, null, values);

        return tag_id;
    }

    public List<Step> getAllStepsOfLocation(int loc_id) {
        List<Step> stepList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_STEP + " WHERE " + KEY_STEP_LOCATION_ID + " = " + loc_id;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Step ts = new Step();
                ts.setLocId(c.getInt((c.getColumnIndex(KEY_STEP_LOCATION_ID))));
                ts.setStepDesc((c.getString(c.getColumnIndex(KEY_STEP_DESCRIPTION))));
                ts.setStepNum(c.getInt(c.getColumnIndex(KEY_STEP_NUMBER)));
                ts.setTitle(c.getString(c.getColumnIndex(KEY_STEP_TITLE)));
                ts.setPictureName(c.getString(c.getColumnIndex(KEY_STEP_PICTURE)));
                // adding to step list
                stepList.add(ts);
            } while (c.moveToNext());
        }
        c.close();
        return stepList;
    }

    //CRUD of loc_has_images table
    public String[] getImagesOfLocation(int loc_id)
    {
        ArrayList<String> imageNames = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATION_IMAGE + " WHERE " + KEY_LOCATION_ID_IMAGES + " = " + loc_id;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                imageNames.add(c.getString(c.getColumnIndex(KEY_IMAGE_NAME_IMAGES)));
            }while(c.moveToNext());
            }
        c.close();
        return imageNames.toArray(new String[imageNames.size()]);

        }


    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}