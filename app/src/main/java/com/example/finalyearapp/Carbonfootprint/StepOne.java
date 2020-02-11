package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.MainActivity;
import com.example.finalyearapp.R;
import com.google.android.gms.common.util.Strings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class StepOne extends AppCompatActivity {

    int i = 1;
    Spinner mySpinner;
    TextView text;

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one);

        text = findViewById(R.id.textView);
        mySpinner = (Spinner) findViewById(R.id.values);
Calculator();





    }

    public void Calculator()
    {


        if(i >3) {
            Toast.makeText(StepOne.this, "finito" , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(StepOne.this, StepTwo.class);
            startActivity(intent);

        }
        else {

            if(i == 1)
            {


                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepOne.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.foodvalues));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }
            if(i == 2)
            {
                String message = getString(R.string.foodq2);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepOne.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.foodcosts));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }

            if(i == 3)
            {
                String message = getString(R.string.foodq3);
                text.setText(message);

                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepOne.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.localfood));

                myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter((myAdapter));
            }

            Button submit = (Button) findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(StepOne.this, "submitted" , Toast.LENGTH_SHORT).show();
                    i = i + 1;
                    Calculator();
                }
            });



        }
    }
}
