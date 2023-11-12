package com.example.bidbound.database;
import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.bidbound.DAO.UserDao;
import com.example.bidbound.entities.user;



@androidx.room.Database(entities = {user.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    private static  AppDatabase instance ;
    public abstract UserDao userDAO();


    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "BidboundBase")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
