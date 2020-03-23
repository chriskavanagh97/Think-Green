package com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalyearapp.QuizMenuPackage.PlasticQuizMenu.Quiz;
import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.PollutionQuiz;
import com.example.finalyearapp.QuizMenuPackage.RecycleQuiz;
import com.example.finalyearapp.R;

public class OceanQuizMenu extends AppCompatActivity {

    String Level1 = "1";
    String Level2 = "6";
    String Level3 = "11";
    String quiztype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean_quiz_menu);

        ImageView buttonOne, buttonTwo, buttonThree;

        buttonOne = (ImageView) findViewById(R.id.ButtonOne);
        buttonTwo = (ImageView) findViewById(R.id.ButtonTwo);
        buttonThree = (ImageView) findViewById(R.id.ButtonThree);

        Intent intent = getIntent();
        quiztype = intent.getStringExtra("name");

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quiztype.equals("pollution")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level1);
                    startActivity(intent);
                }
                else if (quiztype.equals("plastic")) {
                    Intent intent = new Intent(OceanQuizMenu.this, Quiz.class);

                    intent.putExtra("quiznum", Level1);
                    startActivity(intent);
                }
                else if (quiztype.equals("energy")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level1);
                    startActivity(intent);
                }
                else if (quiztype.equals("sun")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level1);
                    startActivity(intent);
                }
                else if (quiztype.equals("ocean")) {
                    Intent intent = new Intent(OceanQuizMenu.this, Oceanquizz.class);

                    intent.putExtra("quiznum", Level1);
                    startActivity(intent);
                }
                else if (quiztype.equals("recycle")) {
                    Intent intent = new Intent(OceanQuizMenu.this, RecycleQuiz.class);

                    intent.putExtra("quiznum", Level1);
                    startActivity(intent);
                }





            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (quiztype.equals("pollution")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level2);
                    startActivity(intent);
                }
                else if (quiztype.equals("plastic")) {
                    Intent intent = new Intent(OceanQuizMenu.this, Quiz.class);

                    intent.putExtra("quiznum", Level2);
                    startActivity(intent);
                }
                else if (quiztype.equals("energy")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level2);
                    startActivity(intent);
                }
                else if (quiztype.equals("sun")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level2);
                    startActivity(intent);
                }
                else if (quiztype.equals("ocean")) {
                    Intent intent = new Intent(OceanQuizMenu.this, Oceanquizz.class);

                    intent.putExtra("quiznum", Level2);
                    startActivity(intent);
                }
                else if (quiztype.equals("recycle")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level2);
                    startActivity(intent);
                }

            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (quiztype.equals("pollution")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level3);
                    startActivity(intent);
                }
                else if (quiztype.equals("plastic")) {
                    Intent intent = new Intent(OceanQuizMenu.this, Quiz.class);

                    intent.putExtra("quiznum", Level3);
                    startActivity(intent);
                }
                else if (quiztype.equals("energy")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level3);
                    startActivity(intent);
                }
                else if (quiztype.equals("sun")) {
                    Intent intent = new Intent(OceanQuizMenu.this, Oceanquizz.class);

                    intent.putExtra("quiznum", Level3);
                    startActivity(intent);
                }
                else if (quiztype.equals("ocean")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level3);
                    startActivity(intent);
                }
                else if (quiztype.equals("recycle")) {
                    Intent intent = new Intent(OceanQuizMenu.this, PollutionQuiz.class);

                    intent.putExtra("quiznum", Level3);
                    startActivity(intent);
                }

            }
        });
    }
}
