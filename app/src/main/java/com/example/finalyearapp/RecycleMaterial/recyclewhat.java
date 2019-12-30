package com.example.finalyearapp.RecycleMaterial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;



import com.example.finalyearapp.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recyclewhat extends AppCompatActivity {

    private ArrayList<Material> materials = new ArrayList<>();

    private RecyclerView recyclerView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclewhat);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Materials");


        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        Query queryRef = database.orderByChild("score").limitToLast(10);
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Material user =dataSnapshot.getValue(Material.class);

                //leaderboardlist.add(new Material(user.getMaterialname(),user.getEmail(),user.getScore()));


                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Material p = dataSnapshot1.getValue(Material.class);
                    materials.add(new Material(p.getName()));
                }


                adapter = new RecycleAdapter(materials, recyclewhat.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }


    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }
}

