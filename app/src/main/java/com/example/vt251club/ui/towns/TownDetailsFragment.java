package com.example.vt251club.ui.towns;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vt251club.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TownDetailsFragment extends AppCompatActivity implements OnMapReadyCallback {

    public static Town currentTown;
    private TextView displayTown, displayCounty, displayZip, displayEst, displayAcres, displayLat, displayLong;
    private GoogleMap gMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_town_details);

        displayTown = findViewById(R.id.details_town_name);
        displayCounty = findViewById(R.id.details_town_county);
        displayZip = findViewById(R.id.details_town_zip);
        displayEst = findViewById(R.id.details_town_established);
        displayAcres = findViewById(R.id.details_town_acres);
        displayLat = findViewById(R.id.details_town_latitude);
        displayLong = findViewById(R.id.details_town_longitude);

        displayTown.setText(currentTown.get_town());
        displayCounty.setText(currentTown.get_county()+" County");
        displayZip.setText(currentTown.get_zip());
        displayEst.setText("Est. "+currentTown.get_established());
        displayAcres.setText(String.valueOf(currentTown.get_acres())+" Acres");
        displayLat.setText(String.valueOf("Lat:    "+currentTown.get_latitude()));
        displayLong.setText(String.valueOf("Long: "+currentTown.get_longitude()));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.local_map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng town =new LatLng(currentTown.get_latitude(),currentTown.get_longitude());
        gMap.addMarker(new MarkerOptions().position(town).title(currentTown.get_town()));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(town));
        gMap.moveCamera(CameraUpdateFactory.zoomTo((float)7.5));
    }
}
