package com.cours.android_lesson_exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        Contact contact = (Contact) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_adapter_layout, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView firstname = convertView.findViewById(R.id.firstName);
        TextView mail = convertView.findViewById(R.id.mail);
        TextView phone = convertView.findViewById(R.id.phone);

        if(contact!= null){
            name.setText(contact.getFirst_name());
            firstname.setText(contact.getLast_name());
            mail.setText(contact.getMail());
            phone.setText(contact.getPhone());
        }

        return convertView;
    }
}
