package com.example.finalyearapp.ClimateChange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalyearapp.QuizMenuPackage.OceanQuizzMenu.OceanQuizMenu;
import com.example.finalyearapp.QuizMenuPackage.PlasticQuizMenu.Quiz;
import com.example.finalyearapp.QuizMenuPackage.PollutionQuizPackage.pollutionQuizmenu;
import com.example.finalyearapp.QuizMenuPackage.QuizAdapter;
import com.example.finalyearapp.QuizMenuPackage.QuizMenu;
import com.example.finalyearapp.QuizMenuPackage.QuizModel;
import com.example.finalyearapp.R;

import java.util.ArrayList;

public class AnimalMenu extends AppCompatActivity {    private RecyclerView recyclerView;
    private ArrayList<Animal> imageModelArrayList;
    private animaladapter adapter;

    private int[] myImageList = new int[]{R.drawable.penguincycle, R.drawable.pandacycle,R.drawable.sealcycle, R.drawable.tigercycle,R.drawable.polarbearcycle,R.drawable.slothcycle,R.drawable.rhinocycle, R.drawable.elephantcycle, R.drawable.monkeycycle,R.drawable.gturtlecycle, R.drawable.sleopardcycle, R.drawable.turtlecycle};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_menu);




        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = quizzes();
        adapter = new animaladapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private ArrayList<Animal> quizzes(){

        ArrayList<Animal> list = new ArrayList<>();

        for(int i = 0; i < 12; i++){
            Animal animal = new Animal();
            animal.setImage_drawable(myImageList[i]);
            if(i == 0)
            {
                animal.setName("penguin");
            }
            else if(i == 1)
            {
                animal.setName("panda");
            }
            else if(i == 2)
            {
                animal.setName("seal");
            }
            else if(i == 3)
            {
                animal.setName("tiger");
            }   else if(i == 4)
            {
                animal.setName("polarbear");
            }
            else if(i == 5)
            {
                animal.setName("sloth");
            }
            else if(i == 6)
            {
                animal.setName("rhino");
            }
            else if(i == 7)
            {
                animal.setName("elephant");
            }
            else if(i == 8)
            {
                animal.setName("monkey");
            }
            else if(i == 9)
            {
                animal.setName("turtle");
            }
            else if(i == 10)
            {
                animal.setName("leopard");
            }
            else if(i == 11)
            {
                animal.setName("turtle");
            }



            list.add(animal);
        }

        return list;
    }






    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }


}