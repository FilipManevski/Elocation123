package com.example.newelocationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


Button client,odjavi,locirajClient;
EditText sifraKlient;
ListView listClients;
DatabaseHandler Dbh;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Dbh = new DatabaseHandler(this);
        odjavi = findViewById(R.id.odjavi);
        locirajClient = findViewById(R.id.button);
        sifraKlient = findViewById(R.id.ClientID);
        client = findViewById(R.id.pregledajKlient);

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondPage.class);
                startActivity(i);
            }
        });

             odjavi.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     Intent i = new Intent(MainActivity.this,LoginActivity.class);
                     startActivity(i);

                 }
             });

             locirajClient.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     String ClientID = sifraKlient.getText().toString();
                     boolean CL = Dbh.checkID(ClientID);
                     if (CL == true)
                     {


                     }
                 }
             });

    }




}







