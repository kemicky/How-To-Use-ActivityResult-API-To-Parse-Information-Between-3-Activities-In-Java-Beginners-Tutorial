package com.mjkonceptz.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProcessInputActivity extends AppCompatActivity {

    //TODO: Define & Initialize Keys/Tokens.
    public static final String EXTRA_DISPLAY_NAME = "displayName";
    public  static final String EXTRA_DISPLAY_SUM = "displaySum";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_input);

        //TODO: Get Result Forwarded From Activity B(InputActivity).
        Intent intent = getIntent();

        String nameInput = intent.getStringExtra(InputActivity.EXTRA_NAME);
        int sum = intent.getIntExtra(InputActivity.EXTRA_SUM, 0);


        /*TODO: Use the Result From Activity B (InputActivity) To Display
           Some Information In Activity C(ProcessInput).*/

        TextView txtDisplayName = findViewById(R.id.txtDisplayName);

        TextView txtDisplaySumResult = findViewById(R.id.txtDisplaySumResult);

        // Display information in this Activity before parsing or forwarding
        // the needed result to Activity A-(MainActivity)
        txtDisplayName.setText("Name: " + nameInput);

        txtDisplaySumResult.setText("The Sum of  Num1 and Num2: " + sum);


        //TODO: Send The Result Received From Activity B(InputActivity)  Back To A(MainActivity).
        Button btnSendResult = findViewById(R.id.btnSendResult);
        btnSendResult.setOnClickListener(view -> {

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_DISPLAY_NAME, nameInput);
            resultIntent.putExtra(EXTRA_DISPLAY_SUM, sum);
            setResult(RESULT_OK, resultIntent);
            finish();

        }); //setOnClickListener



    }//onCreate



}//end_class