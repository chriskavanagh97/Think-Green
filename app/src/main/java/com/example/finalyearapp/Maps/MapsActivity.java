package com.example.finalyearapp.Maps;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import com.example.finalyearapp.MainMenu;
import com.example.finalyearapp.R;

import com.google.android.gms.location.FusedLocationProviderClient;


import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;


import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback , GoogleMap.OnPolylineClickListener {

    private GoogleMap mMap;
    Location mLastLocation;
    com.google.android.gms.maps.model.Marker mCurrLocationMarker;
    Dialog myDialog;
    GeoApiContext mCGeoApiContext = null;
    String TAG = "MapsActivity";
    Location location;
    private ArrayList<PolylineData> mPolylineData = new ArrayList<>();
    private ArrayList<Marker> mTripMarkers = new ArrayList<>();




    // Add a marker in Sydney and move the camera
    LatLng Marker;
    LatLng Markerblue;
    LatLng MarkerRed;
    LatLng MarkerYellow;
    Location currentlocation;
    int i;

    String name, address, city, state, coordinantes, recycleoutlet;
    Double lat, lng;

    String name2, address2, city2, intentcity;
    Double lat2, lng2;
    RelativeLayout maincontent;
    LinearLayout mainmenu;
    Button sessiontype;
    RecyclerView recyclerView;
    ArrayList<place> places = new ArrayList<>();
    RecycleAdapter adapter;
    String singlevalue;

    FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
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

        Button home = (Button) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MapsActivity.this, MainMenu.class);
                startActivity(intent);
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


        Spinner mySpinner = (Spinner) findViewById(R.id.cities);
        Spinner mySpinner2 = findViewById(R.id.outlets);

        ArrayList<String> cities = new ArrayList<>();

        cities.add("Carlow");
        cities.add("Cavan");
        cities.add("Clare");
        cities.add("Cork");
        cities.add("Dublin");
        cities.add("Galway");
        cities.add("Donegal");
        cities.add("Kerry");
        cities.add("Kildare");
        cities.add("Kilkenny");
        cities.add("Laois");
        cities.add("Louth");
        cities.add("Leitrim");
        cities.add("Limerick");
        cities.add("Longford");
        cities.add("Mayo");
        cities.add("Meath");
        cities.add("Offaly");
        cities.add("Roscommon");
        cities.add("Sligo");
        cities.add("Tipperary");
        cities.add("Waterford");
        cities.add("Westmeath");
        cities.add("Wexford");
        cities.add("Wicklow");
        ArrayList<String> outlets = new ArrayList<>();

        outlets.add("All");
        outlets.add("Bring Bank");
        outlets.add("Lighting Dropoff");
        outlets.add("Civic Amenity Site");
        outlets.add("Electrical Retailers");



        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MapsActivity.this,
                android.R.layout.simple_list_item_1, cities);


        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter((myAdapter));

        ArrayAdapter<String> myadapter2 = new ArrayAdapter<>(MapsActivity.this,
                android.R.layout.simple_list_item_1, outlets);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter((myadapter2));

        Button filterchange = findViewById(R.id.filter);
        filterchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mySpinner2.getSelectedItem().toString().equals("All")) {


                    String item = mySpinner.getSelectedItem().toString();
                    Intent mapintent = new Intent(MapsActivity.this, MapsActivity.class);
                    mapintent.putExtra("value", "general locations");
                    mapintent.putExtra("city", item);
                    startActivity(mapintent);
                }
                else {


                    String item = mySpinner.getSelectedItem().toString();
                    String outlet = mySpinner2.getSelectedItem().toString();
                    Intent mapintent = new Intent(MapsActivity.this, MapsActivity.class);
                    mapintent.putExtra("value", "specified outlet");
                    mapintent.putExtra("city", item);
                    mapintent.putExtra("recycleoutlet", outlet);
                    startActivity(mapintent);
                }

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
    private void removeTripMarkers(){
        for(Marker marker: mTripMarkers){
            marker.remove();
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent value = getIntent();
        singlevalue = value.getStringExtra("value");
        mMap = googleMap;
        mMap.setOnInfoWindowClickListener(this);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnPolylineClickListener(this);


        if(mCGeoApiContext == null) {

            mCGeoApiContext = new GeoApiContext.Builder().apiKey(getString(R.string.google_maps_key)).build();
        }



        if (singlevalue.equals("specific location")) {



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


                address2 = value.getStringExtra("address");
                name2 = value.getStringExtra("name");
                lat2 = value.getDoubleExtra("lat", 1);
                lng2 = value.getDoubleExtra("lng", 1);
                city2 = value.getStringExtra("city");

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

                Location marker = new Location("marker");
                marker.setLatitude(lat2);
                marker.setLongitude(lng2);


                Toast.makeText(MapsActivity.this, "city" + marker.getLatitude(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MapsActivity.this, "city" + city2, Toast.LENGTH_SHORT).show();



                for (int i = 0; i < m_jArry.length(); i++) {

                    JSONObject jo_inside = m_jArry.getJSONObject(i);


                    if(jo_inside.getString("City").equals(city2)) {


                        name = jo_inside.getString("Name");
                        String address1 = jo_inside.getString("Address");
                        String address2 = jo_inside.getString("Address2");
                        city = jo_inside.getString("City");
                        state = jo_inside.getString("State");
                        lat = jo_inside.getDouble("lat");
                        lng = jo_inside.getDouble("lng");
                        address = address1 + " , " + address2;
                        coordinantes = lat + "," + lng;

                                   /* adapter = new RecycleAdapter(places, MapsActivity.this);
                                    recyclerView.setAdapter(adapter);
                                    mMap = googleMap;*/

                        Location target = new Location("target");
                        target.setLatitude(lat);
                        target.setLongitude(lng);
                        if (marker.distanceTo(target) < 10000) {

                            places.add(new place(name, address, city, state, coordinantes, lat, lng));


                            adapter = new RecycleAdapter(places, MapsActivity.this);
                            recyclerView.setAdapter(adapter);

                            mMap = googleMap;


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
                        } else {

                        }
                    }
                    else{

                    }
                }


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else if(singlevalue.equals("general locations")) {


            //();
            //getDeviceLocation();

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

                intentcity = value.getStringExtra("city");

                Toast.makeText(MapsActivity.this, "city" + intentcity, Toast.LENGTH_SHORT).show();


                for (int i = 0; i < m_jArry.length(); i++) {

                    JSONObject jo_inside = m_jArry.getJSONObject(i);

                    if(jo_inside.getString("City").equals(intentcity)) {

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

                        if (name.equals("Bring Bank")) {

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
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lat,
                                            lng), 10));

                        }
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
        else if(singlevalue.equals("specified outlet")){

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

                intentcity = value.getStringExtra("city");

                Toast.makeText(MapsActivity.this, "city" + intentcity, Toast.LENGTH_SHORT).show();


                for (int i = 0; i < m_jArry.length(); i++) {

                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    recycleoutlet = value.getStringExtra("recycleoutlet");

                    if(jo_inside.getString("City").equals(intentcity) & jo_inside.getString("Name").equals(recycleoutlet)) {

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

                        if (name.equals("Bring Bank")) {

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
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lat,
                                            lng), 10));

                        }
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

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            Task locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        currentlocation = (Location) task.getResult();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(currentlocation.getLatitude(),
                                        currentlocation.getLongitude()), 13));
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.");

                    }
                }
            });

        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
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

        }else if(marker.getTitle().contains("Bring Bank") ){


            TextView textview = (TextView) myDialog.findViewById(R.id.description);
            textview.setText("Bring banks are recycling containers provided by your local authority. These facilities typically accept glass bottles and jars, aluminium and tin cans. Bring Banks are FREE.\n" +
                    "\n");

        }
        else if(marker.getTitle().contains("Bring Bank") ){


            TextView textview = (TextView) myDialog.findViewById(R.id.description);
            textview.setText("Bring banks are recycling containers provided by your local authority. These facilities typically accept glass bottles and jars, aluminium and tin cans. Bring Banks are FREE.\n" +
                    "\n");

        }
        else if(marker.getTitle().contains("Bring Bank") ){


            TextView textview = (TextView) myDialog.findViewById(R.id.description);
            textview.setText("Bring banks are recycling containers provided by your local authority. These facilities typically accept glass bottles and jars, aluminium and tin cans. Bring Banks are FREE.\n" +
                    "\n");

        }
        Button route = myDialog.findViewById(R.id.route);
        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                calculateDirections(marker);
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                myDialog.dismiss();


            }

        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();


    }
    /* private void getDeviceLocation() {
         /*
          * Get the best and most recent location of the device, which may be null in rare
          * cases when a location is not available.
          */
   /*() try {
            Task locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        currentlocation = (Location) task.getResult();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(currentlocation.getLatitude(),
                                        currentlocation.getLongitude()), 13));
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.");
                    }
                }
            });
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }*/
    private void calculateDirections(Marker marker){
        try {
            Task locationResult = mFusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        // Set the map's camera position to the current location of the device.
                        currentlocation = (Location) task.getResult();
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(currentlocation.getLatitude(),
                                        currentlocation.getLongitude()), 13));
                    } else {
                        Log.d(TAG, "Current location is null. Using defaults.");

                    }

                    Log.d(TAG, "calculateDirections: calculating directions.");

                    com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                            marker.getPosition().latitude,
                            marker.getPosition().longitude
                    );
                    DirectionsApiRequest directions = new DirectionsApiRequest(mCGeoApiContext);

                    directions.alternatives(true);

                    directions.origin(
                            new com.google.maps.model.LatLng(
                                    currentlocation.getLatitude(),
                                    currentlocation.getLongitude()
                            )
                    );
                    Log.d(TAG, "calculateDirections: destination: " + destination.toString());
                    directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
                        @Override
                        public void onResult(DirectionsResult result) {
                            Log.d(TAG, "calculateDirections: routes: " + result.routes[0].toString());
                            Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
                            Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance);
                            Log.d(TAG, "calculateDirections: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());

                            addPolylinesToMap(result);
                        }

                        @Override
                        public void onFailure(Throwable e) {
                            Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage() );

                        }
                    });
                }
            });

        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }


    }
    private void addPolylinesToMap(final DirectionsResult result) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: result routes: " + result.routes.length);

                if(mPolylineData.size()>0){
                    for(PolylineData polylineData : mPolylineData){
                        polylineData.getPolyline().remove();

                    }
                    mPolylineData.clear();
                    mPolylineData = new ArrayList<>();
                }
                double duration = 9999;

                for (DirectionsRoute route : result.routes) {
                    Log.d(TAG, "run: leg: " + route.legs[0].toString());
                    List<com.google.maps.model.LatLng> decodedPath = PolylineEncoding.decode(route.overviewPolyline.getEncodedPath());

                    List<LatLng> newDecodedPath = new ArrayList<>();

                    // This loops through all the LatLng coordinates of ONE polyline.
                    for (com.google.maps.model.LatLng latLng : decodedPath) {

//                        Log.d(TAG, "run: latlng: " + latLng.toString());

                        newDecodedPath.add(new LatLng(
                                latLng.lat,
                                latLng.lng
                        ));
                    }
                    Polyline polyline = mMap.addPolyline(new PolylineOptions().addAll(newDecodedPath));
                    polyline.setColor(Color.GRAY);
                    polyline.setClickable(true);
                    mPolylineData.add(new PolylineData(polyline, route.legs[0]));

                    //highlights shortest duration

                    double tempduration = route.legs[0].duration.inSeconds;

                    if(tempduration < duration){
                        duration = tempduration;
                        onPolylineClick(polyline);
                        zoomRoute(polyline.getPoints());
                    }


                }
            }
        });
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


    @Override
    public void onPolylineClick(Polyline polyline) {

        int index = 0;

        for(PolylineData polylineData: mPolylineData){
            index++;
            Log.d(TAG, "onPolylineClick: toString: " + polylineData.toString());
            if(polyline.getId().equals(polylineData.getPolyline().getId())){
                polylineData.getPolyline().setColor(Color.RED);
                polylineData.getPolyline().setZIndex(1);

                LatLng endLocation = new LatLng(
                        polylineData.getLeg().endLocation.lat,
                        polylineData.getLeg().endLocation.lng);

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(endLocation)
                        .title("Duration: " + polylineData.getLeg().duration +"\n" + " Distance: " + polylineData.getLeg().distance));

                marker.showInfoWindow();

                mTripMarkers.add(marker);


            }
            else{
                polylineData.getPolyline().setColor(Color.GRAY);
                polylineData.getPolyline().setZIndex(0);
            }
        }
    }

    public void zoomRoute(List<LatLng> lstLatLngRoute) {

        if (mMap == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty()) return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : lstLatLngRoute)
            boundsBuilder.include(latLngPoint);

        int routePadding = 120;
        LatLngBounds latLngBounds = boundsBuilder.build();

        mMap.animateCamera(
                CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding),
                600,
                null
        );
    }

}