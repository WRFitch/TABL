package com.example.android.tabl.restaurant_recyclerview;

import android.content.Context;
import android.content.res.Resources;

import com.example.android.tabl.R;
import com.google.android.gms.maps.model.LatLng;

/**
 * Restaurant object for FindRestaurantActivity
 *
 * @WRFitch
 */

/**
 * TODO: lighten class - too many variables
 * TODO: define class requirements better - can this be used for network processing?
 *
 */

public class Restaurant {

    private String name;
    private String id;
    private String address;
    private String[] flags;
    private LatLng location; //find best location datatype to use
    private String distanceFromUser;

    public Restaurant(){
    }

    //unfinished default constructor
    public Restaurant(Context c){
        Resources res = c.getResources();
        this.name = res.getString(R.string.default_restaurant);
        this.address = res.getString(R.string.default_addr);
        this.distanceFromUser = res.getString(R.string.default_distance) + " "
                + res.getString(R.string.dist_miles);
        this.id = res.getString(R.string.default_id);
        this.flags = res.getStringArray(R.array.menu_titles);
    }

    public Restaurant(String name, String id, String address, String distanceFromUser,
                      String[] flags,
                      LatLng location){
        this.name = name;
        this.id = id;
        this.address = address;
        this.flags = flags;
        this.location = location;
        this.distanceFromUser = distanceFromUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getFlags() {
        return flags;
    }

    public void setFlags(String[] flags) {
        this.flags = flags;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(String distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }

    //debug ver - do not add to final app!
    public String[] getMenuTitles(){
        String[] returnVal = {

        };
        return returnVal;
    }

    public String[] getMenuTitles(String restaurantId){
        String[] returnVal = {

        };
        return returnVal;
    }
}
