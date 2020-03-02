package com.example.finalyearapp;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearapp.QuizMenuPackage.QuizResults;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


public class ReviewQuestions extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private TextView t1_question;
    private TextView next;
    private TextView description;
    private int total = 1;
    private int incorrect = 0;
    private int correct =0;
    private ArrayList<String> ids = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_questions);



        b1 = (Button) findViewById(R.id.option1);
        b2 = (Button) findViewById(R.id.option2);
        b3 = (Button) findViewById(R.id.option3);
        b4 = (Button) findViewById(R.id.option4);
        next = (TextView) findViewById(R.id.timerTxt);

        t1_question = (TextView) findViewById(R.id.questionsTxt);

        description =  (TextView) findViewById(R.id.description);
        UpdateQuestion();
    }
    private void UpdateQuestion(){
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        String userid = user.getUid();
        Boolean done = false;

        DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Review Questions").child(userid).child("Pollution");
        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Iterator<DataSnapshot> questions = dataSnapshot.getChildren().iterator();
                while(questions.hasNext()){
                    DataSnapshot question = questions.next();
                    String id;
                    id = question.child("id").getValue().toString();
                    ids.add(id);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        if(total >4) {

            description.setText("Completed");
            details();

        }
        else
        {
            int n = 0;

            //if the number has been selected, so add the selected items to a list.
            DatabaseReference databaseref2 = FirebaseDatabase.getInstance().getReference().child("Questions").child("Pollution").child(String.valueOf(ids.get(n)));
            databaseref2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question = dataSnapshot.getValue(Question.class);
                    t1_question.setText(question.getQuestion());
                    b1.setText(question.getAnswer1());
                    b2.setText(question.getAnswer2());
                    b3.setText(question.getAnswer3());
                    b4.setText(question.getAnswerCorrect());



                    b1.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    incorrect++;
                                    b1.setBackgroundColor(Color.RED);
                                    b4.setBackgroundColor(Color.GREEN);

                                    description.setBackgroundColor(Color.YELLOW);
                                    description.setText(question.getDescription());

                                    Question newQuestion = new Question();
                                    newQuestion.setId(question.getId());




                                }
                            }, 1500);
                        }
                    });
                    b2.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    incorrect++;
                                    b2.setBackgroundColor(Color.RED);
                                    b4.setBackgroundColor(Color.GREEN);

                                    description.setBackgroundColor(Color.YELLOW);
                                    description.setText(question.getDescription());

                                    Question newQuestion = new Question();
                                    newQuestion.setId(question.getId());






                                }
                            }, 1500);
                        }
                    });
                    b3.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    incorrect++;
                                    b3.setBackgroundColor(Color.RED);
                                    b4.setBackgroundColor(Color.GREEN);

                                    description.setBackgroundColor(Color.YELLOW);
                                    description.setText(question.getDescription());

                                    Question newQuestion = new Question();
                                    newQuestion.setId(question.getId());


                                }
                            }, 1500);
                        }
                    });
                    b4.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    b4.setBackgroundColor(Color.GREEN);

                                    description.setText(question.getDescription());
                                    description.setBackgroundColor(Color.YELLOW);



                                }
                            }, 1500);
                        }
                    });
                    next.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    b2.setBackgroundColor(Color.LTGRAY);
                                    b1.setBackgroundColor(Color.LTGRAY);
                                    b3.setBackgroundColor(Color.LTGRAY);
                                    b4.setBackgroundColor(Color.LTGRAY);
                                    description.setText("");
                                    description.setBackgroundColor(Color.WHITE);
                                    total = total + 1;

                                    UpdateQuestion();
                                }
                            }, 1500);
                        }
                    });


                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }
    private void details(){

        total = total -1 ;
        Intent resultintent = new Intent(ReviewQuestions.this, QuizResults.class);
        resultintent.putExtra("total",String.valueOf(total));
        resultintent.putExtra("correct",String.valueOf(correct));
        resultintent.putExtra("incorrect",String.valueOf(incorrect));
        startActivity(resultintent);

    }


    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }

}

