package com.example.finalyearapp.Carbonfootprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalyearapp.MainActivity;
import com.example.finalyearapp.Question;
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

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    ArrayList<Entry> dataVals = new ArrayList<>();
    ArrayList<Float> dataVals2 = new ArrayList<>();
    int total = 0;
    Dialog myDialog;
    float score;
    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        linechart = (LineChart) findViewById(R.id.linechart);
        Display();

    }


    public void Display(){
        Intent i = getIntent();
        //dataVals2 = i.getFloatArrayExtra();

        Toast.makeText(linechartresults.this, "Display screen activated" + dataVals2  , Toast.LENGTH_SHORT).show();
        dataVals.add(new Entry(0,12));
        dataVals.add(new Entry(1,11));
        dataVals.add(new Entry(2,9));
        dataVals.add(new Entry(3,13));




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