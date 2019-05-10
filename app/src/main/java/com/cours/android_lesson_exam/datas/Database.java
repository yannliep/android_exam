package com.cours.android_lesson_exam.datas;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(entities = {Contact.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract ContactDAO contactDAO();

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, Database.class, "database").build();
        }
        return instance;
    }

}
