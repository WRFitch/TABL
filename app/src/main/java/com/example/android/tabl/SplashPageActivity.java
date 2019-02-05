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
 * TODO: Refactor to fit SplashScreen - change to image fragment?
 * TODO: implement swipe to remove
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashPageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        ImageView logo = findViewById(R.id.SplashPageLogo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call next activity
                Intent intent = new Intent(v.getContext(),
                        FindRestaurantActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();
            }
        });

        preloadMenuData();
    }

    //swipe to remove
    private void onSwipe() {
        //remove (duh)
    }

    //preloads menu data in preparation for next method.
    private static void preloadMenuData(){
        /**
         * requirements:
         * list of restaurants from nearby
         */
    }
}
