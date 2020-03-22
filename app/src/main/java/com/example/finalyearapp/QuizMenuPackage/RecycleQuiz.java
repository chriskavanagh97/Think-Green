package com.example.finalyearapp.QuizMenuPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalyearapp.QuizMenuPackage.PlasticQuizMenu.Quiz;
import com.example.finalyearapp.R;

public class RecycleQuiz extends AppCompatActivity {

    TextView question;
    ImageView picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_quiz);

        Quiz();
        picture = findViewById(R.id.imageView6);
        question = findViewById(R.id.question);
    }

    public void Quiz(){

        for(int i =0; i < 11; i++)
        {
            if(i == 0)
            {
                picture.setImageResource(R.drawable.aluminium);




            }

        }

    }
}
