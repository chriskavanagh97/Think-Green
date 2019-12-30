package com.example.finalyearapp.RecycleMaterial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalyearapp.MainMenu;
import com.example.finalyearapp.QuizMenu;
import com.example.finalyearapp.R;




public class recyclemenu extends AppCompatActivity {

    String name = "aerosols";

    public static final String KEY1="com.example.MESSAGE1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclemenu);


        ImageView quiz = (ImageView) findViewById(R.id.Quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(recyclemenu.this, recyclewhat.class);
                startActivity(inttoQuiz);

            }
        });

        ImageView recycle = (ImageView) findViewById(R.id.recycle);
        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intomaterial = new Intent(recyclemenu.this, Materialview.class);


                intomaterial.putExtra("title", name);
                startActivity(intomaterial);




            }
        });
    }
}
