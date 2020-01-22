package com.example.finalyearapp.QuizMenuPackage;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu.OceanQuizMenu;
import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.PollutionQuiz;
import com.example.finalyearapp.QuizMenuPackage.PlasticQuizMenu.Quiz;
import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.pollutionQuizmenu;
import com.example.finalyearapp.R;
import com.example.finalyearapp.ReviewQuestions;

import java.util.ArrayList;



public class QuizMenu extends AppCompatActivity implements QuizAdapter.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<QuizModel> imageModelArrayList;
    private QuizAdapter adapter;

    private int[] myImageList = new int[]{R.drawable.energy, R.drawable.ocean,R.drawable.plastic, R.drawable.pollution,R.drawable.recycle,R.drawable.sun,R.drawable.energy};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);


        Button pollution = (Button) findViewById(R.id.Pollution);
        pollution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoPQuiz = new Intent(QuizMenu.this, pollutionQuizmenu.class);
                startActivity(inttoPQuiz);

            }
        });

        Button plastic = (Button) findViewById(R.id.Plastic);
        plastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(QuizMenu.this, OceanQuizMenu.class);
                startActivity(inttoQuiz);

            }
        });

        Button review = (Button) findViewById(R.id.Review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(QuizMenu.this, Quiz.class);
                startActivity(inttoQuiz);

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = quizzes();
        adapter = new QuizAdapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    private ArrayList<QuizModel> quizzes(){

        ArrayList<QuizModel> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            QuizModel quizModel = new QuizModel();
            quizModel.setImage_drawable(myImageList[i]);
            list.add(quizModel);
        }

        return list;
    }






    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }

    @Override
    public void onClickListener(int posistion) {

        int itemclicked = Integer.parseInt(String.valueOf(imageModelArrayList.get(posistion)));

        if (itemclicked == 1) {


            Intent inttoQuiz = new Intent(QuizMenu.this, ReviewQuestions.class);
            startActivity(inttoQuiz);

        }
        if (itemclicked == 2) {


            Intent inttoQuiz = new Intent(QuizMenu.this, PollutionQuiz.class);
            startActivity(inttoQuiz);
        }
        if (itemclicked == 3) {


            Intent inttoQuiz = new Intent(QuizMenu.this, Quiz.class);
            startActivity(inttoQuiz);
        }
    }
}
