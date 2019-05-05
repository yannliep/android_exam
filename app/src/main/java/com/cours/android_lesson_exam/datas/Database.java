package com.cours.android_lesson_exam.datas;

import androidx.room.RoomDatabase;

//TODO finir la classe
@androidx.room.Database()
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract ContactDAO contactDAO();

    public static Database getInstance() {

    }

}
