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
        final TextView T1,T2,T3,T4 ,R1,R2,R3,R4;

        T1 = (TextView) findViewById(R.id.NameTitle);
        T2 = (TextView) findViewById(R.id.UsernameTitle);
        T3 = (TextView) findViewById(R.id.EmailTitle);

        T4 = (TextView) findViewById(R.id.ScoreTitle);

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
       /* DatabaseReference database2 = FirebaseDatabase.getInstance().getReference().child("Score").child(userid);
        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Score score = dataSnapshot.getValue(Score.class);
                int Score = score.getScore();

                int score1 = 0;
                score1 = score1 + Score;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

       /* home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewUser.this, Menu.class));
            }
        });*/

    }
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }
}
