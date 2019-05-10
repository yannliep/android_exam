package com.cours.android_lesson_exam.datas;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

//TODO finir la classe
@androidx.room.Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract ContactDAO contactDAO();

    static public Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    Database.class, "database")
                    .build();
        }
        return instance;
    }

}
