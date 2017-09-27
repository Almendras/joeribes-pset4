package com.example.joeribes.joeribes_pset4;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_PRODUCTS = "productTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCTNAME = "productName";
    private static final String COLUMN_DESCRIPTION = "productDescription";

    //We need to pass database information along to superclas
    /*
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    */
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PRODUCTNAME + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void create(Activity activity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, activity.get_productname());
        values.put(COLUMN_DESCRIPTION, activity.get_description());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Delete a activity from the database
    public void delete(int activityID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "=\"" + activityID + "\";");
    }

    public ArrayList<Activity> read(){
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Activity> activities = new ArrayList<>();

        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_PRODUCTNAME + ", " + COLUMN_DESCRIPTION + " FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME));
                String productDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                Activity activity = new Activity(productName, productDescription, id);
                activities.add(activity);
            }

            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return activities;

    }


    public Activity descriptionToString(int food_id){
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Activity> activities = new ArrayList<>();
        Activity activity = null;

        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_PRODUCTNAME + ", " + COLUMN_DESCRIPTION + " FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " = " + food_id;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME));
                String productDescription = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                activity = new Activity(productName, productDescription, id);
            }

            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return activity;
    }

    public int update(Activity activity) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, activity.get_productname());
        values.put(COLUMN_DESCRIPTION, activity.get_description());

        return db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ? ", new String[] { String.valueOf(activity.get_id())});
    }



}