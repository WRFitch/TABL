package com.example.android.tabl.utils;

/**
 * Collection of network utilities for TABL.
 *
 * @WRFitch
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * TODO: implement test data
 * TODO: implement data caching for favourite items
 */

public class TablNetUtils {


    public static boolean isNetworkAvailable(Context c){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String dbTest(){
        return null;
    }

    public static void connectToDB(){

    }

    //returns default restaurant data
    public static String[] getRestaurantData(){
        return null;
    }

    public static String[] getRestaurantData(int restaurantId){
        return null;
    }

    //define what radius and current location
    public static String[] getNearbyRestaurants(){
        String[] returnVal = null;
        return returnVal;
    }

    //define what radius and current location
    public static String[] getNearbyRestaurants(int radius){
        return null;
    }
}
