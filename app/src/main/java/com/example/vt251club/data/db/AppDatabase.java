package com.example.vt251club.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(version = 1, entities = {SubmissionDatabase.class, VisitedDatabase.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract SubmissionDao SubmissionDao();

    public abstract VisitedDao VisitedDao();


}