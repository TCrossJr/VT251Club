package com.example.vt251club.ui.towns;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TownDetails extends HashSet {

    private List<Town> towns;

    public TownDetails(){
        this.towns = new ArrayList<>();
    }

    public List<Town> getTowns() {
        return towns;
    }
}
