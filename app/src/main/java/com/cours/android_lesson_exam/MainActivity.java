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
        fab.setOnClickListener(onClickListener);
        //On récupère la liste et on ajoute la gestion du clic
        listView = findViewById(R.id.list);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(onItemLongClickListener);
        //Mise à jour de la liste
        updateList();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            MainActivity.this.startActivity(intent);
        }
    };

    private ListView.OnItemClickListener onItemClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
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
                    .setTitle("Suppression")
                    .setMessage("Êtes-vous sûr de vouloir le supprimer")
                    .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Contact contact = (Contact) finalParent.getItemAtPosition(finalPosition);
                            viewModel.delete(contact);
                        }
                    }).setNegativeButton("non", null).show();
            return true;
        }
    };

    private void updateList() {
        viewModel = ViewModelProviders.of(this)
                .get(ContactViewModel.class);

        viewModel.getAll().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                //On crée un adapter afin d'afficher un objet Category dans notre liste
                ContactAdapter adapter = new ContactAdapter(MainActivity.this, contacts);
                //On injecte l'adapter dans notre liste
                listView.setAdapter(adapter);
            }
        });
    }
}
