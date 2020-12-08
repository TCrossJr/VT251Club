package com.example.vt251club.ui.towns;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vt251club.R;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TownsFragment extends Fragment {

    private TownsViewModel townsViewModel;
    protected TownDetails town_details;
    protected TownDetailsDataAdapter town_adapter;

    private final String JSON_FILENAME = "towns.json";
    private LinearLayoutManager layoutManager;
    //    private final String JSON_FILENAME = "towns_detailed.json";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        townsViewModel =
                new ViewModelProvider(requireActivity()).get(TownsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_towns, container, false);

        RecyclerView rView = root.findViewById(R.id.towns_recycler_view);
        Button alphaButton = root.findViewById(R.id.view_alphabetically);
        Button countyButton = root.findViewById(R.id.view_county);
        TextView detailsView = root.findViewById(R.id.towns_details_view);
/*
        //TODO: RMV
        for( Object town : town_details ) {
            System.out.println("name=" + town.toString()); //RMV
            detailsView.append("\n"+town.toString());
        }
*/
//        TownDetailsDataAdapter rAdapter =
//        rView.setLayoutManager(rView.getLayoutManager());
//        layoutManager = new LinearLayoutManager(getActivity());

        town_adapter = new TownDetailsDataAdapter(town_details);
        rView.setAdapter(town_adapter);
//        rView.setAdapter(ListAdapter.class);
        return root;
    }

    private void initData() {

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(getResources().getAssets().open(JSON_FILENAME),"UTF-8"));
            Gson gson = new Gson();
/*
            // TODO: FIX ->Not used right now, objects are loaded in as objects. Not Serialized using gson //RMV
            TownDetails ts = new TownDetails();
            String json = gson.toJson(ts);
*/
            town_details = gson.fromJson(reader,TownDetails.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }
}