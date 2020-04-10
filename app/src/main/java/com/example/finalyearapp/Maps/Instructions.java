package com.example.finalyearapp.Maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.finalyearapp.MachineLearning.ClassifierActivity;
import com.example.finalyearapp.R;

public class Instructions extends AppCompatActivity {

    Button next;
    int loopvalue =0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        loop();


    }

    public void loop()
    {
        Intent intent = getIntent();
        loopvalue = intent.getIntExtra("value", 0);

        if(loopvalue == 3) {

            if (i == 5) {
                intent();

            } else {

                ConstraintLayout background;
                background = findViewById(R.id.background);


                if (i == 0) {
                    background.setBackgroundResource(R.drawable.mapsinstruction);

                } else if (i == 1) {
                    background.setBackgroundResource(R.drawable.mapinstruction1);
                } else if (i == 2) {
                    background.setBackgroundResource(R.drawable.mapsinstruction3);
                }
                else if (i == 3) {
                    background.setBackgroundResource(R.drawable.menuaccess);
                }
                else if (i == 4) {
                    background.setBackgroundResource(R.drawable.menufunctions);
                }



                next = findViewById(R.id.next);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i++;
                        loop();

                    }
                });


            }
        }
        else if(loopvalue == 2){

            if (i == 2) {
                intentCam();

            } else {

                ConstraintLayout background;
                background = findViewById(R.id.background);


                if (i == 0) {
                    background.setBackgroundResource(R.drawable.caminstruction1);

                } else if (i == 1) {
                    background.setBackgroundResource(R.drawable.caminstruction2);
                }


                next = findViewById(R.id.next);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        i++;
                        loop();

                    }
                });


            }

        }

}





    public void intent(){

        Intent intent = new Intent(Instructions.this, Filterscreen.class);
        intent.putExtra("value", "false");

        startActivity(intent);
    }
    public void intentCam(){

        Intent intent = new Intent(Instructions.this, ClassifierActivity.class);

        startActivity(intent);
    }
}

