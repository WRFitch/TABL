package com.example.android.tabl.restaurant_recyclerview;

import com.google.android.gms.maps.model.LatLng;

/**
 * Restaurant object for FindRestaurantActivity
 *
 * @WRFitch
 */

/**
 * TODO: lighten class - too many variables
 * TODO: define class requirements better - can this be used for network processing?
 */

public class Restaurant {
    private String name;
    private String id;
    private String address;
    private String[] flags;
    private LatLng location;//find best location datatype to use
    private int distanceFromUser;

    public Restaurant(){
    }

    public Restaurant(String name, String id, String address,
                      String[] flags,
                      LatLng location,
                      int distanceFromUser){
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

    public int getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(int distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }
}
