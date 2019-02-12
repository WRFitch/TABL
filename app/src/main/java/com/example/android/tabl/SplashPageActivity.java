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
 * TODO: refactor for efficiency
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tabl.utils.TablUtils;

public class SplashPageActivity extends AppCompatActivity{

    //private parcel (parcel of data)
    private final int WAIT_VALUE = 10000;
    private boolean firedNext = false;
    private boolean isNextReady = false;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        prepNextView();
        tv = findViewById(R.id.SplashPageVersionName);

        //wait x seconds, then load. delete this once next activity is implemented.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if((!firedNext) && isNextReady) {
                    callFindRestaurantActivity(SplashPageActivity.this);
                }
            }
        }, WAIT_VALUE);

        ImageView logo = findViewById(R.id.SplashPageLogo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firedNext = true;
                if(isNextReady) callFindRestaurantActivity(v.getContext());
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

    //preloads menu data in preparation for next method, parcels them to be passed over
    private void preloadFRAData(){
        //checkLocationPermission();
    }

    private void prepNextView(){
        TablUtils.checkLocationPerms(this, this);
        isNextReady = true;
    }
}
