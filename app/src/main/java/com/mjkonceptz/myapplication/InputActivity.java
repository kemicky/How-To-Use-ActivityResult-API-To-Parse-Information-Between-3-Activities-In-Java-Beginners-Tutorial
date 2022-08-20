package com.mjkonceptz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    //TODO:  Define Views, Constants and KEYS.
    public static final String EXTRA_NAME = "inputName";
    public static final String EXTRA_SUM = "extraSum";

    private EditText txtNameInput;
    private EditText txtNum1;
    private EditText txtNum2;
    private Button btnGetResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //TODO:  Initialize the  Views.
        txtNameInput = findViewById(R.id.txtNameInput);
        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        btnGetResult = findViewById(R.id.btnGetResult);


        //TODO: Define & Initialize Variables to accept User Inputs from Text Views.
        calculateSum();



    }//onCreate

    private  void  calculateSum() {
        //onClick
        btnGetResult.setOnClickListener(view -> {

            /*TODO: Prepare View To Forward Input Result To
               Activity C(ProcessInputActivity) By Setting Flags To Forward The Result.*/

            // Get the Name Input from the User Here:
            final String nameInput = txtNameInput.getText().toString();

            // Get the number Input from the User Here:
            String sFirstNum =txtNum1.getText().toString();
            String sSecondNum = txtNum2.getText().toString();

            //Convert the Users Number Input Here To An Integer to Handle
            // the Calculation of the Sum pf the Numbers Here:

            int num1 = Integer.parseInt(sFirstNum);
            int num2 = Integer.parseInt(sSecondNum);

            final int sum = num1 + num2;

            //Start a new Intent to foward the result sum and user name to Activity C-ProcessInputActivity.
            Intent intentCalculate = new Intent(InputActivity.this, ProcessInputActivity.class);

            intentCalculate.putExtra(EXTRA_NAME, nameInput);
            intentCalculate.putExtra(EXTRA_SUM, sum);

            //SetFlags forward result here:
            intentCalculate.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intentCalculate);
            finish();






        }); //setOnClickListener

    }//calculateSum

}//end