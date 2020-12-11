package com.example.vt251club.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface VisitedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setTownVisited(VisitedDatabase ... VisitedDatabase);

    @Query("SELECT townName FROM visiteddatabase WHERE visited LIKE :bool")
    String[] getVisited(Boolean bool);


}
