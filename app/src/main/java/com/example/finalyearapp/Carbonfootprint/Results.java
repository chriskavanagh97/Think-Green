package com.example.finalyearapp.Carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalyearapp.ClimateChange.Didyouknow;
import com.example.finalyearapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {

    double score;
    ArrayList <Double> dataVals2 = new ArrayList<>();
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);






        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String userid = mFirebaseAuth.getCurrentUser().getUid();


          /*  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Carbonfootprint").child(userid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        final LineChartresult linechats = dataSnapshot.getValue(LineChartresult.class);
                        score = linechats.getyValue();

                        dataVals2.add(score);

                    }

                    //float confirm = total.floatValue();
                  //  Toast.makeText(Results.this, "Display screen activated" + score  , Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/

        PieChart();
        Button history = (Button) findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculator();

            }
        });

        Button analysis = (Button) findViewById(R.id.transport);
        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultintent = new Intent(Results.this, transport.class);
                resultintent.putExtra("result","transport");



                startActivity(resultintent);

            }
        });
        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultintent2 = new Intent(Results.this, transport.class);
                resultintent2.putExtra("result","home");
                startActivity(resultintent2);

            }
        });
        Button purchasing = (Button) findViewById(R.id.purchasing);
        purchasing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultintent3 = new Intent(Results.this, transport.class);
                resultintent3.putExtra("result","purchasing");
                startActivity(resultintent3);

            }
        });
        Button food = (Button) findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultintent4 = new Intent(Results.this, transport.class);
                resultintent4.putExtra("result","food");
                startActivity(resultintent4);

            }
        });
        Button menu = findViewById(R.id.menubtn);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuintent = new Intent(Results.this, Menu.class);
                startActivity(menuintent);

            }
        });

    Button climatechange = findViewById(R.id.climatechange);
        menu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent climateintent = new Intent(Results.this, Didyouknow.class);
            startActivity(climateintent);

        }
    });
    }

    public void calculator(){

        //Toast.makeText(Results.this, "Float array: calculator method" + dataVals2  , Toast.LENGTH_SHORT).show();
        Intent resultintent = new Intent(Results.this, linechartresults.class);
        //resultintent.putExtra("results",dataVals2);

        startActivity(resultintent);


        }
        public void PieChart(){
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
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);


        }

    }


