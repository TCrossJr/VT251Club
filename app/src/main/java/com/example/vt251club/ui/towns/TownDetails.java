package com.example.vt251club.ui.towns;


import java.util.ArrayList;
import java.util.List;

public class TownDetails extends ArrayList {

    private ArrayList<Town> towns;

    public TownDetails(){
        towns = new ArrayList<>();
    }

    public TownDetails(ArrayList<Town> townDetailsList) {
        towns = new ArrayList<>(townDetailsList);
    }

    public List<Town> getTowns() {
        return towns;
    }

    public Town getSingleTown( int position) {
        return (Town) this.get(position);
    }
}
