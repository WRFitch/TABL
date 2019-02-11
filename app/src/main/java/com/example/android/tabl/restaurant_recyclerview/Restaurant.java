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
    private String[] menuIds;
    private LatLng location; //find best location datatype to use

    public Restaurant(){
    }

    //unfinished default constructor. Passing context is bad and I shouldn't do it.
    public Restaurant(Context c){
        Resources res = c.getResources();
        this.name = res.getString(R.string.default_restaurant);
        this.address = res.getString(R.string.default_addr);
        this.id = res.getString(R.string.default_id);
        this.flags = res.getStringArray(R.array.menu_titles);
        //this.menuIds = res.getStringArray(R.array.menu_ids);
    }

    public Restaurant(String name, String id, String address, String distanceFromUser,
                      String[] flags,
                      LatLng location){
        this.name = name;
        this.id = id;
        this.address = address;
        this.flags = flags;
        this.location = location;
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

    //calc restaurant distance from user
    public String getDistanceFromUser() {
        return null;
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

    public String[] getMenuIds(){
        return this.menuIds;
    }
}
