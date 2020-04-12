package com.example.finalyearapp;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewUser extends AppCompatActivity {

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        // home = (Button) findViewById(R.id.home);
        final TextView R1,R2,R3,R4;



        R1 = (TextView) findViewById(R.id.Nameresult);
        R2 = (TextView) findViewById(R.id.Usernameresult);
        R3 = (TextView) findViewById(R.id.Emailresult);

        R4 = (TextView) findViewById(R.id.ScoreResult );

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String userid = mFirebaseAuth.getCurrentUser().getUid();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(userid));
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                R1.setText(user.getFirstName());
                R2.setText(user.getUsername());
                R3.setText(user.getEmail());
                R4.setText(String.valueOf((user.getScore())));



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
