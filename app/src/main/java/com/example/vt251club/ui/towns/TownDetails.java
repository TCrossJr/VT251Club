package com.example.vt251club.ui.towns;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TownDetails extends HashSet {

    private ArrayList<Town> towns;

    public TownDetails(){
        towns = new ArrayList<>();
    }

    public List<Town> getTowns() {
        return towns;
    }

    public Town getSingleTown( int position) {
        return towns.get(position);
    }
}
