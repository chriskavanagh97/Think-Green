package com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu;




import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalyearapp.Question;
import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.PollutionQuiz;
import com.example.finalyearapp.QuizResults;
import com.example.finalyearapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
public class Oceanquizz extends AppCompatActivity  {


    
    private RadioButton b1;
    private RadioButton b2;
    private RadioButton b3;
    private RadioButton b4;
    Button popup;
    String answeresult;
    private TextView t1_question;
    String answer;
    private TextView description;
    private int total = 1;
    private int incorrect = 0;
    private int correct =0;
    int n;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        b1 = (RadioButton) findViewById(R.id.option1);
        b2 = (RadioButton) findViewById(R.id.option2);
        b3 = (RadioButton) findViewById(R.id.option3);
        b4 = (RadioButton) findViewById(R.id.option4);




        t1_question = (TextView) findViewById(R.id.questionsTxt);

        description =  (TextView) findViewById(R.id.description);
        UpdateQuestion();

        myDialog = new Dialog(this);
    }
    public void ShowPopup(View v) {

        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    private void UpdateQuestion(){


        if(total >4) {

            description.setText("Completed");
            details();

        }
        else
        {


            DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference().child("Questions").child("Pollution").child(String.valueOf(total));
            databaseref.addValueEventListener(new ValueEventListener() {
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

                            boolean checked = ((RadioButton) v).isChecked();

                            switch (v.getId())
                            {
                                case R.id.option1:
                                    if (checked)
                                        answer = "wrong";


                                case R.id.option2:
                                    if (checked)
                                        answer = "wrong";


                                case R.id.option3:
                                    if (checked)
                                        answer = "wrong";


                                case R.id.option4:
                                    if (checked)
                                        answer = "correct";


                            }

                        }
                    });

                    popup = (Button) findViewById(R.id.popup);
                    popup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {


                            TextView txtclose;
                            Button btnFollow;
                            myDialog.setContentView(R.layout.custompopup);
                            txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
                            txtclose.setText("X");

                            TextView txtanswer = (TextView) myDialog.findViewById(R.id.Answer);

                            if (answer.equals("wrong"))
                            {
                                txtanswer.setText("Sorry this is Incorrect");
                                incorrect ++;
                            }
                            else {
                                txtanswer.setText("Well done this is correct");
                                correct ++;

                            }

                            TextView textview = (TextView) myDialog.findViewById(R.id.description);
                            textview.setText(question.getDescription());

                            txtclose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    myDialog.dismiss();
                                    total ++;
                                }
                            });
                            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            myDialog.show();
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
        Intent resultintent = new Intent(Oceanquizz.this, QuizResults.class);
        resultintent.putExtra("total",String.valueOf(total));
        resultintent.putExtra("correct",String.valueOf(correct));
        resultintent.putExtra("incorrect",String.valueOf(incorrect));
        startActivity(resultintent);

    }

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }


    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }

}

