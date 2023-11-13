package com.example.bidbound.DAO;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.bidbound.entities.user;
import java.util.List;

@Dao
public interface  UserDao {

    @Insert
    public  void addUser(user user);
    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    user signIn(String email, String password);












}
