package com.example.vt251club.data.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SubmissionDatabase {
    @PrimaryKey(autoGenerate = true)
    public int postID;

    @ColumnInfo(name = "town")
    public String townName;

    @ColumnInfo(name = "text")
    public String submissionText;

    @ColumnInfo(name = "date", defaultValue = "CURRENT_TIMESTAMP")
    public String date;

    @ColumnInfo(name = "image", defaultValue = "NULL")
    public String imageUri;
}
