package com.example.android.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        final Button findRestaurantButton = findViewById(R.id.findRestaurantButton);
        findRestaurantButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //begin findRestaurant activity (executes on main thread)
                //(try to keep activity stack as small as possible)
            }
        });
    }
}
