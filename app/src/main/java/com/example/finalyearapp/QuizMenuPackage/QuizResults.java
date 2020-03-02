package com.example.finalyearapp.QuizMenuPackage;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearapp.MainMenu;
import com.example.finalyearapp.R;
import com.example.finalyearapp.Score;
import com.example.finalyearapp.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class QuizResults extends AppCompatActivity {
    private int score;
    Time time;
    Date date;
    private String email;
    private String userid;
    private String score1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);


        TextView t1 = (TextView) findViewById(R.id.title1);
        TextView r1 = (TextView) findViewById(R.id.result1);

        TextView r2 = (TextView) findViewById(R.id.result2);
        TextView t2 = (TextView) findViewById(R.id.title2);

        TextView r3 = (TextView) findViewById(R.id.result3);
        TextView t3 = (TextView) findViewById(R.id.title3);

        TextView r4 = (TextView) findViewById(R.id.result4);
        TextView t4 = (TextView) findViewById(R.id.title4);

        Button home = (Button) findViewById(R.id.home);
        Button button = (Button) findViewById(R.id.button);
        Intent i = getIntent();

        String questions = i.getStringExtra("total");
        String corrrect = i.getStringExtra("correct");
        String incorrect = i.getStringExtra("incorrect");


        r1.setText(questions);
        r2.setText(corrrect);
        r3.setText(incorrect);


        score = Integer.parseInt(corrrect) * 100;

        r4.setText(String.valueOf(score));

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        userid = mFirebaseAuth.getCurrentUser().getUid();

        Map map = new HashMap();
        map.put("timestamp", ServerValue.TIMESTAMP);

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("Score");
        Score newScore = new Score(email,  score, map);


        newScore.setUsername(email);
        newScore.setTimestamp(map);
        newScore.setScore(score);


        mRootRef.child(userid).setValue(newScore);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(userid));

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                score1 = String.valueOf(user.getScore());




                final int score2 = score + Integer.parseInt(score1);

                //Search through the score tables and add up all their scores.

                //r3.setText(String.valueOf(score));
                FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(userid)).child("score").setValue(score2);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        email =  mFirebaseAuth.getCurrentUser().getEmail();








        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResults.this, MainMenu.class));
            }
        });


    }




}

