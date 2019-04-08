package com.example.android.tabl.utils;

/**
 * Collection of network utilities for TABL.
 *
 * @WRFitch
 */

import android.location.Location;

import com.example.android.tabl.restaurant_recyclerview.Restaurant;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

/**
 * TODO: implement test data
 * TODO: implement data caching for favourite items
 */

public class FirebaseUtils {

    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    //this is bad, and I should feel bad.
    static String restaurantCollection = "restaurants";

    public static ArrayList<Restaurant> getRestaurants(Location userLoc, double radius){
        CollectionReference restaurantsRef = db.collection(restaurantCollection);
        
        return null;
    }
}