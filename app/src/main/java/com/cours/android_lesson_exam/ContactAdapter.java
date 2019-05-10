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
        Contact contact = (Contact) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_adapter_layout, parent, false);
        }

        TextView first_name = convertView.findViewById(R.id.firstName);
        TextView last_name = convertView.findViewById(R.id.name);
        TextView mail = convertView.findViewById(R.id.mail);
        TextView phone = convertView.findViewById(R.id.phone);

        if (contact != null) {
            first_name.setText(contact.getFirst_name());
            last_name.setText(contact.getLast_name());
            mail.setText(contact.getMail());
            phone.setText(contact.getPhone());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return this.getView(position, convertView, parent);
    }
}
