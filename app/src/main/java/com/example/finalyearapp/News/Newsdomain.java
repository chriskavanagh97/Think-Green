package com.example.finalyearapp.News;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalyearapp.QuizMenuPackage.QuizAdapter;
import com.example.finalyearapp.QuizMenuPackage.QuizModel;
import com.example.finalyearapp.R;

import java.util.ArrayList;

public class Newsdomain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Newsmenumodel> imageModelArrayList;
    private Newsadapter adapter;

    private int[] myImageList = new int[]{R.drawable.rte, R.drawable.irishtimes, R.drawable.time, R.drawable.independent, R.drawable.cnn, R.drawable.sun, R.drawable.energy};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdomain);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        imageModelArrayList = domains();
        adapter = new Newsadapter(this, imageModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

    }

    private ArrayList<Newsmenumodel> domains() {

        ArrayList<Newsmenumodel> list = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Newsmenumodel newsmodell = new Newsmenumodel();
            newsmodell.setImage_drawable(myImageList[i]);
            if (i == 0) {
                newsmodell.setName("rte");
            } else if (i == 1) {
                newsmodell.setName("the-irish-times");
            } else if (i == 2) {
                newsmodell.setName("time");
            } else if (i == 3) {
                newsmodell.setName("Independent");
            } else if (i == 4) {
                newsmodell.setName("cnn");
            } else if (i == 5) {
                newsmodell.setName("sun");
            }

            list.add(newsmodell);
        }

        return list;
    }


    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }

}

