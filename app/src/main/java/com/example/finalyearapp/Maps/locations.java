package com.example.finalyearapp.Maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.finalyearapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class locations extends AppCompatActivity {
    private RecyclerView recyclerView;
    com.example.finalyearapp.Maps.RecycleAdapter adapter;
    ArrayList <Location> locations = new ArrayList<>();
    String name, address,  city,  state,  coordinantes , lat , lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);


    }

    public void loadJSONFromAsset() {

        String json;
        try {
            InputStream is = getAssets().open("grasses.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("grasses");



            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);


                 name = jo_inside.getString("Name");
                 String address1 = jo_inside.getString("Adresss");
                 String address2 = jo_inside.getString("Adress2");
                 city = jo_inside.getString("City");
                 state = jo_inside.getString("State");
                 lat = jo_inside.getString("lat");
                 lng = jo_inside.getString("lng");
                 address = address1+ " , " + address2;
                 coordinantes = lat + "," + lng;



                locations.add(new Location( name,  address,  city,  state,  coordinantes));


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

}
