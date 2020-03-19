package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalyearapp.R;

public class transport extends AppCompatActivity {

    TextView tip1, tip2, tip3, description, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        tip1 = findViewById(R.id.tip1);
        tip2 = findViewById(R.id.tip2);
        tip3 = findViewById(R.id.tip3);

        description = findViewById(R.id.description);
        category = findViewById(R.id.category);

        tip1.setTextColor(Color.YELLOW);
        description.setText("The most eco-friendly mode of transport is cycling, it creates no emission, uses no resources and keeps you fit and healthy. And if all that doesn’t sway your opinion - you will save money while helping save the environment.\n" +
                "\n" +
                "By cycling more, you are also taking cars off the road that would otherwise cause congestion and pollution.");
        category.setText("CYCLE");

    }
}
