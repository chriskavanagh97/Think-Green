package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.PollutionQuiz;
import com.example.finalyearapp.QuizMenuPackage.QuizResults;
import com.example.finalyearapp.R;

public class StepThree extends AppCompatActivity {

    int i = 1;
    Spinner mySpinner;
    TextView text;
    int household;
    int clothes;
    int beauty;
    int entertainment;

    EditText answers;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_three);

        text = findViewById(R.id.textView);
        mySpinner = (Spinner) findViewById(R.id.values);

        myDialog = new Dialog(this);
        answers = (EditText) findViewById(R.id.answers);

        Calculator();

    }

    public void Calculator()
    {


        if(i >4) {

            parsing();



        }
        else {

            if (i == 1) {
                String message = getString(R.string.stuffq1);
                text.setText(message);




            }
            if (i == 2) {
                String message = getString(R.string.stuffq2);
                text.setText(message);


            }

            if (i == 3) {
                String message = getString(R.string.stuffq3);
                text.setText(message);


            }
            if (i == 4) {
                String message = getString(R.string.stuffq4);
                text.setText(message);


            }




            Button submit = (Button) findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(i == 1){

                        household = Integer.parseInt(answers.getText().toString());
                        i = i + 1;
                        Calculator();



                    }
                    else if(i == 2) {

                        i = i + 1;
                        clothes = Integer.parseInt(answers.getText().toString());
                        Calculator();


                    }
                    else if(i == 3)
                    {

                        beauty = Integer.parseInt(answers.getText().toString());
                        i = i + 1;
                        Calculator();
                    }
                    else if(i == 4) {

                        entertainment = Integer.parseInt(answers.getText().toString());
                        i = i + 1;
                        Calculator();
                    }




            }



    });
}
    }

    public void parsing(){

        Intent i = getIntent();

        String mpg = i.getStringExtra("mpg");
        String miles = i.getStringExtra("miles");
        String bushours = i.getStringExtra("train");
        String trainhours = i.getStringExtra("bus");


        Intent resultintent = new Intent(StepThree.this, StepFour.class);
        resultintent.putExtra("beauty",String.valueOf(beauty));
        resultintent.putExtra("clothes",String.valueOf(clothes));
        resultintent.putExtra("entertainment",String.valueOf(entertainment));
        resultintent.putExtra("house",String.valueOf(household));

        resultintent.putExtra("mpg",String.valueOf(mpg));
        resultintent.putExtra("miles",String.valueOf(miles));
        resultintent.putExtra("train",String.valueOf(trainhours));
        resultintent.putExtra("bus",String.valueOf(bushours));

        startActivity(resultintent);

    }
}
