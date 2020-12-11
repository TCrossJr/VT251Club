package com.example.vt251club;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vt251club.data.db.AppDatabase;
import com.example.vt251club.data.db.VisitedDatabase;


public class TownVisited extends AppCompatActivity {
    AppDatabase visitedDB;
    boolean getVisited;
    VisitedDatabase setTownVisited = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialization of visitedDB
        visitedDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "VisitedDatabase").allowMainThreadQueries().build();

        visitedDB.VisitedDao().setTownVisited(setTownVisited);
    }

    public void getVisited(View view) {
        VisitedDatabase getVisited = new VisitedDatabase();
    }
}
