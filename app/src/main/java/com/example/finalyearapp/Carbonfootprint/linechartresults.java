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

import com.example.finalyearapp.MainActivity;
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
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class linechartresults extends AppCompatActivity {


    GraphView graph;


    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String userid = mFirebaseAuth.getCurrentUser().getUid();

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d ");

        graph = findViewById(R.id.linechart);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getDataPoint());
        graph.addSeries(series);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(12);
        graph.getViewport().setMaxX(6);
        graph.getGridLabelRenderer().setNumHorizontalLabels(3);




        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(new Date((long)value));
                }else {
                    return super.formatLabel(value, isValueX);

                }

            }
        });
        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Carbonfootprint").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        DataPoint[] dp = new DataPoint[((int) dataSnapshot.getChildrenCount())];
                                                        int index = 0;

                                                        for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
                                                            LineChartresult values = myDataSnapshot.getValue(LineChartresult.class);

                                                            dp[index] = new DataPoint(values.getxValue(), values.getyValue());
                                                            index++;
                                                            Toast.makeText(linechartresults.this, "children count is" + dp.length, Toast.LENGTH_SHORT).show();


                                                        }
                                                        series.resetData(dp);
                                                    }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private DataPoint[] getDataPoint() {

        DataPoint[] dp = new DataPoint[]
                {
                        new DataPoint(0,1),
                        new DataPoint(2,5),
                        new DataPoint(3,1),
                        new DataPoint(5,6),
                        new DataPoint(8,3),




    };
        return (dp);
}
}