package com.example.newelocationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    DatabaseHandler DbH;
    EditText sifraPatrola, sifraKorisnik;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        DbH = new DatabaseHandler(this);
        sifraPatrola = findViewById(R.id.sifraPatrola);
        sifraKorisnik = findViewById(R.id.sifraKorisnik);
        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String EmpID = sifraPatrola.getText().toString();
                String PinCode = sifraKorisnik.getText().toString();
                boolean CU = DbH.checkUser(EmpID,PinCode);
                if (CU == true) {
                    Intent myIntent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(myIntent);
                    Toast.makeText(getApplicationContext(), "Успешно најавени.", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getApplicationContext(),"Корисникот не постои.",Toast.LENGTH_SHORT).show();



            }


        });


    }






    }

















