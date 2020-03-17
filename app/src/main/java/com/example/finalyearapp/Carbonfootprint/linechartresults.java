package com.example.finalyearapp.Carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.finalyearapp.Question;
import com.example.finalyearapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class linechartresults extends AppCompatActivity {

    LineChart linechart;
    ArrayList<Entry> dataVals = new ArrayList<>();
    int total =1;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

         linechart= (LineChart) findViewById(R.id.linechart);

         if (total>5 )
         {

         }else {


             DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Questions").child("Pollution").child(String.valueOf(total));
             databaseref.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                     final LineChartresult linechats = dataSnapshot.getValue(LineChartresult.class);
                      score = linechats.getCarbonfootprint();
                     dataVals.add(new Entry(0, 15));
                     dataVals.add(new Entry(1, 18));
                     dataVals.add(new Entry(2, 12));
                     dataVals.add(new Entry(3, 10));
                     dataVals.add(new Entry(4, 17));

                     total++;
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             });

         }




        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(dataVals,"data set 1");
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setCircleColor(Color.RED);
        lineDataSet1.setCircleHoleRadius(6);
        lineDataSet1.setColor(Color.BLUE);

        lineDataSets.add(lineDataSet1);


        linechart.setData(new LineData(lineDataSets));
        linechart.setVisibleXRangeMaximum(65f);
        linechart.invalidate();

    }
}
