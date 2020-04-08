package com.example.finalyearapp.ClimateChange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.finalyearapp.R;

public class AnimalView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_view);
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");

        String uri = "@drawable/" + name;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);

        getWindow().setBackgroundDrawableResource(imageResource);
}
}
