package com.example.finalyearapp.Maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.MainActivity;
import com.example.finalyearapp.R;
import com.example.finalyearapp.RecycleMaterial.Material;

import java.util.ArrayList;

public class Filterscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterscreen);


        TextView city = findViewById(R.id.city);
        Button start = findViewById(R.id.start);

        ArrayList<String> cities = new ArrayList<>();

        cities.add("Carlow");
        cities.add("Cavan");
        cities.add("Clare");
        cities.add("Cork");
        cities.add("Dublin");
        cities.add("Galway");
        cities.add("Donegal");
        cities.add("Kerry");
        cities.add("Kildare");
        cities.add("Kilkenny");
        cities.add("Laois");
        cities.add("Louth");
        cities.add("Leitrim");
        cities.add("Limerick");
        cities.add("Longford");
        cities.add("Mayo");
        cities.add("Meath");
        cities.add("Offaly");
        cities.add("Roscommon");
        cities.add("Sligo");
        cities.add("Tipperary");
        cities.add("Waterford");
        cities.add("Westmeath");
        cities.add("Wexford");
        cities.add("Wicklow");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cityval = city.getText().toString().toLowerCase();

                for (String item : cities) {

                    if (item.toLowerCase().equals(cityval.toLowerCase())) {

                        Toast.makeText(Filterscreen.this, "Correct city found" , Toast.LENGTH_SHORT).show();


                        Intent mapintent = new Intent(Filterscreen.this, MapsActivity.class);
                        mapintent.putExtra("value", "false");
                        mapintent.putExtra("city", item);
                        startActivity(mapintent);


                    }
                    else {
                        Toast.makeText(Filterscreen.this, "It seems you misspelled the county" , Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }
}
