package com.example.finalyearapp.Maps;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.finalyearapp.R;

import com.google.android.gms.location.FusedLocationProviderClient;


import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;


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


public class MapsActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback , GoogleMap.OnPolylineClickListener {

    private GoogleMap mMap;
    Location mLastLocation;
    com.google.android.gms.maps.model.Marker mCurrLocationMarker;
    Dialog myDialog;
    GeoApiContext mCGeoApiContext = null;
    String TAG = "MapsActivity";
    Location location;
    private ArrayList<PolylineData> mPolylineData = new ArrayList<>();




    // Add a marker in Sydney and move the camera
    LatLng Marker;
    LatLng Markerblue;
    LatLng MarkerRed;
    LatLng MarkerYellow;
    Location currentlocation;

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
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.setOnPolylineClickListener(this);


        if(mCGeoApiContext == null) {

            mCGeoApiContext = new GeoApiContext.Builder().apiKey(getString(R.string.google_maps_key)).build();
        }



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

            getDeviceLocation();

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
                    polyline.setColor(Color.BLUE);
                    polyline.setClickable(true);
                    mPolylineData.add(new PolylineData(polyline, route.legs[0]));

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

        polyline.setColor(Color.RED);
        polyline.setZIndex(1);

    }
}
