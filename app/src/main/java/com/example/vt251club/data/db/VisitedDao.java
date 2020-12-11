package com.example.vt251club.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface VisitedDao {

    @Query("SELECT townName FROM VisitedDatabase WHERE visited LIKE :bool")
    String[] getVisited(Boolean bool);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setTownVisited(VisitedDatabase ... VisitedDatabase);


}
