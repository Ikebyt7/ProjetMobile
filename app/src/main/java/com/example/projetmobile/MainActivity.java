package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends BaseActivity implements MainInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton = findViewById(R.id.button);
        mainButton.setBackgroundColor(Color.RED);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Onclicked", Toast.LENGTH_LONG).show();

            }
        });
        showList();
        Coureur coureur = new Coureur("julian", 28);
        showBaseError();
    }

    private void showList() {
    }

    @Override
    public void showList(List<Coureur> list) {

    }

    @Override
    public void showLoader() {

    }

    @Override
    public void showError() {

    }
}