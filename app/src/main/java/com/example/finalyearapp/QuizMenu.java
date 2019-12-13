package com.example.finalyearapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);

        Button pollution = (Button) findViewById(R.id.Pollution);
        pollution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoPQuiz = new Intent(QuizMenu.this, PollutionQuiz.class);
                startActivity(inttoPQuiz);

            }
        });

        Button plastic = (Button) findViewById(R.id.Plastic);
        plastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(QuizMenu.this, Quiz.class);
                startActivity(inttoQuiz);

            }
        });

        Button review = (Button) findViewById(R.id.Review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(QuizMenu.this, ReviewQuestions.class);
                startActivity(inttoQuiz);

            }
        });


    }

    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }

}
