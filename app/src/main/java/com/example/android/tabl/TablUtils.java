package com.example.android.tabl;


import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * A class of utilities to be used across this application.
 *
 * @WRFitch
 */

public class TablUtils {

    //a standard error message.
    public static void functionNotImplemented(View view){
        //this code will be useful in future for basically anything
        Snackbar.make(view, view.getContext().getString(R.string.todo),
                Snackbar.LENGTH_LONG).setAction(R.string.todo, null)
                .show();
    }

    //get data re restaurant from server
    public static String[] getRestaurantData(){
        String[] returnVal = {

        };


        return null;
    }

    public static void waitXMillis(int ms){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                return;
            }
        }, ms);
    }
}
