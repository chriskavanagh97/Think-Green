package com.example.finalyearapp.Maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.finalyearapp.R;
import com.example.finalyearapp.RecycleMaterial.Material;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class locations extends AppCompatActivity
{



    RecyclerView recyclerView;
    ArrayList <Location> locations = new ArrayList<>();
    String name, address,  city,  state,  coordinantes , lat , lng;
    RecycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);


        recyclerView.setLayoutManager(mLayoutManager);
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


        String json;
        try {
            InputStream is = getAssets().open("BringBanks.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("Bringbanks");



            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);


                 name = jo_inside.getString("Name");
                 String address1 = jo_inside.getString("Address");
                 String address2 = jo_inside.getString("Address2");
                 city = jo_inside.getString("City");
                 state = jo_inside.getString("State");
                 lat = jo_inside.getString("lat");
                 lng = jo_inside.getString("lng");
                 address = address1+ " , " + address2;
                 coordinantes = lat + "," + lng;



                locations.add(new Location( name,  address,  city,  state));


                adapter = new RecycleAdapter(locations,locations.this);
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
        ArrayList<Location> filteredList = new ArrayList<Location>();

        for (Location item : locations)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

        }
        adapter.filterlist(filteredList);
    }

}
