package com.example.newelocationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondPage extends AppCompatActivity {

    Button zatvori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);


        zatvori = findViewById(R.id.button6);

        zatvori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondPage.this, MainActivity.class);
                startActivity(i);
            }
        });
    }


}
