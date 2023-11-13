package com.example.bidbound.entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class Member {

    @PrimaryKey(autoGenerate = true)
    public int id  ;
    @ColumnInfo
    public  String memberFirstName ;

    @ColumnInfo
    public  String memberLastName ;
    @ColumnInfo
    public Date birthDate ;

    public int getId() {
        return id;
    }

    public String getmemberFirstName() {
        return memberFirstName;
    }

    public void setMemberFirstName(String memberFirstName) {
        this.memberFirstName = memberFirstName;
    }
    public String getmemberLastName() {
        return memberLastName;
    }

    public void setMemberLastName(String memberLastName) {
        this.memberLastName = memberLastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setbirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Member(String memberFirstName, String memberLastName, Date birthDate) {
        this.memberFirstName = memberFirstName;
        this.memberLastName = memberLastName;
        this.birthDate =birthDate;
    }
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberFirstName='" + memberFirstName + '\'' +
                ", memberLastName='" + memberLastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
