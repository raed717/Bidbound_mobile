package com.example.bidbound.entities;
 import androidx.room.ColumnInfo;
 import androidx.room.Entity;
 import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class user {

    @PrimaryKey(autoGenerate = true)
     public int id  ;
    @ColumnInfo
    public  String username ;

    @ColumnInfo
    public  String email ;
    @ColumnInfo
    public  String password ;

    public user( String username, String email, String password) {
         this.username = username;
        this.email = email;
        this.password = password;
    }
}
