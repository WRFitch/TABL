package com.example.android.tabl.submenu_recyclerview;

import android.content.Context;
import android.content.res.Resources;

import com.example.android.tabl.R;

/**
 * Menu object for MenuActivity RecyclerView
 *
 * @ImanBerrouhou
 */

public class SubMenu {


    private String name;
    private String description;

    public SubMenu(){

        this.name = "test_subname";

    }

    public SubMenu(Context c){
        Resources res = c.getResources();
        this.name = res.getString(R.string.name_of_item);

    }

    public SubMenu(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
