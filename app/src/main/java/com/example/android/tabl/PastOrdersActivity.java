package com.example.android.tabl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PastOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_orders);

        // top action bar text is renamed and a 'close' symbol is enabled
        this.getSupportActionBar().setTitle("Past Orders");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
    }
}
