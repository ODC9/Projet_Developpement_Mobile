package com.example.user_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EditUserActivity extends AppCompatActivity {

    TextInputEditText id, prenom, nom, classe, email, motDePasse;
    Button btnModif, btnSup;
    ImageView retour;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        retour = findViewById(R.id.btnRetourModif);
        id = findViewById(R.id.txtid);
        prenom = findViewById(R.id.txtPrenom);
        nom = findViewById(R.id.txtNom);
        classe = findViewById(R.id.txtClasse);
        email = findViewById(R.id.txtEmail);
        motDePasse = findViewById(R.id.txtMotDePasse);

        btnModif = findViewById(R.id.btnModif);
        btnSup = findViewById(R.id.btnSupp);

        Intent i = getIntent();
        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("nom").toString();
        String t3 = i.getStringExtra("prenom").toString();
        String t4 = i.getStringExtra("classe").toString();
        String t5 = i.getStringExtra("mail").toString();
        String t6 = i.getStringExtra("mdp").toString();

        id.setText(t1);
        nom.setText(t2);
        prenom.setText(t3);
        classe.setText(t4);
        email.setText(t5);
        motDePasse.setText(t6);

        btnModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });

        btnSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InfoUserActivity.class);
                startActivity(i);
            }
        });

    }

    public void Edit()
    {
        try
        {
            String num = id.getText().toString();
            String name = nom.getText().toString();
            String surname = prenom.getText().toString();
            String clas = classe.getText().toString();
            String mail = email.getText().toString();
            String pass = motDePasse.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BDUtilisateur",Context.MODE_PRIVATE,null);
            String sql = "update utilisateur set prenom = ?, nom = ?, classe=?, email = ?, motDePasse = ? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,surname);
            statement.bindString(2,name);
            statement.bindString(3,clas);
            statement.bindString(4,mail);
            statement.bindString(5,pass);
            statement.bindString(6,num);
            statement.execute();
            Toast.makeText(this,"Modification reussi",Toast.LENGTH_LONG).show();
            prenom.setText("");
            nom.setText("");
            classe.setText("");
            email.setText("");
            motDePasse.setText("");
            id.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Modification echouee",Toast.LENGTH_LONG).show();
        }
    }

    public void Delete()
    {
        try {
            String num = id.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("BDUtilisateur", Context.MODE_PRIVATE, null);
            String sql = "delete from utilisateur where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, num);
            statement.execute();
            Toast.makeText(this, "Enregistrement supprimé", Toast.LENGTH_LONG).show();
            nom.setText("");
            prenom.setText("");
            classe.setText("");
            email.setText("");
            motDePasse.setText("");
            id.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Suppression echouée",Toast.LENGTH_LONG).show();
        }
    }
}