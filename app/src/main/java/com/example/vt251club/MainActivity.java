package com.example.vt251club;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.vt251club.ui.towns.Town;
import com.example.vt251club.ui.towns.TownDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
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

public class MainActivity extends AppCompatActivity {

    private final String JSON_FILENAME = "towns.json";
//    private final String JSON_FILENAME = "towns_detailed.json";

    private AppBarConfiguration mAppBarConfiguration;
    protected ArrayList<Town> town_details_issorted;
    public static TownDetails town_details_alpha;
    public static TownDetails town_details_county;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Starting New Entry...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent newSubmission = new Intent(getApplicationContext(), Submission.class);
                startActivity(newSubmission);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_towns, R.id.nav_progress)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // initialize town data
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public boolean onClick1(MenuItem item) {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
        return true;
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
            gBuilder.registerTypeAdapter(Town.class, new TownTypeAdaptor());
            gBuilder.create();

            Type tDetailsType = new TypeToken<ArrayList<Town>>() {
            }.getType();
            town_details_issorted = new ArrayList<>();
            town_details_alpha = new TownDetails();

            Gson gson = gBuilder.create();
            town_details_issorted = gson.fromJson(reader, tDetailsType);
            town_details_alpha.addAll(town_details_issorted);

            town_details_county = new TownDetails();
            sortTownByCounty(town_details_issorted);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }

    private void sortTownByCounty(ArrayList<Town> townList) {
        ArrayList<Town> addison = new ArrayList<>(0);
        ArrayList<Town> bennington = new ArrayList<>(0);
        ArrayList<Town> caledonia = new ArrayList<>(0);
        ArrayList<Town> chittenden = new ArrayList<>(0);
        ArrayList<Town> essex = new ArrayList<>(0);
        ArrayList<Town> franklin = new ArrayList<>(0);
        ArrayList<Town> grandIsle = new ArrayList<>(0);
        ArrayList<Town> lamoille = new ArrayList<>(0);
        ArrayList<Town> orange = new ArrayList<>(0);
        ArrayList<Town> orleans = new ArrayList<>(0);
        ArrayList<Town> rutland = new ArrayList<>(0);
        ArrayList<Town> washington = new ArrayList<>(0);
        ArrayList<Town> windham = new ArrayList<>(0);
        ArrayList<Town> windsor = new ArrayList<>(0);

        ArrayList<Town> result = new ArrayList<>(0);

        for( int i = 0; i < townList.size(); i++ ) {
            Town t = new Town(townList.get(i));
            switch(t.get_county()) {
                case "Addison":
                    addison.add(t);
                    break;
                case "Bennington":
                    bennington.add(t);
                    break;
                case "Caledonia":
                    caledonia.add(t);
                    break;
                case "Chittenden":
                    chittenden.add(t);
                    break;
                case "Essex":
                    essex.add(t);
                    break;
                case "Franklin":
                    franklin.add(t);
                    break;
                case "Grand Isle":
                    grandIsle.add(t);
                    break;
                case "Lamoille":
                    lamoille.add(t);
                    break;
                case "Orange":
                    orange.add(t);
                    break;
                case "Orleans":
                    orleans.add(t);
                    break;
                case "Rutland":
                    rutland.add(t);
                    break;
                case "Washington":
                    washington.add(t);
                    break;
                case "Windham":
                    windham.add(t);
                    break;
                case "Windsor":
                    windsor.add(t);
                    break;
            }
        }
        result.addAll(addison);
        result.addAll(bennington);
        result.addAll(caledonia);
        result.addAll(chittenden);
        result.addAll(essex);
        result.addAll(franklin);
        result.addAll(grandIsle);
        result.addAll(lamoille);
        result.addAll(orange);
        result.addAll(orleans);
        result.addAll(rutland);
        result.addAll(washington);
        result.addAll(windham);
        result.addAll(windsor);

        town_details_county = new TownDetails();
        town_details_county.addAll(result);
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