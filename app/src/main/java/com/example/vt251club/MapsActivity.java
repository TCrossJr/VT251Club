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
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Polygon berkshireShape = mMap.addPolygon(new PolygonOptions().strokeColor(Color.RED).add(
                new LatLng(45.02128253660609, -72.83182178085383),
                new LatLng(45.02123247119619, -72.80046441489705),
                new LatLng(45.02104532856465, -72.79521722904714),
                new LatLng(45.021127195594275, -72.77599825099414),
                new LatLng(45.02010334858125, -72.73808124984471),
                new LatLng(45.019841723884205, -72.71002316786307),
                new LatLng(45.01960932737873, -72.70384800233701),
                new LatLng(45.000126521357174, -72.6981219676198),
                new LatLng(44.96651737576963, -72.68804054029833),
                new LatLng(44.929955713266345, -72.67428551725183),
                new LatLng(44.9284803825526, -72.7079216158872),
                new LatLng(44.92571726270663, -72.71360561652031),
                new LatLng(44.92660804759477, -72.7176247466183),
                new LatLng(44.928082868668014, -72.71934068516792),
                new LatLng(44.92701922455438, -72.7247036809201),
                new LatLng(44.92485521715947, -72.78331942599691),
                new LatLng(44.92660090403672, -72.82240057753967),
                new LatLng(45.000284591927986, -72.82889653505107),
                new LatLng(45.02128253660609, -72.83182178085383)
        ));

        // Add a marker in Sydney and move the camera
        LatLng vermont = new LatLng(44.5588, -72.5778);
        LatLng origin = new LatLng(0, 0);
        LatLng berkshire = new LatLng(44.9717, -72.7754);
        mMap.addMarker(new MarkerOptions().position(vermont).title("Marker in Vermont"));
        mMap.addMarker(new MarkerOptions().position(origin).title("Marker at origin"));
        mMap.addMarker(new MarkerOptions().position(berkshire).title("Marker in Berkshire"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vermont));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(7));
    }
}