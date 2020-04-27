package com.example.finalyearapp.Maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.Carbonfootprint.StepOne;
import com.example.finalyearapp.MainActivity;
import com.example.finalyearapp.R;
import com.example.finalyearapp.RecycleMaterial.Material;

import java.util.ArrayList;

public class Filterscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterscreen);
        RadioGroup rbGroup;


        Button start = findViewById(R.id.start);
        Spinner mySpinner = (Spinner) findViewById(R.id.values);
        Spinner mySpinner2 = findViewById(R.id.outlets);

        ArrayList<String> cities = new ArrayList<>();

        cities.add("Select County");
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
        ArrayList<String> outlets = new ArrayList<>();

        outlets.add("Select a Recycle station");
        outlets.add("All");
        outlets.add("Bring Bank");
        outlets.add("Lighting Dropoff");
        outlets.add("Civic Amenity Site");
        outlets.add("Electrical Retailers");



        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(Filterscreen.this,
                R.layout.spinner, cities);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter((myAdapter));

        ArrayAdapter<String> myadapter2 = new ArrayAdapter<>(Filterscreen.this,
                R.layout.spinner, outlets);

        myAdapter.setDropDownViewResource(R.layout.spinner);
        mySpinner2.setAdapter((myadapter2));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mySpinner2.getSelectedItem().toString().equals("All")) {


                    String item = mySpinner.getSelectedItem().toString();
                    Intent mapintent = new Intent(Filterscreen.this, MapsActivity.class);
                    mapintent.putExtra("value", "general locations");
                    mapintent.putExtra("city", item);
                    mapintent.putExtra("recycleoutlet", "");
                    startActivity(mapintent);
                }
                else {


                    String item = mySpinner.getSelectedItem().toString();
                    String outlet = mySpinner2.getSelectedItem().toString();
                    Intent mapintent = new Intent(Filterscreen.this, MapsActivity.class);
                    mapintent.putExtra("value", "specified outlet");
                    mapintent.putExtra("city", item);
                    mapintent.putExtra("recycleoutlet", outlet);
                    startActivity(mapintent);
                }

            }


        });

        Button favourite = findViewById(R.id.favourite);
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mapintent = new Intent(Filterscreen.this, MapsActivity.class);
                mapintent.putExtra("value", "favourites");
                startActivity(mapintent);



            }
        });
    }
}
