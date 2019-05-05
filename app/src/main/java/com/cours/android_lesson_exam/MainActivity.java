package com.cours.android_lesson_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cours.android_lesson_exam.datas.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        FloatingActionButton fab = findViewById(R.id.add);
        //TODO ajout des listeners

        //Mise à jour de la liste
        updateList();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //TODO démarrage de l'activité d'édition
        }
    };

    private ListView.OnItemClickListener onItemClickListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //TODO démarrer l'activité d'édition en passant en paramètre l'id du contact sélectionné (Intent.EXTRA_UID)
        }
    };

    private ListView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            //TODO gérer la suppression avec confirmation
            return false;
        }
    };

    private void updateList() {
        //TODO Mettre à jour la liste avec le contenu de la base de donnée
    }
}
