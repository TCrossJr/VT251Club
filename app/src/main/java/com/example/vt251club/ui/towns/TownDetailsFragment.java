package com.example.vt251club.ui.towns;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vt251club.R;

public class TownDetailsFragment extends AppCompatActivity {
    private TextView displayDetails;
    TextView detailsTownName;
    TextView detailsTownCounty;
    TextView detailsTownZip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_town_details);

        detailsTownName = findViewById(R.id.details_town_name);
        detailsTownCounty = findViewById(R.id.details_town_county);
        detailsTownZip = findViewById(R.id.details_town_zip);

        //TODO: RMV
        Town test = new Town("Test", "TestCounty", "05401", "1776", 17000, 44.0, -73.0);
        detailsTownName.setText(test.get_town());
        detailsTownCounty.setText(test.get_county());
    }
}
