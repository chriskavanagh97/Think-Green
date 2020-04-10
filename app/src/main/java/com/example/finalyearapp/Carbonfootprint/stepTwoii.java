package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalyearapp.R;

public class stepTwoii extends AppCompatActivity {

    int airshort;
    int airmedium;
    int airlong;
    int airextended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steptwoii);


        TextView one = findViewById(R.id.one);
        TextView two = findViewById(R.id.two);
        TextView three = findViewById(R.id.three);
        TextView four = findViewById(R.id.four);
        TextView five = findViewById(R.id.five);
        TextView six = findViewById(R.id.six);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                airshort = Integer.parseInt(one.getText().toString()) +  Integer.parseInt(two.getText().toString());
                airmedium = Integer.parseInt(three.getText().toString());
                airlong = Integer.parseInt(four.getText().toString()) + Integer.parseInt(five.getText().toString());
                airextended = Integer.parseInt(six.getText().toString());
                parsing();


            }
        });
    }

    public void parsing() {

        Intent i = getIntent();

        String mpg = i.getStringExtra("mpg");
        String miles = i.getStringExtra("miles");
        String bushours = i.getStringExtra("train");
        String trainhours = i.getStringExtra("bus");


        Intent resultintent = new Intent(stepTwoii.this, StepThree.class);
        resultintent.putExtra("mpg",String.valueOf(mpg));
        resultintent.putExtra("miles",String.valueOf(miles));
        resultintent.putExtra("train",String.valueOf(trainhours));
        resultintent.putExtra("bus",String.valueOf(bushours));
        resultintent.putExtra("short",String.valueOf(airshort));
        resultintent.putExtra("medium",String.valueOf(airmedium));
        resultintent.putExtra("long",String.valueOf(airlong));
        resultintent.putExtra("extended",String.valueOf(airextended));


        startActivity(resultintent);

    }
}
