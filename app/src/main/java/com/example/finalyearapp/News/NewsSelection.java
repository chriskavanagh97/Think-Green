package com.example.finalyearapp.News;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finalyearapp.R;

public class NewsSelection extends AppCompatActivity {

    ImageView customize, latest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_selection);

        customize = findViewById(R.id.imageView);
        latest = findViewById(R.id.imageView2);

        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsSelection.this, ArticlesActivity.class);
                startActivity(intent);

            }
        });

        latest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsSelection.this, ArticlesActivity.class);
                startActivity(intent);

            }
        });
    }
}
