package com.example.finalyearapp.Carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class linechartresults extends AppCompatActivity {


    GraphView graph;


    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);


        graph = findViewById(R.id.linechart);


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());

    }

    private DataPoint[] getDataPoint() {

        DataPoint[] dp = new DataPoint[]
                {
                        new DataPoint(0,1),
                        new DataPoint(2,5),
                        new DataPoint(3,1),
                        new DataPoint(5,6),
                        new DataPoint(8,3),


       /* if (difference < 0 & average < nationalaverage) {
            results.setText("Congratulations your footprint is improving you have lowered you footprint by: " + differencepositive + "\n" +
                    "Your average result is: " + average + " Keep up the good work" + "\n" +
                    "Your average is  " + posave + " lower than the national average " + " Way to lead by example ");

        } else {
            results.setText("Your footprint has increased by " + difference + " from your first test this is not good " + "\n" +
                    " Your average result is: " + average + " Please revisit our tips section to lower your footprint" + "\n" +
                    "your average is  " + (average - nationalaverage) + " ");

        }*/


    };
        return (dp);
}
}