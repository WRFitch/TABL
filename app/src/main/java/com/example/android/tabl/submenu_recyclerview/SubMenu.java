package com.example.android.tabl.submenu_recyclerview;

import android.content.Context;
import android.content.res.Resources;

import com.example.android.tabl.R;
import com.example.android.tabl.menu_recyclerview.FoodItem;

import java.util.ArrayList;

/**
 * Menu object for MenuActivity RecyclerView
 *
 * @ImanBerrouhou
 */

public class SubMenu {

    private String name;
    private ArrayList<FoodItem> foodList;

    public SubMenu(){
        this.name = "Mains";
    }

    public SubMenu(Context c){
        Resources res = c.getResources();
        this.name = res.getString(R.string.menu_item);

    }

    public SubMenu(String name, ArrayList<FoodItem> foodList){
        this.name = name;
        this.foodList = foodList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FoodItem> getFoodList(){
        return this.foodList;
    }

    public void setFoodList(ArrayList<FoodItem> newFoodList){
        this.foodList = newFoodList;
    }

    public void addToFoodList(FoodItem foodItem){
        this.foodList.add(foodItem);
    }

    public void clearFoodList(){
        this.foodList = new ArrayList<>();
    }
}
