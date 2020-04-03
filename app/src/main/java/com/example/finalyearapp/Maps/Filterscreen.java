package com.example.finalyearapp.Maps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.finalyearapp.R;

import java.util.ArrayList;

public class Filterscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterscreen);


        TextView city;

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
    }
}
