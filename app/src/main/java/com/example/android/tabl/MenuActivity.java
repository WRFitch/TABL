package com.example.android.tabl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_more:
                            // open MoreActivity
                            Intent moreIntent = new Intent(getApplicationContext(),
                                    MoreActivity.class);
                            moreIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(moreIntent);
                            break;
                        case R.id.nav_location:
                            // go back to previous activity
                            // downloaded data should be cached automatically, making any
                            // caching method here unnecessary
                            finish();
                            break;
                        case R.id.nav_basket:
                            // open BasketActivity
                            Intent basketIntent = new Intent(getApplicationContext(),
                                    BasketActivity.class);
                            basketIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(basketIntent);
                            break;
                    }
                    return true;

                }
            };

    private void stashLocalData(){

    }

}
