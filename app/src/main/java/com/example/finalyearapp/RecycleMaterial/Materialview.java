package com.example.finalyearapp.RecycleMaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.finalyearapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.finalyearapp.MainActivity.KEY1;


public class Materialview extends AppCompatActivity {



    String name = "aerosols";

    TextView what , how , tip , where, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialview);

        what = (TextView) findViewById(R.id.what);
        how = (TextView) findViewById(R.id.How);
        where = (TextView) findViewById(R.id.Where);
        tip = (TextView) findViewById(R.id.Tip);
        title = (TextView) findViewById(R.id.Title);

        DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Materials").child(String.valueOf(name));
        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final Material material = dataSnapshot.getValue(Material.class);

                title.setText(material.getName());
                what.setText(material.getWhat());
                where.setText(material.getWhere());
                how.setText(material.getHow());
                tip.setText(material.getTip());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
