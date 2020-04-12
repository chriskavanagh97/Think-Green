package com.example.finalyearapp.QuizMenuPackage;


import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.example.finalyearapp.R;


import java.util.ArrayList;



public class QuizMenu extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private ArrayList<QuizModel> imageModelArrayList;
    private QuizAdapter adapter;

    private int[] myImageList = new int[]{ R.drawable.ocean,R.drawable.plastic, R.drawable.pollution,R.drawable.recycle};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);




        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = quizzes();
        adapter = new QuizAdapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    private ArrayList<QuizModel> quizzes(){

        ArrayList<QuizModel> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            QuizModel quizModel = new QuizModel();
            quizModel.setImage_drawable(myImageList[i]);

             if(i == 0)
            {
                quizModel.setName("ocean");
            }
            else if(i == 1)
            {
                quizModel.setName("plastic");
            }
            else if(i == 2)
            {
                quizModel.setName("pollution");
            }
            else if(i == 3)
            {
                quizModel.setName("recycle");
            }

            list.add(quizModel);
        }

        return list;
    }






    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }


}
