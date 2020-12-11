package com.example.vt251club;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class LocalMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener {

    private GoogleMap mMap;
    Marker[] markerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.local_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Markers for the towns
//        markerArray = new Marker[251];
/*
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("markers.txt"), "UTF-8"));
            String data;
            int i = 0;
            while((data = reader.readLine()) != null){
                String[] splitData = data.split("\\(|\\)|,");
                String townName = splitData[0];
                Double firstCoord = Double.parseDouble(splitData[1]);
                Double secondCoord = Double.parseDouble(splitData[2]);
                LatLng marker = new LatLng(firstCoord, secondCoord);
                markerArray[i] = mMap.addMarker(new MarkerOptions().position(marker).title(townName).visible(false));
                i++;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
*/

        // Detect zoom level change
        mMap.setOnCameraChangeListener(this);

        // Move the camera to focus on Vermont
        LatLng vermont = new LatLng(44.5588, -72.5778);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vermont));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(7));
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition){
        // Make markers invisible below zoom level 9
        for(int i = 0; i < 251; i++){
            markerArray[i].setVisible(cameraPosition.zoom > 9);
        }
    }
}