package com.example.finalyearapp.Maps;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.finalyearapp.R;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    Location mLastLocation;
    com.google.android.gms.maps.model.Marker mCurrLocationMarker;
    Dialog myDialog;





    // Add a marker in Sydney and move the camera
    LatLng Marker;
    LatLng Markerblue;
    LatLng MarkerRed;
    LatLng MarkerYellow;


    String name, address, city, state, coordinantes;
    Double lat, lng;

    String name2, address2;
    Double lat2, lng2;
    RelativeLayout maincontent;
    LinearLayout mainmenu;
    Button sessiontype;
    RecyclerView recyclerView;
    ArrayList<place> places = new ArrayList<>();
    RecycleAdapter adapter;
    String singlevalue;


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

        myDialog = new Dialog(this);

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

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);


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
        mMap.setOnInfoWindowClickListener(this);

        LocationCallback mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                List<Location> locationList = locationResult.getLocations();
                if (locationList.size() > 0) {
                    //The last location in the list is the newest
                    Location location = locationList.get(locationList.size() - 1);
                    Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
                    mLastLocation = location;
                    if (mCurrLocationMarker != null) {
                        mCurrLocationMarker.remove();
                    }

                    //Place current location marker
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("Current Position");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                    mCurrLocationMarker = mMap.addMarker(markerOptions);


                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
                }
            }
        };

        if (singlevalue.equals("true")) {


            address2 = value.getStringExtra("address");
            name2 = value.getStringExtra("name");
            lat2 = value.getDoubleExtra("lat", 1);
            lng2 = value.getDoubleExtra("lng", 1);

            if (name2.equals("Bring Bank")) {
                // Add a marker in Sydney and move the camera
                MarkerYellow = new LatLng(lat2, lng2);
                mMap.addMarker(new MarkerOptions().position(MarkerYellow).title(name2 + " " + address2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MarkerYellow, 15));
            } else if (name2.equals("Lighting Dropoff")) {
                MarkerRed = new LatLng(lat2, lng2);
                mMap.addMarker(new MarkerOptions().position(MarkerRed).title(name2 + " " + address2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MarkerRed, 15));
            } else if (name2.equals("Civic Amenity Site")) {
                Markerblue = new LatLng(lat2, lng2);
                mMap.addMarker(new MarkerOptions().position(Markerblue).title(name2 + " " + address2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Markerblue, 15));
            } else if (name2.equals("Electrical Retailers")) {
                Marker = new LatLng(lat2, lng2);
                mMap.addMarker(new MarkerOptions().position(Marker).title(name2 + " " + address2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Marker, 15));
            }


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


                    places.add(new place(name, address, city, state, coordinantes, lat, lng));

                    adapter = new RecycleAdapter(places, MapsActivity.this);
                    recyclerView.setAdapter(adapter);


                    if (name.equals("Bring Bank")) {
                        // Add a marker in Sydney and move the camera
                        MarkerYellow = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(MarkerYellow).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                    } else if (name.equals("Lighting Dropoff")) {
                        MarkerRed = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(MarkerRed).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


                    } else if (name.equals("Civic Amenity Site")) {
                        Markerblue = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(Markerblue).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                    } else if (name.equals("Electrical Retailers")) {
                        Marker = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(Marker).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


                    }


                }


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {


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

                    places.add(new place(name, address, city, state, coordinantes, lat, lng));


                    adapter = new RecycleAdapter(places, MapsActivity.this);
                    recyclerView.setAdapter(adapter);

                    mMap = googleMap;

                    // Add a marker in Sydney and move the camera
                    if (name.equals("Bring Bank")) {

                        MarkerYellow = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(MarkerYellow).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MarkerYellow, 7));
                    } else if (name.equals("Lighting Dropoff")) {
                        MarkerRed = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(MarkerRed).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MarkerRed, 7));

                    } else if (name.equals("Civic Amenity Site")) {
                        Markerblue = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(Markerblue).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Markerblue, 7));

                    } else if (name.equals("Electrical Retailers")) {
                        Marker = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(Marker).title(name + " " + address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Marker, 7));

                    }


                    mMap.setMyLocationEnabled(true);

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

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();

        myDialog.setContentView(R.layout.mapspopup);
        TextView txtclose;
        Button btnFollow;

        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");

        TextView txtanswer = (TextView) myDialog.findViewById(R.id.Answer);
         txtanswer.setText(marker.getTitle());
        if(marker.getTitle().contains("Bring Bank") ){


        TextView textview = (TextView) myDialog.findViewById(R.id.description);
        textview.setText("Bring banks are recycling containers provided by your local authority. These facilities typically accept glass bottles and jars, aluminium and tin cans. Bring Banks are FREE.\n" +
                "\n");

        }
        txtclose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                myDialog.dismiss();


            }

        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }







    private void filter(String text) {
        ArrayList<place> filteredList = new ArrayList<place>();

        for (place item : places) {
            if (item.getCity().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);

            } else if (item.getAddress().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }

        }
        adapter.filterlist(filteredList);
    }


}
