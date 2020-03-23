package com.example.finalyearapp.Carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


import java.util.ArrayList;

public class linechartresults extends AppCompatActivity {

    LineChart linechart;
    Button display;
    float score;
    int total = 0;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    ArrayList<Float> dataVals2 = new ArrayList<>();
    ArrayList<Entry> dataVals = new ArrayList<>();

    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String userid = mFirebaseAuth.getCurrentUser().getUid();

        for (total = 0; total < 4; total++) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Carbonfootprint").child(userid).child(String.valueOf(total));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final LineChartresult linechats = dataSnapshot.getValue(LineChartresult.class);
                    score = linechats.getCarbonfootprint();

                    dataVals2.add(score);
                    //float confirm = total.floatValue();
                    Toast.makeText(linechartresults.this, "Display screen activated" + score, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
       /* Toast.makeText(linechartresults.this, "Float array:" + dataVals2  , Toast.LENGTH_SHORT).show();
            linechart = (LineChart) findViewById(R.id.linechart);
            Display();
           /* Intent i = getIntent();
            float[] dataVals2 = i.getFloatArrayExtra("results");
            Toast.makeText(linechartresults.this, "New Array" + dataVals2, Toast.LENGTH_SHORT).show();
            Toast.makeText(linechartresults.this, "Display screen activated" + dataVals2, Toast.LENGTH_SHORT).show();*/

        display = findViewById(R.id.display);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(linechartresults.this, "Float array: on start" + dataVals2, Toast.LENGTH_SHORT).show();
                Toast.makeText(linechartresults.this, "Float array: on start" + total, Toast.LENGTH_SHORT).show();
                linechart = (LineChart) findViewById(R.id.linechart);
                Display();

            }
        });
    }


    public void Display(){


        Toast.makeText(linechartresults.this, "Float array:" + dataVals2  , Toast.LENGTH_SHORT).show();

        dataVals.add(new Entry(0,dataVals2.get(0)));
        dataVals.add(new Entry(1,dataVals2.get(1)));
        dataVals.add(new Entry(2,dataVals2.get(2)));
        dataVals.add(new Entry(3,dataVals2.get(3)));
        /*dataVals.add(new Entry(0,12));
        dataVals.add(new Entry(1,10));
        dataVals.add(new Entry(2,9));
        dataVals.add(new Entry(3,11));*/





        LineDataSet lineDataSet1 = new LineDataSet(dataVals,"data set 1");
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setCircleColor(Color.RED);
        lineDataSet1.setCircleHoleRadius(6);
        lineDataSet1.setColor(Color.BLUE);

        lineDataSets.add(lineDataSet1);


        linechart.setData(new LineData(lineDataSets));
        linechart.setVisibleXRangeMaximum(65f);
        linechart.invalidate();

        float difference = dataVals2.get(3)- dataVals2.get(0);
        float total = dataVals2.get(0) + dataVals2.get(1) + dataVals2.get(2) + dataVals2.get(3);
        float average = total / 4;

        if(difference< 0)
        {

        }

    }
}