package com.example.vt251club;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vt251club.data.db.VisitedDatabase;


public class TownVisited extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialization of visitedDB


    }

    public void getVisited(View view) {
        VisitedDatabase getVisited = new VisitedDatabase();
    }
}
