package com.example.user_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccueilActivity extends AppCompatActivity {

    Button btnEnregAccueil, btnModifAccueil, btnListAccueil, btnSupAccueil;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btnEnregAccueil = findViewById(R.id.btnEnregAccueil);
        btnModifAccueil = findViewById(R.id.btnModifAccueil);
        btnListAccueil = findViewById(R.id.btnListAccueil);
        btnSupAccueil = findViewById(R.id.btnSupAccueil);

        btnEnregAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnModifAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, InfoUserActivity.class);
                startActivity(intent);
            }
        });

        btnListAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, InfoUserActivity.class);
                startActivity(intent);
            }
        });

        btnSupAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, InfoUserActivity.class);
                startActivity(intent);
            }
        });
    }
}