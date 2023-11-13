package com.example.bidbound.entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


@Entity
public class Project implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id  ;
    @ColumnInfo
    public  String projectName ;

    @ColumnInfo
    public  String projectCategory ;
    @ColumnInfo
    public Date startDate ;

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Project(String projectName, String projectCategory, Date startDate) {
        this.projectName = projectName;
        this.projectCategory = projectCategory;
        this.startDate = startDate;
    }
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectCategory='" + projectCategory + '\'' +
                ", startDate=" + startDate +
                '}';
    }

}
