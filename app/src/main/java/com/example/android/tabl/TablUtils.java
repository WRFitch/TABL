package com.example.android.tabl;


import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * A class of utilities to be used across this application.
 *
 * @WRFitch
 */

public class TablUtils {

    //a standard error message. T
    public static void functionNotImplemented(View view){
        //this code will be useful in future for basically anything
        Snackbar.make(view, view.getContext().getString(R.string.todo),
                Snackbar.LENGTH_LONG).setAction(R.string.todo, null)
                .show();
    }

}
