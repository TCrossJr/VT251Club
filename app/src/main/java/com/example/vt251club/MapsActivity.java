package com.example.vt251club;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
     * we mark all the towns in Vermont, USA.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Polygons for the towns
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("in.txt"), "UTF-8"));
            String data;
            while((data = reader.readLine()) != null){
                PolygonOptions polygonOptions = new PolygonOptions().strokeColor(Color.RED);
                while(!data.equals("")) {
                    String[] splitData = data.split("\\(|\\)|,");
                    Double firstCoord = Double.parseDouble(splitData[1]);
                    Double secondCoord = Double.parseDouble(splitData[2]);
                    polygonOptions.add(new LatLng(firstCoord, secondCoord));
                    data = reader.readLine();
                }
                mMap.addPolygon(polygonOptions);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        // Marker in Vermont
        LatLng vermont = new LatLng(44.5588, -72.5778);

        // Markers for the towns
        LatLng berkshire = new LatLng(44.9717, -72.7754);
        LatLng franklin = new LatLng(44.9813, -72.9166);
        LatLng alburg = new LatLng(44.9751, -73.3002);
        LatLng richford = new LatLng(44.9970, -72.6713);
        LatLng jay = new LatLng(44.9649, -72.4602);

        mMap.addMarker(new MarkerOptions().position(vermont).title("Marker in Vermont"));
        mMap.addMarker(new MarkerOptions().position(berkshire).title("Berkshire"));
        mMap.addMarker(new MarkerOptions().position(franklin).title("Franklin"));
        mMap.addMarker(new MarkerOptions().position(alburg).title("Alburg"));
        mMap.addMarker(new MarkerOptions().position(richford).title("Richford"));
        mMap.addMarker(new MarkerOptions().position(jay).title("Jay"));

        // Move the camera to focus on Vermont
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vermont));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(7));
    }
}