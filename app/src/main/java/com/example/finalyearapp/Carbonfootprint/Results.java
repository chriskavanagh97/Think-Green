package com.example.finalyearapp.Carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalyearapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        PieChart pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(false);


        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(52f, "transport"));
        value.add(new PieEntry(12f, "Purchasing"));
        value.add(new PieEntry(17f, "food"));
        value.add(new PieEntry(29f, "home"));

        PieDataSet pieDataSet = new PieDataSet(value, "Category values");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);


    }
}
