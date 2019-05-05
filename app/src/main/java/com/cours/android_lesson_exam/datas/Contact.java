package com.cours.android_lesson_exam.datas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String last_name;

    private String first_name;

    private String phone;

    private String mail;

    //TODO ajouter getters/setters
}
