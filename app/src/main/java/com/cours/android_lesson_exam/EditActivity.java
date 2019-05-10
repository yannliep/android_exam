package com.cours.android_lesson_exam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.cours.android_lesson_exam.datas.Contact;
import com.cours.android_lesson_exam.datas.ContactViewModel;

public class EditActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;
    private Contact contact;

    private EditText nameEditText;
    private EditText firstNameEditText;
    private EditText mailEditText;
    private EditText phoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        contact = new Contact();

        Intent intent = getIntent();

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);
        nameEditText = findViewById(R.id.name);
        firstNameEditText = findViewById(R.id.firstName);
        mailEditText = findViewById(R.id.mail);
        phoneEditText = findViewById(R.id.phone);

        if (intent.hasExtra(Intent.EXTRA_UID)) {
            int id = intent.getIntExtra(Intent.EXTRA_UID, 0);
            if (id > 0) {
                contactViewModel.getById(id).observe(this, new Observer<Contact>() {
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
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        //On récupère le nom et le type de catégorie et on les injecte dans le modèle
        contact.setLast_name(nameEditText.getText().toString());
        contact.setFirst_name(firstNameEditText.getText().toString());
        contact.setMail(mailEditText.getText().toString());
        contact.setPhone(phoneEditText.getText().toString());
        StringBuilder sb = new StringBuilder();
        if (contact.getFirst_name() == null || "".equals(contact.getFirst_name())) {
            sb.append(" - votre Nom de famille n'est pas écrit\n");
            new AlertDialog.Builder(EditActivity.this)
                    .setTitle("Donnée non valide")
                    .setMessage("")
                    .setNeutralButton("ok", null).show();
        }
        if (contact.getLast_name() == null || "".equals(contact.getLast_name())) {
            sb.append(" - votre Prénom n'est pas écrit\n");
            new AlertDialog.Builder(EditActivity.this)
                    .setTitle("Donnée non valide")
                    .setMessage("votre Prénom n'est pas écrit")
                    .setNeutralButton("ok", null).show();
        }
        if (!isMailValid(contact.getMail())) {
            sb.append(" - votre mail n'est pas juste\n");
        }
        if (!isPhoneValid(contact.getPhone())) {
            sb.append(" - votre numéro de telephone n'est pas juste\n");
        }
        if (sb.toString().equals("")) {
            //On injecte le modèle dans la base de données
            contactViewModel.insertOrUpdate(contact);
            //On ferme l'activité
            finish();
        } else {
            new AlertDialog.Builder(EditActivity.this)
                    .setTitle("Donnée non valide")
                    .setMessage("Plusieur donné ne sont pas juste :\n" + sb.toString())
                    .setNeutralButton("ok", null).show();
        }
    }

    private boolean isMailValid(String mail) {
        return mail.matches("[A-z0-9_\\-\\.]+@[A-z0-9\\.]*[A-z0-9]{2,5}");
    }

    private boolean isPhoneValid(String phone) {
        return phone.matches("(\\+[0-9]{11})|(0[0-9]{9})");
    }
}
