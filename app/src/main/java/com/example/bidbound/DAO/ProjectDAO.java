package com.example.bidbound.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bidbound.entities.Project;

import java.util.List;

@Dao
public interface ProjectDAO {

    @Insert
    long insertProject(Project project);

    @Update
    void updateProject(Project project);

    @Delete
    void deleteProject(Project project);

    @Query("SELECT * FROM project")
    List<Project> getAllProjects();

    @Query("SELECT * FROM project WHERE id = :projectId")
    Project getProjectById(int projectId);

    @Query("SELECT * FROM project WHERE projectName LIKE :query")
    List<Project> searchProjects(String query);

}

