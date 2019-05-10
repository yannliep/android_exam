package com.cours.android_lesson_exam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.cours.android_lesson_exam.datas.Contact;
import com.cours.android_lesson_exam.datas.ContactViewModel;

public class EditActivity extends AppCompatActivity {

    private Contact contact;

    private ContactViewModel viewModel;

    private EditText nameEditText;
    private EditText firstNameEditText;
    private EditText mailEditText;
    private EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        viewModel = ViewModelProviders.of(this)
                .get(ContactViewModel.class);

        nameEditText = findViewById(R.id.name);
        firstNameEditText = findViewById(R.id.firstName);
        mailEditText = findViewById(R.id.mail);
        phoneEditText = findViewById(R.id.phone);

        //TODO afficher le bouton de retour
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        contact = new Contact();

        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_UID)) {
            //TODO récupèrer le contenu du contact passé en paramètres
            int id = intent.getIntExtra(Intent.EXTRA_UID, 0);
            if (id > 0) {
                //On cherche l'identifiant dans la base de données
                viewModel.getById(id).observe(this, new Observer<Contact>() {

                    @Override
                    public void onChanged(Contact contactFound) {
                        if (contactFound != null) {
                            contact = contactFound;
                            nameEditText.setText(contact.getLast_name());
                            firstNameEditText.setText(contact.getFirst_name());
                            mailEditText.setText(contact.getMail());
                            phoneEditText.setText(contact.getPhone());
                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO ajouter le menu
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO gérer le choix dans le menu
        switch (item.getItemId()) {
            case R.id.save:
                save();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    //Doit être appelé par le bouton de sauvegarde
    private void save() {
        //TODO sauvegarder en base de données
        //On récupère le nom et le type de catégorie et on les injecte dans le modèle
        contact.setLast_name(nameEditText.getText().toString());
        contact.setFirst_name(firstNameEditText.getText().toString());
        contact.setMail(mailEditText.getText().toString());
        contact.setPhone(phoneEditText.getText().toString());
        //On injecte le modèle dans la base de données
        viewModel.insertOrUpdateContact(contact);
        //On ferme l'activité
        finish();
    }
}
