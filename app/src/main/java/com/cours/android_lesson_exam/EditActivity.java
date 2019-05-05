package com.cours.android_lesson_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.cours.android_lesson_exam.datas.Contact;

public class EditActivity extends AppCompatActivity {

    private Contact contact;

    private EditText nameEditText;
    private EditText firstNameEditText;
    private EditText mailEditText;
    private EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //TODO afficher le bouton de retour

        contact = new Contact();

        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_UID)) {
            //TODO récupèrer le contenu du contact passé en paramètres
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO ajouter le menu
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO gérer le choix dans le menu
    }

    //Doit être appelé par le bouton de sauvegarde
    private void save() {
        //TODO sauvegarder en base de données
    }
}
