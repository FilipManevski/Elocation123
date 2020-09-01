package com.example.newelocationapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {


    DatabaseHandler DbH;
    EditText sifraPatrola, sifraKorisnik;
    Button login;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        DbH = new DatabaseHandler(this);
        sifraPatrola = findViewById(R.id.sifraPatrola);
        sifraKorisnik = findViewById(R.id.sifraKorisnik);
        login =  findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String UserID = sifraPatrola.getText().toString();
                String PinCode = sifraKorisnik.getText().toString();
                boolean CU = DbH.checkUser(PinCode);
                boolean CUD = DbH.checkUserID(UserID);
                if (CU == true && CUD == true) {
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                       startActivity(myIntent);


                }
                else
                    Toast.makeText(getApplicationContext(),"Корисникот не постои.",Toast.LENGTH_SHORT).show();



            }


        });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



    }






    }

















