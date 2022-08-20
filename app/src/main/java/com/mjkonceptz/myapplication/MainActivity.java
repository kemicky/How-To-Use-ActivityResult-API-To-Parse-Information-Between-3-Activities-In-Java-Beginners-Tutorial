package com.mjkonceptz.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

  //TODO: Define Constants To Access ActivityResult API .
    public static final  String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_NAME = "keyName";
    public static final String KEY_ANSWER = "keyAnswer";

    private TextView txtShowName;
    private TextView txtShowResult;

    private  String name;
    private  int answer;


   //TODO: View LifeCycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtShowName  = findViewById(R.id.txtShowName);
        txtShowResult  = findViewById(R.id.txtShowResult);

       Button btnGetResult = findViewById(R.id.btnGetResult);



        btnGetResult.setOnClickListener(view -> {
            //TODO: Call ActivityResult API To Launch.
            mStartForResult.launch(new Intent(MainActivity.this, InputActivity.class));


        });

        //TODO: Call The Activity To Dispatch & Display the Result.
        displayNameAnswer();


    }//onCreate

   @SuppressWarnings("Convert2Lambda")
   ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
           new ActivityResultCallback<ActivityResult>() {
               @Override
               public void onActivityResult(ActivityResult result) {

                   //TODO: Validate If Activity gets the forwarded results from Activity C, Then Handle the Result.
                   if(result.getResultCode() == Activity.RESULT_OK){
                       Intent intent = result.getData();

                       //Assert that intent is  not null and the Activity is accessing the incoming result data.
                       assert intent != null;

                       //Use try-catch for additional validation handling of the result.

                       try{
                           String nameInput = intent.getStringExtra(ProcessInputActivity.EXTRA_DISPLAY_NAME);
                           int sum = intent.getIntExtra(ProcessInputActivity.EXTRA_DISPLAY_SUM,0);

                           // Retrieve and update the new input here by calling the updateNameAndAnswer Method Here:
                           updateNameAndAnswer(nameInput, sum);


                       }catch (Exception e){
                           e.printStackTrace();
                       }//try-catch



                   }//if-Result_OK

               }//onActivityResult

   }); //ActivityResultLauncher



    //TODO: Set The View To Display The Result.
    @SuppressLint("SetTextI18n")
    private void displayNameAnswer(){

        // Dispatch the ActivityResult API here by Displaying the stored result.

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        name = prefs.getString(KEY_NAME, "");
        answer = prefs.getInt(KEY_ANSWER, 0);

        txtShowName.setText("Name: " + name);
        txtShowResult.setText("Input Sum Result: " + answer);


    }//displayNameAnswer()


    //TODO: Set The View To Update  & Save the new result.
    @SuppressLint("SetTextI18n")
    private void updateNameAndAnswer(String newName, int newAnswer){

        // Parse the incoming result tp the Editor here by creating new instance of the input name and sum here:
        name = newName;
        answer = newAnswer;
        txtShowName.setText("Name: " + name);
        txtShowResult.setText("Input Sum Result: " + answer);

        // Store and Save the incoming result data here by using the SharedPreferences Method.
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(KEY_NAME, name);
        editor.putInt(KEY_ANSWER, answer);

        editor.apply();



    }//updateNameAndAnswer(newName, newAnswer)

}//end