package com.example.finalyearapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class QuizMenu extends AppCompatActivity implements QuizAdapter.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<QuizModel> imageModelArrayList;
    private QuizAdapter adapter;

    private int[] myImageList = new int[]{R.drawable.energy, R.drawable.ocean,R.drawable.plastic, R.drawable.pollution,R.drawable.recycle,R.drawable.sun,R.drawable.energy};
    private String[] myImageNameList = new String[]{"Energy","Ocean" ,"Plastic","Pollution","Recycle","Sun","Energy"};


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

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = quizzes();
        adapter = new QuizAdapter(this, imageModelArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    private ArrayList<QuizModel> quizzes(){

        ArrayList<QuizModel> list = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            QuizModel quizModel = new QuizModel();
            quizModel.setName(myImageNameList[i]);
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
