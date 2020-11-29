package com.example.vt251club;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class TownDetails {

    private HashSet<Town> towns;

    public TownDetails(){
        this.towns = new HashSet<Town>();
//        this.jsonFile = "assets/towns.json";
    }

    public static TownDetails parseJSON(String response) throws IOException {
        Gson gson = new Gson();
        System.out.println("Start..");
        BufferedReader br = new BufferedReader(new FileReader("assets/towns.json"));
        TownDetails townsParsed = new TownDetails();
        townsParsed = gson.fromJson(br, TownDetails.class);
        System.out.println("townsParsed->"+townsParsed.toString());
        System.out.println("Done...");
//        gson.fromJson("towns.json",townsParsed.getClass());
        return townsParsed;
    }

}
