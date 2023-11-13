//package com.example.bidbound.database;
//
//import android.content.Context;
//
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.room.TypeConverters;
//
//import com.example.bidbound.DAO.ProjectDAO;
////import com.example.bidbound.DAO.TaskDAO;
//import com.example.bidbound.entities.Converters;
//import com.example.bidbound.entities.Project;
//import com.example.bidbound.entities.Task;
//
//@androidx.room.Database(entities = {Project.class}, version = 1, exportSchema = false)
//@TypeConverters(Converters.class)
//public abstract class AppDatabase extends RoomDatabase {
//
//
//    private static  AppDatabase instance ;
//    public abstract ProjectDAO projectDAO();
////    public abstract TaskDAO taskDAO();
//
//
//    public static AppDatabase getAppDatabase(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "BidboundBase")
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return instance;
//    }
//
//}