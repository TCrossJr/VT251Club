package com.example.vt251club;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;

public class TownDetails {

    private HashSet<Town> towns;

    public TownDetails(){
        this.towns = new HashSet<Town>();
//        this.jsonFile = "assets/towns.json";
    }

    public static TownDetails parseJSON(String response) throws IOException {
        Gson gson = new Gson();
        System.out.println("TD_Start..");
//        BufferedReader br = new BufferedReader(new FileReader("assets/towns.json"));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) { Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\t0540\\Documents\\TestVt251Club\\src\\com\\boro\\towns_detailed.json")); }
        TownDetails townsParsed = new TownDetails();
//        townsParsed = gson.fromJson(br, TownDetails.class);
        System.out.println("townsParsed->"+townsParsed.toString());
        System.out.println("TD_Done...");
//        gson.fromJson("towns.json",townsParsed.getClass());
        return townsParsed;
    }

}
