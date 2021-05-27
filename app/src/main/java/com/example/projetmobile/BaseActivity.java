package com.example.projetmobile;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

abstract class BaseActivity extends AppCompatActivity {

    public void showBaseError(){
        //TODO Afficher l'erreur
        Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show();
    }
}
