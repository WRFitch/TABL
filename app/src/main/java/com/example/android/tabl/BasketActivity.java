package com.example.android.tabl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasketActivity extends AppCompatActivity {

    private Button checkoutButton, selectTableButton, addTipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        // checkoutbutton will open checkoutActivity
        checkoutButton = findViewById(R.id.CheckoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // selectTableButton will open SelectTableActivity
        selectTableButton = (Button) findViewById(R.id.selectTableButton);
        selectTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BasketActivity.this, SelectTableActivity.class));
            }
        });

        // triggers a dialog box to appear to edit tip amount
        addTipButton = (Button) findViewById(R.id.addTipButton);
        addTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code to open dialog box HERE
            }
        });


    }
}
