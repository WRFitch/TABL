package com.example.android.tabl;


public class MenuListActvity {

    private int menu_image_id;
    private String menu_item;

    public MenuListActvity(int menu_image_id, String menu_item) {
        this.menu_image_id = menu_image_id;
        this.menu_item = menu_item;
    }


    public int getMenu_image_id(){

        return menu_image_id;
    }

    public String getMenu_item() {

        return menu_item;
    }
}
