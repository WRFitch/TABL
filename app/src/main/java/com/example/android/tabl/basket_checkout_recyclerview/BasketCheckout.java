package com.example.android.tabl.basket_checkout_recyclerview;

import android.content.Context;
import android.content.res.Resources;

import com.example.android.tabl.R;

public class BasketCheckout {

    private String itemname;
    private String itemid;
    private String price;
    private integer quantity;
    private String option;

    public BasketCheckout(){
    }

    //unfinished default constructor. Passing context is bad and I shouldn't do it.
    public BasketCheckout(Context c){
        Resources res = c.getResources();
        this.itemname = res.getString(R.string.default_restaurant);
        this.price = res.getString(R.string.default_addr);
        this.itemid = res.getString(R.string.default_id);
        this.quantity = res.getString(R.string.default_id);
        //this.menuIds = res.getStringArray(R.array.menu_ids);



}
