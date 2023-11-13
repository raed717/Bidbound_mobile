//package com.example.bidbound.DAO;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.example.bidbound.entities.Task;
//
//import java.util.List;
//@Dao
//public interface TaskDAO {
//    @Insert
//    long insertTask(Task task);
//
//    @Update
//    void updateTask(Task task);
//
//    @Delete
//    void deleteTask(Task task);
//
//    @Query("SELECT * FROM task")
//    List<Task> getAllTasks();
//
//    @Query("SELECT * FROM task WHERE id = :taskId")
//    Task getTaskById(int taskId);
//
//    @Query("SELECT * FROM task WHERE taskName LIKE :query")
//    List<Task> searchTasks(String query);
//}
