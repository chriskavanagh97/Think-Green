package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.finalyearapp.R;

public class transport extends AppCompatActivity {

    TextView tip1, tip2, tip3, description, category;
    LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
         background = findViewById(R.id.background);
        tip1 = findViewById(R.id.tip1);
        tip2 = findViewById(R.id.tip2);
        tip3 = findViewById(R.id.tip3);

        description = findViewById(R.id.description);
        category = findViewById(R.id.category);
        Intent i = getIntent();
        String result = i.getStringExtra("result");

        if (result.equals("transport"))
        {
            transport();
        }
        else if(result.equals("home"))
        {
            home();


        }
        else if(result.equals("purchasing"))
        {

            Stuff();


        }

        else if(result.equals("food"))
        {
            food();


        }






    }

    public void food(){

        tip1.setTextColor(Color.YELLOW);


        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                background.setBackgroundResource(R.drawable.publictransporttip);
                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                tip3.setTextColor(Color.BLACK);

            }
        });
        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                background.setBackgroundResource(R.drawable.publictransporttip);
                tip1.setTextColor(Color.YELLOW);
                tip2.setTextColor(Color.BLACK);
                tip3.setTextColor(Color.BLACK);

            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setBackgroundResource(R.drawable.publictransporttip);
                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setTextColor(Color.YELLOW);

            }
        });

    }
    public void Stuff(){
        tip1.setTextColor(Color.YELLOW);


        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                tip3.setTextColor(Color.BLACK);
                background.setBackgroundResource(R.drawable.publictransporttip);



            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setBackgroundResource(R.drawable.publictransporttip);

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setHintTextColor(Color.YELLOW);
                background.setBackgroundResource(R.drawable.publictransporttip);


            }
        });
        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                background.setBackgroundResource(R.drawable.publictransporttip);
                tip1.setTextColor(Color.YELLOW);
                tip2.setTextColor(Color.BLACK);
                tip3.setTextColor(Color.BLACK);

            }
        });

    }
    public void home(){

        tip1.setTextColor(Color.YELLOW);


        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                tip3.setTextColor(Color.BLACK);
                background.setBackgroundResource(R.drawable.publictransporttip);

            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setHintTextColor(Color.YELLOW);
                background.setBackgroundResource(R.drawable.publictransporttip);


            }
        });
        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                background.setBackgroundResource(R.drawable.publictransporttip);
                tip1.setTextColor(Color.YELLOW);
                tip2.setTextColor(Color.BLACK);
                tip3.setTextColor(Color.BLACK);
                background.setBackgroundResource(R.drawable.publictransporttip);


            }
        });


    }
    public void transport(){

        tip1.setTextColor(Color.YELLOW);


        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                background.setBackgroundResource(R.drawable.publictransporttip);
                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.YELLOW);
                tip3.setTextColor(Color.BLACK);

            }
        });
        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                background.setBackgroundResource(R.drawable.cycletip);
                tip1.setTextColor(Color.YELLOW);
                tip2.setTextColor(Color.BLACK);
                tip3.setTextColor(Color.BLACK);

            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tip1.setTextColor(Color.BLACK);
                tip2.setTextColor(Color.BLACK);
                tip3.setTextColor(Color.YELLOW);
                background.setBackgroundResource(R.drawable.publictransporttip);


            }
        });

    }
}
