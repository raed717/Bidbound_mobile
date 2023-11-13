package com.example.bidbound.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id  ;
    @ColumnInfo
    public  String TaskName ;

    @ColumnInfo
    public  String TaskDescription ;
    @ColumnInfo
    public int Complexite ;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", TaskName='" + TaskName + '\'' +
                ", TaskDescription='" + TaskDescription + '\'' +
                ", Complexite=" + Complexite +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }

    public Task(int id, String taskName, String taskDescription, int complexite) {
        this.id = id;
        TaskName = taskName;
        TaskDescription = taskDescription;
        Complexite = complexite;
    }


    public void setTaskDescription(String taskDescription) {
        TaskDescription = taskDescription;
    }

    public int getComplexite() {
        return Complexite;
    }

    public void setComplexite(int complexite) {
        Complexite = complexite;
    }
}
