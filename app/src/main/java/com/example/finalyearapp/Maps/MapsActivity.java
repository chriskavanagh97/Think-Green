package com.example.finalyearapp.Maps;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.finalyearapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;



    // Add a marker in Sydney and move the camera
    LatLng Marker ;

    String name, address,  city,  state,  coordinantes;
    Double lat , lng;

    String name2, address2;
    Double lat2 ,lng2;
    RelativeLayout maincontent;
    LinearLayout mainmenu;
    Button sessiontype;
    RecyclerView recyclerView;
    ArrayList<Location> locations = new ArrayList<>();
    RecycleAdapter adapter;
    String singlevalue ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        maincontent = (RelativeLayout) findViewById(R.id.mainContent);
        mainmenu = (LinearLayout) findViewById(R.id.mainmenu);

        sessiontype = (Button) findViewById(R.id.sessionType);

        sessiontype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maincontent.animate().translationX(-785);
                mainmenu.animate().translationX(-785);
            }
        });




        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent.animate().translationX(0);
                mainmenu.animate().translationX(0);


            }
        });



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

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent value = getIntent();
        singlevalue = value.getStringExtra("value");
        mMap = googleMap;


        if(singlevalue.equals("true"))
        {

            address2 = value.getStringExtra("address");
            name2 = value.getStringExtra("name");
            lat2 = value.getDoubleExtra("lat", 1);
            lng2 = value.getDoubleExtra("lng", 1);

            Marker = new LatLng(lat2, lng2);
            mMap.addMarker(new MarkerOptions().position(Marker).title(name2 + " " + address2));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Marker, 15));

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
                    lat = jo_inside.getDouble("lat");
                    lng = jo_inside.getDouble("lng");
                    address = address1 + " , " + address2;
                    coordinantes = lat + "," + lng;



                    locations.add(new Location(name, address, city, state, coordinantes, lat ,lng ));

                    adapter = new RecycleAdapter(locations, MapsActivity.this);
                    recyclerView.setAdapter(adapter);



                    // Add a marker in Sydney and move the camera
                    Marker = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(Marker).title(name + " " + address));




                }


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else {


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
                    lat = jo_inside.getDouble("lat");
                    lng = jo_inside.getDouble("lng");
                    address = address1 + " , " + address2;
                    coordinantes = lat + "," + lng;

                    locations.add(new Location(name, address, city, state, coordinantes ,lat ,lng ));


                    adapter = new RecycleAdapter(locations, MapsActivity.this);
                    recyclerView.setAdapter(adapter);

                    mMap = googleMap;

                    // Add a marker in Sydney and move the camera
                    LatLng Marker = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(Marker).title(name + " " + address));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(Marker));

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
    private void filter(String text)
    {
        ArrayList<Location> filteredList = new ArrayList<Location>();

        for (Location item : locations)
        {
            if(item.getCity().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);

            }

        }
        adapter.filterlist(filteredList);
    }
}
