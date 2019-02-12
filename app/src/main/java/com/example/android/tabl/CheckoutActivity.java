package com.example.android.tabl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class CheckoutActivity extends AppCompatActivity {

    private Button cancelOrderButton, HelpWithOrderButton, addTipButton;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Cancel Order button will open BasketActivity
        cancelOrderButton = findViewById(R.id.CancelOrderButton);
        cancelOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

// NEED TO MAKE HELP ACTIVITY AND CLASS SO I CAN LINK THIS BUTTON TO IT, LINKED IT TO 'MORE' PAGE FOR NOW
        HelpWithOrderButton = findViewById(R.id.HelpWithOrderButton);
        HelpWithOrderButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MoreActivity.class); //HERE^
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    });



}
}

        
