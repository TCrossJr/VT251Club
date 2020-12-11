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

    @Query("SELECT postID FROM SubmissionDatabase")
    Integer[] getAllPostId();

    @Query("SELECT image FROM submissiondatabase WHERE postID LIKE :id")
    String getAllImageFromID(int id);

    @Query("SELECT town FROM submissiondatabase WHERE postID LIKE :id")
    String getAllTownFromID(int id);

    @Query("SELECT text FROM submissiondatabase WHERE postID LIKE :id")
    String getAllTextFromID(int id);
}
