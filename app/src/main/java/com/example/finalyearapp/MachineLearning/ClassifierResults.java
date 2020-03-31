package com.example.finalyearapp.MachineLearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearapp.R;

public class ClassifierResults extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifier_results);

        Intent intent = getIntent();
        String result = intent.getStringExtra("value");

        if( result.equals("paper"))
        {
            getWindow().setBackgroundDrawableResource(R.drawable.paperrecycle);
        }else if(result.equals("metal")){

            getWindow().setBackgroundDrawableResource(R.drawable.metalrecycle);

        }
        else if(result.equals("glass")){

            getWindow().setBackgroundDrawableResource(R.drawable.glassrecycle);

        }
        else if(result.equals("plastic")){

            getWindow().setBackgroundDrawableResource(R.drawable.plasticrecycle);

        }
        else if(result.equals("trash")){

            getWindow().setBackgroundDrawableResource(R.drawable.trashrecycle);

        }
    }
}
