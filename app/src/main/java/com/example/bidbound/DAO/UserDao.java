package com.example.bidbound.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bidbound.entities.user;

import java.util.ArrayList;

@Dao
public interface  UserDao {

    @Insert
    public  void addUser(user user);

    @Query("Select * from user")
    public ArrayList<user> getAll();








}
