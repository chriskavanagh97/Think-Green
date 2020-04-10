package com.example.finalyearapp.ClimateChange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearapp.R;

public class Didyouknow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.didyouknow);

        Button findout = findViewById(R.id.findout);
        findout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Didyouknow.this, AnimalMenu.class);
                startActivity(intent);

            }
        });
    }
}
