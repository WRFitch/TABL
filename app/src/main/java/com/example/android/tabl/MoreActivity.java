package com.example.android.tabl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MoreActivity extends AppCompatActivity {

   private Button ongoingOrdersButton, pastOrdersButton, helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        // top action bar text is renamed and a 'close' symbol is enabled
        this.getSupportActionBar().setTitle("More");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

        // ongoingOrdersButton will open CheckoutActivity
        ongoingOrdersButton = (Button) findViewById(R.id.ongoingOrdersButton);
        ongoingOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this, CheckoutActivity.class));
            }
        });

        // pastOrdersButton will open PastOrdersActivity
        pastOrdersButton = (Button) findViewById(R.id.pastOrdersButton);
        pastOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this, PastOrdersActivity.class));
            }
        });

        // helpButton will open Help Page
        helpButton = (Button) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelp();

                // code that will open help page HERE
            }
        });
    }

    public void openHelp(){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
}
