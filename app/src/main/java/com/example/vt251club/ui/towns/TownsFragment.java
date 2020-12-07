package com.example.vt251club.ui.towns;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vt251club.R;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;

public class TownsFragment extends Fragment {

    private TownsViewModel townsViewModel;

    private static final String JSON_FILENAME = "towns.json";
//    private static final String JSON_FILENAME = "towns_detailed.json";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        townsViewModel =
                new ViewModelProvider(requireActivity()).get(TownsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_towns, container, false);

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(getResources().getAssets().open(JSON_FILENAME),"UTF-8"));
            Gson gson = new Gson();
            TownDetails ts = new TownDetails();
            String json = gson.toJson(ts);

            TownDetails twns = gson.fromJson(reader,TownDetails.class);
            //tODO: RMV
            for( Object town : twns ) {
                System.out.println("name="+town.toString());
            }
//            java.util.Collections.sort((List<Town>)Collections.list(twns.getTowns()), Collator.getInstance());

            System.out.println("twns.size->" + twns.size() );
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
        return root;
    }
}