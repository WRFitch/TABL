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


    public SubMenu(){
        this.name = "Mains";
    }

    public SubMenu(Context c){
        Resources res = c.getResources();
        this.name = res.getString(R.string.menu_item);

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
