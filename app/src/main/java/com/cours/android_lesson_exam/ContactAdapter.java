package com.cours.android_lesson_exam;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cours.android_lesson_exam.datas.Contact;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContactAdapter extends ArrayAdapter
{
    public ContactAdapter(@NonNull Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //TODO ajouter l'affichage (R.layout.contact_adapter_layout)
    }
}
