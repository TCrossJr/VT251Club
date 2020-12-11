package com.example.vt251club.ui.towns;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.vt251club.R;

public class TownDetailsFragment extends Fragment {
    private TextView displayDetails;
    TextView detailsTownName;
    TextView detailsTownCounty;
    TextView detailsTownZip;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_town_details, container, false);
        detailsTownName = view.findViewById(R.id.details_town_name);
        detailsTownCounty = view.findViewById(R.id.details_town_county);
        detailsTownZip = view.findViewById(R.id.details_town_zip);
        Town t = (Town) getArguments().getSerializable("t");
        detailsTownName.setText(t.get_town());
        detailsTownCounty.setText(t.get_county());

        Log.d("wtf", "wtf");
        return view;
    }
}
