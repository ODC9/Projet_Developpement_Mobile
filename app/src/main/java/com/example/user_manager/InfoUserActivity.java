package com.example.user_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class InfoUserActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        // Recuperer les positions aue doivent prendre les valeurs de la table dans la liste
        SQLiteDatabase db = openOrCreateDatabase("BDUtilisateur", Context.MODE_PRIVATE, null);
        list = findViewById(R.id.id_list);
        final Cursor c = db.rawQuery("select * from utilisateur", null);
        int id = c.getColumnIndex("id");
        int prenom = c.getColumnIndex("prenom");
        int nom = c.getColumnIndex("nom");
        int classe = c.getColumnIndex("classe");
        int mail = c.getColumnIndex("email");
        int mdp = c.getColumnIndex("motDePasse");
        titles.clear();

        // Afficher au niveau de la liste
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        list.setAdapter(arrayAdapter);
        final ArrayList<Utilisateur> etu = new ArrayList<Utilisateur>();
        if (c.moveToFirst()) {
            do {
                Utilisateur user = new Utilisateur();
                user.id = c.getString(id);
                user.prenom = c.getString(prenom);
                user.nom = c.getString(nom);
                user.classe = c.getString(classe);
                user.email = c.getString(mail);
                user.motDePasse = c.getString(mdp);

                etu.add(user);
                titles.add(c.getString(id) + "\n" + c.getString(prenom) + "\n" + c.getString(nom) + "\n" + c.getString(classe) +  "\n"
                        + c.getString(mail) + "\n" + c.getString(mdp) + "\n");
            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list.invalidateViews();
        }

        // Selection d'un element
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                Utilisateur user = etu.get(position);
                Intent i = new Intent(InfoUserActivity.this, EditUserActivity.class);
                i.putExtra("id", user.id);
                i.putExtra("prenom", user.prenom);
                i.putExtra("nom", user.nom);
                i.putExtra("classe", user.classe);
                i.putExtra("mail", user.email);
                i.putExtra("mdp", user.motDePasse);
                startActivity(i);
            }
        });


    }
}