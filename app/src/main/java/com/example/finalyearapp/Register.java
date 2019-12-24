package com.example.finalyearapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {

    private EditText EmailId;
    private EditText Password;
    private EditText RegName;
    private EditText RegUsername;
    private int Score;
    private String UserId;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        EmailId = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        RegName = findViewById(R.id.Name);
        RegUsername = findViewById(R.id.Username);


        Button registerButton = (Button) findViewById(R.id.Registerbutton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = EmailId.getText().toString();
                String pword = Password.getText().toString();
                final String name = RegName.getText().toString();
                final String username = RegUsername.getText().toString();

                if (Email.isEmpty()) {
                    EmailId.setError("Please enter Email");
                    EmailId.requestFocus();
                } else if (pword.isEmpty()) {
                    Password.setError("Please enter Password");
                    Password.requestFocus();
                }   else if (name.isEmpty()) {
                    RegName.setError("Please enter Name");
                    RegName.requestFocus();
                }   else if (username.isEmpty()) {
                    RegUsername.setError("Please enter username");
                    RegUsername.requestFocus();



                } else {
                    mFirebaseAuth.createUserWithEmailAndPassword(Email, pword).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(Register.this, "onComplete: Failed=" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            } else
                            {

                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                UserId = Objects.requireNonNull(user).getUid();

                                Score = 0;
                                User createUser = new User(username, Email, name, Score);
                                createUser.setUsername(username);
                                createUser.setEmail(Email);
                                createUser.setFirstName(name);
                                createUser.setScore(Score);

                                mRootRef.child(UserId).setValue(createUser);


                                startActivity(new Intent(Register.this, MainMenu.class));
                            }
                        }


                    });
                }

            }

        });

    }



}
