package com.qualcomm.QCARSamples.ImageTargets.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.qualcomm.QCARSamples.ImageTargets.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 6/4/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static DatabaseHelper sInstance;
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "locationManager";

    // Table Names
    private static final String TABLE_LOCATION = "location";
    private static final String TABLE_TARGET = "target";
    private static final String TABLE_LOCATION_IMAGE = "loc_has_images";

    // Common column names
    private static final String KEY_ID = "id";

    // LOCATION Table - column nmaes
    private static final String KEY_LOCATION_NAME = "location_name";
    private static final String KEY_HAS_VISITED = "hasVisited";
    private static final String KEY_DESCRIPTION = "location_description";
    private static final String KEY_ICON_NAME = "icon_name";

    // TAGS Table - column names
    private static final String KEY_TARGET_NAME = "target_name";
    private static final String KEY_IMAGE_NAME = "image_name";
    private static final String KEY_LOCATION_ID = "location_id";

    // LOC_HAS_IMAGES Table - column names
    private static final String KEY_LOCATION_ID_IMAGES = "location_id";
    private static final String KEY_IMAGE_NAME_IMAGES = "image_name";

    // Table Create Statements
    // location table create statement
    private static final String CREATE_TABLE_LOCATION = "CREATE TABLE "
            + TABLE_LOCATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION_NAME
            + " TEXT," + KEY_HAS_VISITED + " INTEGER," + KEY_DESCRIPTION
            + " TEXT," + KEY_ICON_NAME +" TEXT" + ")";

    // targets table create statement
    private static final String CREATE_TABLE_TARGET = "CREATE TABLE " + TABLE_TARGET
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION_ID + " INTEGER, " + KEY_TARGET_NAME + " TEXT,"
            + KEY_IMAGE_NAME + " TEXT" + ")";

    // loc has images table create statement
    private static final String CREATE_TABLE_LOC_HAS_IMAGES = "CREATE TABLE "
            + TABLE_LOCATION_IMAGE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_LOCATION_ID_IMAGES + " INTEGER," + KEY_IMAGE_NAME_IMAGES + " TEXT" + ")";

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
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_LOCATION);
        db.execSQL(CREATE_TABLE_TARGET);
        db.execSQL(CREATE_TABLE_LOC_HAS_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TARGET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION_IMAGE);

        // create new tables
        onCreate(db);
    }

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

        return tl;
    }
    
    public List<Location> getAllLocations() {
        List<Location> locationList = new ArrayList<Location>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOCATION;

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

                // adding to todo list
                locationList.add(tl);
            } while (c.moveToNext());
        }

        return locationList;
    }

    public long createLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOCATION_NAME, location.getName());
        values.put(KEY_DESCRIPTION, location.getDescription());
        values.put(KEY_HAS_VISITED, location.isHasVisited());
        values.put(KEY_ICON_NAME, location.getIconName());

        // insert row
        long tag_id = db.insert(TABLE_LOCATION, null, values);

        return tag_id;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}