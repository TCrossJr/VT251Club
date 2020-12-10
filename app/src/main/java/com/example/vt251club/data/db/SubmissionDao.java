package com.example.vt251club.data.db;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SubmissionDao {
    @Insert
    void newSubmission(SubmissionDatabase ... SubmissionDatabase);

    @Query("SELECT * FROM submissiondatabase")
    Cursor loadALL();

    @Query("SELECT image FROM submissiondatabase")
    String[] getAllImages();


}
