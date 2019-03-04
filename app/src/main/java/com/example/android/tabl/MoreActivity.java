package com.example.android.tabl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.android.tabl.utils.TablUtils;


public class MoreActivity extends AppCompatActivity {

   private Button ongoingOrdersButton, pastOrdersButton, helpButton;
   private Switch switchDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        // top action bar text is renamed and a 'close' symbol is enabled
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

        // ongoingOrdersButton will open CheckoutActivity
        ongoingOrdersButton = (Button) findViewById(R.id.ongoingOrdersButton);
        ongoingOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MoreActivity.this, CheckoutActivity.class));
                    openCheckoutPage();
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
                // code that will open help page HERE
                openHelp();
            }
        });

        // DARK MODE SWITCH
        switchDarkMode = (Switch) findViewById(R.id.switchDarkMode);
        switchDarkMode.setChecked(false);
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //changing themes programmatically is really hard
                if(isChecked){
                    TablUtils.errorMsg(buttonView, "I AM THE NIGHT");
                }else{
                    TablUtils.errorMsg(buttonView, "I am no longer the night");
                }
            }
        });
    }

    public void openHelp(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void openCheckoutPage(){
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }
}
