package com.example.vt251club.data.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VisitedDatabase {
    @PrimaryKey(autoGenerate = false)
    public String townName;

    @ColumnInfo(name = "visited", defaultValue = "false")
    public Boolean visitedBool;
}
