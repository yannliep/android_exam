package com.cours.android_lesson_exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cours.android_lesson_exam.datas.Contact;
import com.cours.android_lesson_exam.datas.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        FloatingActionButton fab = findViewById(R.id.add);

        //TODO ajout des listeners
        fab.setOnClickListener(onClickListener);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(onItemLongClickListener);

        //Mise à jour de la liste
        updateList();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO démarrage de l'activité d'édition
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            startActivity(intent);
        }
    };

    private ListView.OnItemClickListener onItemClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //TODO démarrer l'activité d'édition en passant en paramètre l'id du contact sélectionné (Intent.EXTRA_UID)
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            //On récupère l'identifiant du contact sélectionnée
            Contact contact = (Contact) parent.getItemAtPosition(position);
            intent.putExtra(Intent.EXTRA_UID, contact.getId());
            startActivity(intent);
        }
    };

    private ListView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            //TODO gérer la suppression avec confirmation
            final AdapterView<?> finalParent = parent;
            final int finalPosition = position;

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(R.string.delete_title)
                    .setMessage(R.string.delete_message)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Contact contact = (Contact) finalParent.getItemAtPosition(finalPosition);
                            viewModel.deleteContact(contact);
                        }
                    }).setNegativeButton(R.string.no, null).show();
            return true;
        }
    };

    private void updateList() {
        //TODO Mettre à jour la liste avec le contenu de la base de donnée
        viewModel = ViewModelProviders.of(this)
                .get(ContactViewModel.class);

        viewModel.getAll().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                //On crée un adapter afin d'afficher un objet Category dans notre liste
                ContactAdapter adapter = new ContactAdapter(getApplicationContext(), contacts);
                //On injecte l'adapter dans notre liste
                listView.setAdapter(adapter);
            }
        });
    }
}
