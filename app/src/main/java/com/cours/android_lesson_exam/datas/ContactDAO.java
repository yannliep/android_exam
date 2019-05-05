package com.cours.android_lesson_exam.datas;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

@Dao
public interface ContactDAO {
    //TODO ajouter les requêtes

    LiveData<List<Contact>> getAll();

    LiveData<Contact> getById(int id);

    void insert(Contact contact);

    void update(Contact contact);

    void delete(Contact contact);
}
