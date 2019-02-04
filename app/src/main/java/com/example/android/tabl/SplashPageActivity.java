package com.example.android.tabl;

/**
 * Splash Page for TABL
 *
 * A simple image that users swipe away after a second to start application. Alters usability and
 * hides small optimisation getting local restaurant data in preparation for FindRestaurantActivity.
 *
 * @WRFitch
 */

/**
 * TODO: This is now the splashscreen, not the StartPage.
 * TODO: Refactor to fit SplashScreen - change to image fragment?
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashPageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);
    }

}
