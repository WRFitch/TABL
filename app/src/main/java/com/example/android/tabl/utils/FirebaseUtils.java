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
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FirebaseUtils {

    static FirebaseFirestore db;

    //this is bad, and I should feel bad.
    static String restaurantCollection = "Restaurants";

    //new, perhaps-functional one
    public static ArrayList<Restaurant> getRestaurantsInRadius(Location userLoc, double radius){
        final ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        int numCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(numCores * 2, numCores *2,
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        db = FirebaseFirestore.getInstance(FirebaseApp.getInstance());
        CollectionReference restaurantsRef = db.collection(restaurantCollection);
        Query query1 = restaurantsRef
                .whereLessThanOrEqualTo("Longitude", userLoc.getLongitude()+radius)
                .whereGreaterThanOrEqualTo("Longitude", userLoc.getLongitude()-radius);
        Query query2 = restaurantsRef
                .whereLessThanOrEqualTo("Latitude", userLoc.getLatitude()+radius)
                .whereGreaterThanOrEqualTo("Latitude", userLoc.getLatitude()-radius);

        // Create a reference to the cities collection
        CollectionReference citiesRef = db.collection("cities");
        // Create a query against the collection.
        Query query = citiesRef.whereEqualTo("state", "CA");
        Task getQuery = query.get().addOnCompleteListener(executor, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        restaurantList.add(new Restaurant());
                    }
                }
            }
        });
        return restaurantList;
    }
}