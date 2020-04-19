package com.example.finalyearapp.Leaderboard;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalyearapp.R;
import com.example.finalyearapp.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Leaderboard extends AppCompatActivity {

    private ArrayList<User> leaderboardlist = new ArrayList<>();

    private RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_leaderboard);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);

        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);


        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Users");

        //Collections.sort(leaderboardlist, Collections.reverseOrder());







        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        Query queryRef = database.orderByChild("score").limitToLast(10);
        queryRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    int i = 1;
                    User p = dataSnapshot1.getValue(User.class);
                    leaderboardlist.add(new User(i,p.getEmail(),p.getScore()));
                }


                adapter = new MyAdapter(leaderboardlist, Leaderboard.this);
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
