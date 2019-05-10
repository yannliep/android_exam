package com.cours.android_lesson_exam.datas;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ContactViewModel extends AndroidViewModel {
    private ContactDAO contactDAO;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactDAO = Database.getInstance(application.getApplicationContext())
                .contactDAO();
    }


    public LiveData<List<Contact>> getAll() {
        return contactDAO.getAll();
    }

    public LiveData<Contact> getById(int id) {
        return contactDAO.getById(id);
    }

    public void insertOrUpdateContact(Contact contact) {
        new InsertOrUpdateTask(contactDAO).execute(contact);
    }

    public void deleteContact(Contact contact) {
        new DeleteTask(contactDAO).execute(contact);
    }

    private static class InsertOrUpdateTask extends
            AsyncTask<Contact, Void, Void> {
        private ContactDAO contactDAO;

        public InsertOrUpdateTask(ContactDAO contactDAO) {
            this.contactDAO = contactDAO;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            Contact contact = contacts[0];
            if (contact.getId() > 0) {
                contactDAO.update(contact);
            } else {
                contactDAO.insert(contact);
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Contact, Void, Void> {
        private ContactDAO contactDAO;

        public DeleteTask(ContactDAO contactDAO) {
            this.contactDAO = contactDAO;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            Contact contact = contacts[0];
            contactDAO.delete(contact);
            return null;
        }
    }
}

