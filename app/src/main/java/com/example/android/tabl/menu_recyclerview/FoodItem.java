package com.example.android.tabl.menu_recyclerview;

import android.content.Context;
import android.content.res.Resources;

import com.example.android.tabl.R;

import java.util.Map;

/**
 * Menu object for MenuActivity RecyclerView
 *
 * @WRFitch
 */

public class FoodItem {

    private String name;
    private double price;
    private String description;
    private String[] flags;

    public FoodItem(){
        this.name = "test_title";
        this.price = -1.5;
        this.description = "test description";
        this.flags = new String[] {"vegan", "vegetarian", "gluten_free", "halal"};
    }

    public FoodItem(Map map){
        this.name = (String) map.get("Name");
        this.price = (Double) map.get("Price");
        this.description = (String) map.get("Description");
        this.flags = (String[]) map.get("Flags");
    }

    //unfinished default constructor. Passing context is bad and I shouldn't do it.
    public FoodItem(Context c){
        Resources res = c.getResources();
        this.name = res.getString(R.string.default_restaurant);

    }

    public FoodItem(String name, double price, String description, String[] tags){
        this.name = name;
        this.price = price;
        this.description = description;
        this.flags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getFlags() {
        return flags;
    }

    public void setFlags(String[] flags) {
        this.flags = flags;
    }
}
