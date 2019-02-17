package com.example.android.tabl.db_handlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.tabl.restaurant_recyclerview.Restaurant;

public class TablDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tablDB.db";
    public static final String TABLE_NAME = "Restaurant";
    public static final String COLUMN_ID = "RestaurantID";
    public static final String COLUMN_NAME = "RestaurantName";
    public static final String COLUMN_ADDRESS = "RestaurantAddr";
    public static final String COLUMN_LATLNG = "RestaurantLatLng";
    public static final String COLUMN_FLAGS_ID = "RestaurantFlagsID";

    public TablDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public String loadHandler() {
        return null;
    }
    public void addHandler(Restaurant restaurant) {

    }
    public Restaurant findHandler(String studentname) {
        return null;
    }
    public boolean deleteHandler(int ID) {
        return false;
    }
    public boolean updateHandler(int ID, String name) {
        return false;
    }
}
