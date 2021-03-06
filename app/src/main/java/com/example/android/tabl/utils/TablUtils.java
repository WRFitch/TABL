package com.example.android.tabl.utils;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.tabl.R;

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

    public static void functionNotImplemented(View view, String msg){
        //this code will be useful in future for basically anything
        Snackbar.make(view, msg + view.getContext().getString(R.string.todo),
                Snackbar.LENGTH_LONG).setAction(R.string.todo, null)
                .show();
    }

    public static void errorMsg(View view, String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void getLocationPerms(Context c, Activity a){
        if (!checkLocationPerms(c)) {
            requestLocationPerms(a);
        }
    }

    public static boolean checkLocationPerms(Context c){
        if (ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(c, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public static void requestLocationPerms(Activity a){
        ActivityCompat.requestPermissions(a, new String[]
                        { Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION },
                1);
    }

    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
