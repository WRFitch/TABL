package com.example.android.tabl;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BasketActivity extends AppCompatActivity {

    private Button checkoutButton, selectTableButton, addTipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

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

        selectTableButton = findViewById(R.id.selectTableButton);
        selectTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BasketActivity.this, SelectTableActivity.class);
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BasketActivity.this);
                alertDialogBuilder.setTitle("Add a Tip");
                alertDialogBuilder.setCancelable(true);

                final EditText input = new EditText(BasketActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                alertDialogBuilder.setView(input);
                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialogBuilder.setPositiveButton("Add Tip",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                //add
                            }
                        });
                AlertDialog mDialog = alertDialogBuilder.create();
                mDialog.show();
            }
        });


    }


}
