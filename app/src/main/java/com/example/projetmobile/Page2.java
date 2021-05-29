package com.example.projetmobile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class Page2 extends AppCompatActivity {

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        String coureurList = getIntent().getStringExtra("CoureurList");
        TextView textView = findViewById(R.id.textView);
        textView.setText("j'abandonne");

    }

}