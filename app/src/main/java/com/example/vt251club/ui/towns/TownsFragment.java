package com.example.vt251club.ui.towns;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vt251club.MainActivity;
import com.example.vt251club.R;

public class TownsFragment extends Fragment {

    private TownsViewModel townsViewModel;

    protected TownDetailsDataAdapter town_adapter;
    protected LinearLayoutManager layoutManager;


    private RecyclerView rView;
    private Button alphaButton;
    private Button countyButton;
    private TextView detailsView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        townsViewModel =
                new ViewModelProvider(requireActivity()).get(TownsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_towns, container, false);

        rView = root.findViewById(R.id.towns_recycler_view);
        detailsView = root.findViewById(R.id.towns_details_view);
        alphaButton = root.findViewById(R.id.view_alphabetically);
        countyButton = root.findViewById(R.id.view_county);

        layoutManager = new LinearLayoutManager(getActivity());
//        town_adapter = new TownDetailsDataAdapter(MainActivity.town_details_alpha);
        town_adapter = new TownDetailsDataAdapter(MainActivity.town_details_county);
        town_adapter.notifyDataSetChanged();

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        rView.addItemDecoration(divider);
        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(town_adapter);
        alphaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                town_adapter = new TownDetailsDataAdapter(MainActivity.town_details_alpha);
                town_adapter.notifyDataSetChanged();
                rView.setAdapter(town_adapter);
            }
        });
        countyButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                town_adapter = new TownDetailsDataAdapter(MainActivity.town_details_county);
                town_adapter.notifyDataSetChanged();
                rView.setAdapter(town_adapter);
            }
        });
        return root;
    }
}