package com.example.finalyearapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.finalyearapp.Leaderboard.Leaderboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

import static com.example.finalyearapp.MainActivity.KEY1;


public class MainMenu extends AppCompatActivity {

    int score1;

    private int score;
    DatabaseReference database;
    TextView emaiil;
    RelativeLayout maincontent;
    LinearLayout mainmenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        String email = Objects.requireNonNull(mFirebaseAuth.getCurrentUser()).getEmail();

        maincontent = (RelativeLayout) findViewById(R.id.mainContent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent.animate().translationX(0);
                mainmenu.animate().translationX(0);


            }
        });
        maincontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maincontent.animate().translationX(-735);
                mainmenu.animate().translationX(-735);
            }
        });
        //=========================================================================================================================================================================================
        //reading from the Database
        //=========================================================================================================================================================================================


        //=========================================================================================================================================================================================
        //Setting the text box with info from the database
        //=========================================================================================================================================================================================

        ImageView quiz = (ImageView) findViewById(R.id.Quiz);
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inttoQuiz = new Intent(MainMenu.this, QuizMenu.class);
                startActivity(inttoQuiz);

            }
        });

        //=========================================================================================================================================================================================
        //Sign Out
        //=========================================================================================================================================================================================

        Button signout = (Button) findViewById(R.id.SignOut);
        signout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intToMain);

            }
        });

        //=========================================================================================================================================================================================
        //Writing a new score to the database
        //=========================================================================================================================================================================================
       /* Button scoreupdate = (Button) findViewById(R.id.ScoreUpdate);
        scoreupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {

                score = 100;




            }
        });*/

        //=========================================================================================================================================================================================
        //Switching to the profile page
        //=========================================================================================================================================================================================
        Button profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        ViewUser.class);

                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        intent.getStringExtra(KEY1);


        ImageView leaderboard = (ImageView) findViewById(R.id.Leaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,
                        Leaderboard.class);

                startActivity(intent);
            }
        });

    }


}



