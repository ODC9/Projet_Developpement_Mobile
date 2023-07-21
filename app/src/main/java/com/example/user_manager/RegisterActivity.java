package com.example.user_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText prenom, nom, classe, email, motDePasse;
    Button btnAjout, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prenom = findViewById(R.id.txtPrenom);
        nom = findViewById(R.id.txtNom);
        classe = findViewById(R.id.txtClasse);
        email = findViewById(R.id.txtEmail);
        motDePasse = findViewById(R.id.txtMotDePasse);
        btnAjout = findViewById(R.id.btnAjout);
        btnList = findViewById(R.id.btnList);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), InfoUserActivity.class);
                startActivity(i);
            }
        });

        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });


    }

    public void insert()
    {
        try
        {
            String txtprenom = prenom.getText().toString();
            String txtnom = nom.getText().toString();
            String txtclasse = classe.getText().toString();
            String txtmail = email.getText().toString();
            String txtmdp = motDePasse.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BDUtilisateur", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS utilisateur(id INTEGER PRIMARY KEY AUTOINCREMENT,prenom VARCHAR, nom VARCHAR,classe VARCHAR, email VARCHAR,motDePasse VARCHAR)");
            String sql = "insert into utilisateur(prenom, nom, classe, email, motDePasse)values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,txtprenom);
            statement.bindString(2,txtnom);
            statement.bindString(3,txtclasse);
            statement.bindString(4,txtmail);
            statement.bindString(5,txtmdp);
            statement.execute();
            Toast.makeText(this,"Enregistrement reussi",Toast.LENGTH_LONG).show();
            prenom.setText("");
            nom.setText("");
            classe.setText("");
            email.setText("");
            motDePasse.setText("");
            prenom.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Enregistrement echoue",Toast.LENGTH_LONG).show();
        }
    }
}