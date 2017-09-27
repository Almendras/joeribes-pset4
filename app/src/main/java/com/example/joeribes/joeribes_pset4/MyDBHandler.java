package com.example.joeribes.joeribes_pset4;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    // Initializing values for the database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "activityDB.db";
    private static final String TABLE_ACTIVITY = "activityTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ACTIVITY = "activityName";
    private static final String COLUMN_FINISHED = "finished";
    private static final String COLUMN_DESCRIPTION = "activityDescription";


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ACTIVITY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ACTIVITY + " TEXT NOT NULL, " +
                COLUMN_FINISHED + " BOOL NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        onCreate(db);
    }

    //Add a new row to the database
    public void create(Activity activity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTIVITY, activity.get_activityName());
        values.put(COLUMN_DESCRIPTION, activity.get_description());
        values.put(COLUMN_FINISHED, activity.get_finished());
        db.insert(TABLE_ACTIVITY, null, values);
        db.close();
    }

    //Delete a activity from the database
    public void delete(int activityID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ACTIVITY + " WHERE " + COLUMN_ID + "=\"" + activityID + "\";");
    }

    // Read the values from the database and story them in an ArrayList
    public ArrayList<Activity> read(){
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Activity> activities = new ArrayList<>();

        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_ACTIVITY + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_FINISHED + " FROM " + TABLE_ACTIVITY;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY));
                String productDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int finished = cursor.getInt(cursor.getColumnIndex(COLUMN_FINISHED));
                Activity activity = new Activity(productName, productDescription, id, finished);
                activities.add(activity);
            }

            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return activities;

    }

    // Delete values from the database which match the id
    public Activity readDescription(int activity_id){
        SQLiteDatabase db = getReadableDatabase();

        Activity activity = null;

        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_ACTIVITY + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_FINISHED + " FROM " + TABLE_ACTIVITY + " WHERE " + COLUMN_ID + " = " + activity_id;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY));
                String productDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                int finished = cursor.getInt(cursor.getColumnIndex(COLUMN_FINISHED));
                activity = new Activity(productName, productDescription, id, finished);
            }

            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return activity;
    }

    // Update the database with new values
    public int update(Activity activity) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACTIVITY, activity.get_activityName());
        values.put(COLUMN_DESCRIPTION, activity.get_description());
        values.put(COLUMN_FINISHED, activity.get_finished());
        return db.update(TABLE_ACTIVITY, values, COLUMN_ID + " = ? ", new String[] { String.valueOf(activity.get_id()) });

    }



}