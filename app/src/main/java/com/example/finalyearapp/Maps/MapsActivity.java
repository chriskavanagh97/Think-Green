package com.example.finalyearapp.Maps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String name, address,  city,  state,  coordinantes;
    Double lat , lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

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


                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng Marker = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(Marker).title(name + " " + address));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(Marker));

            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
