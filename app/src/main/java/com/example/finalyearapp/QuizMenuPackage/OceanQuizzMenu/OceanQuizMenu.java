package com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalyearapp.R;

public class OceanQuizMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean_quiz_menu);

        ImageView buttonOne, buttonTwo, buttonThree;

        buttonOne = (ImageView) findViewById(R.id.ButtonOne);
        buttonTwo = (ImageView) findViewById(R.id.ButtonTwo);
        buttonThree = (ImageView) findViewById(R.id.ButtonThree);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int questionnumber = 1;
                Intent intent = new Intent(OceanQuizMenu.this, Oceanquizz.class);

                intent.putExtra("quiznum", questionnumber);
                startActivity(intent);


            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int questionnumber = 4;
                Intent intent = new Intent(OceanQuizMenu.this, Oceanquizz.class);

                intent.putExtra("quiznum", questionnumber);
                startActivity(intent);

            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int questionnumber = 7;
                Intent intent = new Intent(OceanQuizMenu.this, Oceanquizz.class);

                intent.putExtra("quiznum", questionnumber);
                startActivity(intent);

            }
        });
    }
}
