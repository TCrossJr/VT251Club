package com.example.vt251club.ui.towns;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vt251club.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TownsFragment extends Fragment {

    private TownsViewModel townsViewModel;
    protected TownDetails town_details;
    protected ArrayList<Town> town_details_issorted;
    protected TownDetailsDataAdapter town_adapter;
    protected LinearLayoutManager layoutManager;


    private final String JSON_FILENAME = "towns.json";
    private RecyclerView rView;
    private Button alphaButton;
    private Button countyButton;
    private TextView detailsView;
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

        rView = root.findViewById(R.id.towns_recycler_view);
        alphaButton = root.findViewById(R.id.view_alphabetically);
        countyButton = root.findViewById(R.id.view_county);
        detailsView = root.findViewById(R.id.towns_details_view);

        layoutManager = new LinearLayoutManager(getActivity());
        town_adapter = new TownDetailsDataAdapter(town_details);

        rView.setLayoutManager(layoutManager);
        rView.scrollToPosition(0);
        rView.setAdapter(town_adapter);
        return root;
    }

    private void initData() {

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(getResources().getAssets().open(JSON_FILENAME), "UTF-8"));
            GsonBuilder gBuilder = new GsonBuilder();
            gBuilder.registerTypeAdapter(Town.class, new JsonSerializer<Town>() {
                @Override
                public JsonElement serialize(Town src, Type typeOfSrc, JsonSerializationContext context) {
                    JsonObject jsonObj = new JsonObject();
                    jsonObj.addProperty("name", "");
                    jsonObj.addProperty("county", "");
                    jsonObj.addProperty("zip", "");
                    jsonObj.addProperty("established", "");
                    jsonObj.addProperty("acres", 0);
                    jsonObj.addProperty("latitude", 45.0);
                    jsonObj.addProperty("longitude", -73.0);

                    return jsonObj;
                }
            });
            gBuilder.registerTypeAdapter(Town.class, new JsonDeserializer<Town>() {
                @Override
                public Town deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    JsonObject jsonObj = json.getAsJsonObject();
                    String town = jsonObj.get("town").getAsString();
                    String county = jsonObj.get("county").getAsString();
                    String zip = jsonObj.get("zip").getAsString();
                    String established = jsonObj.get("established").getAsString();
                    int acres = jsonObj.get("acres").getAsInt();
                    double latitude = jsonObj.get("latitude").getAsDouble();
                    double longitude = jsonObj.get("longitude").getAsDouble();

                    return new Town(town, county, zip, established, acres, latitude, longitude);
                }
            });
            gBuilder.registerTypeAdapter(TownDetails.class, new JsonSerializer<TownDetails>() {
                @Override
                public JsonElement serialize(TownDetails src, Type typeOfSrc, JsonSerializationContext context) {
                    return null;
                }
            });
            gBuilder.registerTypeAdapter(TownDetails.class, new JsonDeserializer<TownDetails>() {
                @Override
                public TownDetails deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return null;
                }
            });
            gBuilder.registerTypeAdapter(Town.class, new TownTypeAdaptor());
            gBuilder.registerTypeAdapter(TownDetails.class, new TownDetailsTypeAdaptor());
            gBuilder.create();

            Type tDetailsType = new TypeToken<ArrayList<Town>>() {
            }.getType();
            town_details_issorted = new ArrayList<>();
            town_details = new TownDetails();
            Gson gson = gBuilder.create();
            town_details_issorted = gson.fromJson(reader, tDetailsType);
            town_details.addAll(town_details_issorted);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }


    private class TownDetailsTypeAdaptor extends TypeAdapter<TownDetails> {

        @Override
        public void write(JsonWriter out, TownDetails value) throws IOException {

        }

        @Override
        public TownDetails read(JsonReader in) throws IOException {
            return null;
        }
    }

    private class TownTypeAdaptor extends TypeAdapter<Town> {
        @Override
        public void write(JsonWriter out, Town town) throws IOException {
            out.beginObject();

            //town
            out.name("town");
            out.value(town.get_town());
            //county
            out.name("county");
            out.value(town.get_county());
            //zip
            out.name("zip");
            out.value(town.get_zip());
            //established
            out.name("established");
            out.value(town.get_established());
            //acres
            out.name("acres");
            out.value(town.get_acres());
            //latitude
            out.name("latitude");
            out.value(town.get_latitude());
            //longitude
            out.name("longitude");
            out.value(town.get_longitude());

            out.endObject();
        }

        @Override
        public Town read(JsonReader in) throws IOException {
            final Town town = new Town();
            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {

                    case "town":
                        town.set_town(in.nextString());
                        break;
                    case "county":
                        town.set_county(in.nextString());
                        break;
                    case "zip":
                        town.set_zip(in.nextString());
                        break;
                    case "established":
                        town.set_established(in.nextString());
                        break;
                    case "acres":
                        town.set_acres(in.nextInt());
                        break;
                    case "latitude":
                        town.set_latitude(in.nextDouble());
                        break;
                    case "longitude":
                        town.set_longitude(in.nextDouble());
                        break;
                }
            }
            in.endObject();
            return town;
        }
    }
}