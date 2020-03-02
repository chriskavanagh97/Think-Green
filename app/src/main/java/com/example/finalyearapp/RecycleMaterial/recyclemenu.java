package com.example.finalyearapp.RecycleMaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalyearapp.MachineLearning.CameraConnectionFragment;
import com.example.finalyearapp.MachineLearning.ClassifierActivity;
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

        ImageView classification = (ImageView) findViewById(R.id.imageclassification);
        classification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(recyclemenu.this, ClassifierActivity.class);
                startActivity(intent);

            }
        });
        ImageView recycle = (ImageView) findViewById(R.id.recycle);
        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intomaterial = new Intent(recyclemenu.this, Materialview.class);


                intomaterial.putExtra("name", name);
                startActivity(intomaterial);




            }
        });
    }
}
