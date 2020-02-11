package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.R;

public class StepTwo extends AppCompatActivity {


    int i = 1;
    Spinner mySpinner;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_two);

        text = findViewById(R.id.textView);
        mySpinner = (Spinner) findViewById(R.id.values);
        Calculator();

    }

    public void Calculator()
    {


        if(i >7) {
            Toast.makeText(StepTwo.this, "finito" , Toast.LENGTH_SHORT).show();


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
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.foodcosts));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }

            if (i == 3) {
                String message = getString(R.string.travelq3);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.localfood));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if (i == 4) {
                String message = getString(R.string.travelq4);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.localfood));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if (i == 5) {
                String message = getString(R.string.travelq5);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.localfood));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if (i == 6) {
                String message = getString(R.string.travelq6);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepTwo.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.localfood));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }



            Button submit = (Button) findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(StepTwo.this, "submitted", Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    Calculator();
                }
            });


        }
    }
}
