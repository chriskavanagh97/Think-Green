package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.PollutionQuiz;
import com.example.finalyearapp.QuizMenuPackage.QuizResults;
import com.example.finalyearapp.R;

public class StepTwo extends AppCompatActivity {

    EditText textanswer;
    int i = 1;
    Spinner mySpinner;
    TextView text;
    ImageView image;
    int mpg;
    int miles;
    int bushours;
    int trainhours;
    int flightmiles;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);



        text = findViewById(R.id.textView);
        mySpinner = (Spinner) findViewById(R.id.values);

        myDialog = new Dialog(this);
        image = findViewById(R.id.graphic);


        Calculator();

    }

    public void Calculator()
    {


        if(i >5) {

            parsing();



        }
        else {

            if (i == 1) {
                String message = getString(R.string.travelq1);
                text.setText(message);


                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.travel));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));

            }
            if (i == 2) {
                String message = getString(R.string.travelq2);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.travel2));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }

            if (i == 3) {
                String message = getString(R.string.travelq3);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.travel3));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));

            }
            if (i == 4) {
                String message = getString(R.string.travelq4);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.travel4));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if (i == 5) {
                String message = getString(R.string.travelq5);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.travel5));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }



            Button submit = (Button) findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

/*
===========================================================================================================
QUESTION ONE ANSWERS
===========================================================================================================
 */
                    if(mySpinner.getSelectedItem().equals("Neither - I cycle , walk or use public transport")){

                        i = 4;
                        Calculator();

                    }
                    else if(mySpinner.getSelectedItem().equals("motor bike"))
                    {

                        i = 3;
                        mpg = 25;
                        Calculator();

                    }
/*
===========================================================================================================
QUESTION TWO ANSWERS
===========================================================================================================
 */

                    else if(i == 2 & mySpinner.getSelectedItem().equals("Electric car"))
                    {

                        mpg = 0;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 2 & mySpinner.getSelectedItem().equals("Plug-in Hybrid car")) {

                        mpg = 160;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 2 & mySpinner.getSelectedItem().equals("Hybrid car")) {

                        mpg = 120;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 2 & mySpinner.getSelectedItem().equals("small petrol or diesel car")) {

                        mpg = 75;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 2 & mySpinner.getSelectedItem().equals("medium petrol or diesel car")) {

                        mpg = 65;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 2 & mySpinner.getSelectedItem().equals("large petrol or diesel car")) {

                        mpg = 35;
                        i = i + 1;
                        Calculator();
                    }

/*
===========================================================================================================
QUESTION THREE ANSWERS
===========================================================================================================
 */

                    else if(i == 3 & mySpinner.getSelectedItem().equals("0 to 120km")) {

                        miles = 50;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 3 & mySpinner.getSelectedItem().equals("120km to 250km")) {

                        miles = 190;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 3 & mySpinner.getSelectedItem().equals("250km to 370km")) {

                        miles = 310;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 3 & mySpinner.getSelectedItem().equals("370km to 500km")) {

                        miles = 450;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 3 & mySpinner.getSelectedItem().equals("500+")) {

                        miles = 600;
                        i = i + 1;
                        Calculator();
                    }
/*
===========================================================================================================
QUESTION FOUR ANSWERS
===========================================================================================================
 */

                    else if(i == 4 & mySpinner.getSelectedItem().equals("I dont travel by train")) {

                        trainhours = 0;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 4 & mySpinner.getSelectedItem().equals("under 2 hours")) {

                        trainhours = 85;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 4 & mySpinner.getSelectedItem().equals("2 to 5 hours")) {

                        trainhours = 195;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 4 & mySpinner.getSelectedItem().equals("5 to 15 hours")) {

                        trainhours = 650;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 4 & mySpinner.getSelectedItem().equals("15 to 25 hours")) {

                        trainhours = 1300;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 4 & mySpinner.getSelectedItem().equals("over 25 hours")) {

                        trainhours = 2600;
                        i = i + 1;
                        Calculator();
                    }

/*
===========================================================================================================
QUESTION FIVE ANSWERS
===========================================================================================================
 */


                    else if(i == 5 & mySpinner.getSelectedItem().equals("I dont travel by train")) {

                        bushours = 0;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 5 & mySpinner.getSelectedItem().equals("under 1 hour")) {

                        bushours = 17;
                        i = i + 1;
                        Calculator();
                    }

                    else if(i == 5 & mySpinner.getSelectedItem().equals("1 to 3 hours")) {

                        bushours = 34;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 5 & mySpinner.getSelectedItem().equals("3 to 6 hours")) {

                        bushours = 85;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 5 & mySpinner.getSelectedItem().equals("6 to 10 hours")) {

                        bushours = 136;
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 5 & mySpinner.getSelectedItem().equals("over 10 hours")) {

                        bushours = 225;
                        i = i + 1;
                        Calculator();
                    }


                    else {
                        i = i + 1;
                        Calculator();

                    }
                }
            });


        }
    }
    public void parsing() {

        Intent resultintent = new Intent(StepTwo.this, stepTwoii.class);
        resultintent.putExtra("mpg",String.valueOf(mpg));
        resultintent.putExtra("miles",String.valueOf(miles));
        resultintent.putExtra("train",String.valueOf(trainhours));
        resultintent.putExtra("bus",String.valueOf(bushours));


        startActivity(resultintent);

    }

}
