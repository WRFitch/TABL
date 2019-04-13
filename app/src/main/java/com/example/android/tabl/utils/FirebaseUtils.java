package com.example.android.tabl.utils;

/**
 * Collection of network utilities for TABL.
 *
 * @WRFitch
 */

import android.location.Location;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.android.tabl.restaurant_recyclerview.Restaurant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirebaseUtils {

    static FirebaseFirestore db;

    //this is bad, and I should feel bad.
    static String restaurantCollection = "Restaurants";

    /*
    //old, perhaps-functional one
    public static ArrayList<Restaurant> getRestaurantsInRadius(Location userLoc, double radius){
        db = FirebaseFirestore.getInstance();
        final ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        CollectionReference restaurantsRef = db.collection(restaurantCollection);
        //
        Query query = restaurantsRef
                .whereLessThanOrEqualTo("Longitude", userLoc.getLongitude()+radius)
                .whereGreaterThanOrEqualTo("Longitude", userLoc.getLongitude()-radius);
        Query query2 = restaurantsRef.whereLessThanOrEqualTo("Latitude", userLoc.getLatitude()+radius)
                .whereGreaterThanOrEqualTo("Latitude", userLoc.getLatitude()-radius);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        restaurantList.add(new Restaurant(document.getData()));
                    }
                }
            }
        });
        return restaurantList;
    }*/

    //new, perhaps-functional one
    public static ArrayList<Restaurant> getRestaurantsInRadius(Location userLoc, double radius){
        radius =100;
        db = FirebaseFirestore.getInstance(FirebaseApp.getInstance());
        final ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        CollectionReference restaurantsRef = db.collection(restaurantCollection);
        Query query = restaurantsRef.whereEqualTo("Name", "Bella Italia");
        Query query1 = restaurantsRef
                .whereLessThanOrEqualTo("Longitude", userLoc.getLongitude()+radius)
                .whereGreaterThanOrEqualTo("Longitude", userLoc.getLongitude()-radius);
        Query query2 = restaurantsRef
                .whereLessThanOrEqualTo("Latitude", userLoc.getLatitude()+radius)
                .whereGreaterThanOrEqualTo("Latitude", userLoc.getLatitude()-radius);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //currently query not returning anything based on poor db reference or poor query
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        restaurantList.add(new Restaurant());
                    }
                }
            }
        });
        return restaurantList;
    }
}