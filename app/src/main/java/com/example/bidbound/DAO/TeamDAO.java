package com.example.bidbound.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.bidbound.entities.Team;


import java.util.List;

@Dao
public interface TeamDAO {
    @Insert
    void insertOne(Team user);
    @Delete
    void delete(Team user);
    @Query("SELECT * FROM team")
    List<Team> getAll();
}
