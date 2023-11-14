package com.example.bidbound.database;
import android.content.Context;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bidbound.DAO.ProjectDAO;
import com.example.bidbound.DAO.TeamDAO;
import com.example.bidbound.DAO.UserDao;
import com.example.bidbound.entities.Converters;
import com.example.bidbound.entities.Project;
import com.example.bidbound.entities.user;
import com.example.bidbound.entities.Team;




@Database(entities = {user.class,Team.class, Project.class} , version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {


    private static  AppDatabase instance ;
    public abstract UserDao userDAO();
    public abstract TeamDAO teamDAO ();
    public abstract ProjectDAO projectDAO();


    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "BidboundBase")
                    .allowMainThreadQueries()
                    .build();
            if (instance == null) {
                Log.e("AppDatabase", "Database instance is null after creation");
            } else {
                Log.d("AppDatabase", "Database created!");
            }

        }
        return instance;
    }

}
