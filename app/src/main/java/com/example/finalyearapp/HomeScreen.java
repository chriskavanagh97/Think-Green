package com.example.finalyearapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreen extends AppCompatActivity {


    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);



        ImageButton login = (ImageButton) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intToMain = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(intToMain);

            }
        });

        ImageButton register = (ImageButton) findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent inttoReg = new Intent(HomeScreen.this, Register.class);
                startActivity(inttoReg);

            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            //=========================================================================================================================================================================================
            //If somebody opens the app and are already logged in this will check it and send them to the menu
            //=========================================================================================================================================================================================

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null){
                    Toast.makeText(HomeScreen.this,"You are Logged in",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(HomeScreen.this,
                            MainMenu.class);
                    startActivity(intent);
                }
                else{
                    Intent inttoReg = new Intent(HomeScreen.this, HomeScreen.class);
                    startActivity(inttoReg);
                    Toast.makeText(HomeScreen.this,"You are Logged out",Toast.LENGTH_SHORT).show();

                }

            }
        };

    }



        //=========================================================================================================================================================================================
        //If somebody opens the app and are already logged in this will check it and send them to the menu
        //=========================================================================================================================================================================================


    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();



    }
}
