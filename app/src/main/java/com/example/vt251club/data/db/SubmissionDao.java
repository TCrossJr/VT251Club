package com.example.vt251club.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SubmissionDao {
    @Insert
    void newSubmission(SubmissionDatabase ... SubmissionDatabase);

    @Query("SELECT * FROM submissiondatabase")
    String[] loadALL();

    @Query("SELECT image FROM submissiondatabase")
    String[] getAllImages();


}
