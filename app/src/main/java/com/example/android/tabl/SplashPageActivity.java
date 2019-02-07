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

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SplashPageActivity extends AppCompatActivity{

    private final int WAIT_VALUE = 10000;
    //private Class NEXT_CLASS = FindRestaurantActivity.class;
    private boolean firedNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        preloadMenuData();

        //wait x seconds, then load. delete this once next activity is implemented.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!firedNext) {
                    callFindRestaurantActivity(SplashPageActivity.this);
                    finish();
                }
            }
        }, WAIT_VALUE);

        ImageView logo = findViewById(R.id.SplashPageLogo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firedNext = true;
                callFindRestaurantActivity(v.getContext());
                finish();
            }
        });


    }

    private void callFindRestaurantActivity(Context c){
        //call next activity
        Intent intent = new Intent(c, FindRestaurantActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //swipe to remove method - just call callFindRestaurantActivity(v);
    private void onSwipe(View v) {
        callFindRestaurantActivity(v.getContext());
    }

    //preloads menu data in preparation for next method.
    private static void preloadMenuData(){
        /**
         * requirements:
         * list of restaurants from nearby
         */

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
