package com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu.OceanQuizMenu;
import com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu.Oceanquizz;
import com.example.finalyearapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

public class pollutionQuizmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution_quizmenu);
        ImageView buttonOne, buttonTwo, buttonThree;

        buttonOne = (ImageView) findViewById(R.id.ButtonOne);
        buttonTwo = (ImageView) findViewById(R.id.ButtonTwo);
        buttonThree = (ImageView) findViewById(R.id.ButtonThree);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int questionnumber = 1;
                Intent intent = new Intent(pollutionQuizmenu.this, PollutionQuiz.class);

                intent.putExtra("quiznum", String.valueOf(questionnumber));
                startActivity(intent);


            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int questionnumber = 4;
                Intent intent = new Intent(pollutionQuizmenu.this, PollutionQuiz.class);

                intent.putExtra("quiznum", String.valueOf(questionnumber));
                startActivity(intent);

            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int questionnumber = 7;
                Intent intent = new Intent(pollutionQuizmenu.this, PollutionQuiz.class);

                intent.putExtra("quiznum", String.valueOf(questionnumber));
                startActivity(intent);

            }
        });
    }

}
