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

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tabl.utils.TablUtils;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class SplashPageActivity extends AppCompatActivity{

    private boolean isNextReady = false;
    private TextView tv;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        prepNextActivity();

        ImageView logo = findViewById(R.id.SplashPageLogo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNextReady){
                    callFindRestaurantActivity(v.getContext());
                    finish();
                } else {
                    TablUtils.errorMsg(v, "still loading!");
                }
            }
        });
    }

    private void callFindRestaurantActivity(Context c){
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
        TablUtils.getLocationPerms(this, new FindRestaurantActivity());
        isNextReady = true;
    }

    //rewrite for efficiency
    private void prepNextActivity(){
        TablUtils.getLocationPerms(this, this);
        firestoreSetup();
        //isNextReady = TablUtils.checkLocationPerms(this); //android's parallelisation can get out.
        isNextReady = true;
    }

    private void firestoreSetup(){
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
    }
}
