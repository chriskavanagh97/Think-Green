package com.example.finalyearapp.RecycleMaterial;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


import com.example.finalyearapp.R;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class recyclewhat extends AppCompatActivity {
    String Where, why;

    private ArrayList<Material> materials = new ArrayList<>();

    private RecyclerView recyclerView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclewhat);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);


        recyclerView.setLayoutManager(mLayoutManager);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Materials");


        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        EditText search = (EditText) findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });


     /*   database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Material p = dataSnapshot1.getValue(Material.class);
                    materials.add(new Material(p.getName()));
                }


                adapter = new RecycleAdapter(materials, recyclewhat.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        String json;
        try {
            InputStream is = getAssets().open("materials.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("Materials");



            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String what = jo_inside.getString("What?");
                String name = jo_inside.getString("name");


                if(jo_inside.has("Where?"))
                {
                     Where = jo_inside.getString("Where?");

                }
                else
                {
                    Where = " ";
                }
                if(jo_inside.has("Why?"))
                {
                    why = jo_inside.getString("Why?");

                }
                else if (jo_inside.has("How?"))
                {
                    why = jo_inside.getString("How?");
                }


                materials.add(new Material(what, Where, name));






   /* String json;
            try{
        InputStream is = getAssets().open("output.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");


        JSONObject obj = new JSONObject(json);
        JSONArray m_jArry = obj.getJSONArray("Materials");


        for (int i = 0; i < m_jArry.length(); i++) {

            JSONObject jo_inside = m_jArry.getJSONObject(i);


            Iterator<String> keysItr = jo_inside.keys();
            while (keysItr.hasNext()) {
                String key = keysItr.next();
                materials.add(new Material(key));

            }
*/

            adapter = new RecycleAdapter(materials, recyclewhat.this);
            recyclerView.setAdapter(adapter);


        }



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void filter(String text)
    {
        ArrayList<Material> filteredList = new ArrayList<Material>();

        for (Material item : materials)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

        }
        adapter.filterlist(filteredList);
    }


    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
    }
}

