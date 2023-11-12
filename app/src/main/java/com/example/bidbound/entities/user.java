package com.example.bidbound.entities;
 import androidx.room.ColumnInfo;
 import androidx.room.Entity;
 import androidx.room.PrimaryKey;

@Entity
public class user {
    @PrimaryKey(autoGenerate = true)
     public int id  ;
    @ColumnInfo
    public  String username ;

    @ColumnInfo
    public  String email ;
    @ColumnInfo

    public  String pasword ;

    public user(int id, String username, String email, String pasword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pasword = pasword;
    }
}
