package com.example.newelocationapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {


Button client,odjavi,locirajClient,zapocniRuta;
EditText sifraKlient;
TextView Name,Location,loc2,Longitude,Lattitude;
Object lattitude,longitude;


DatabaseHandler Dbh;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Dbh = new DatabaseHandler(this);

        zapocniRuta = findViewById(R.id.zapocni);




        odjavi = findViewById(R.id.button3);
        locirajClient = findViewById(R.id.button);
        sifraKlient = findViewById(R.id.clientID);




        client = findViewById(R.id.button);






      zapocniRuta.setOnClickListener(new View.OnClickListener() {
          public Object LatLng;


          @Override
          public void onClick(View v) {

              String ClientID = sifraKlient.getText().toString();


              boolean ClientLL = Dbh.checkID(ClientID);


              if (ClientLL == true) {



                  DBManager dbManager = new DBManager(MainActivity.this);
                  try {
                      dbManager.open();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }

                  Cursor cursor = dbManager.getLatLng(ClientID);
                  cursor.moveToFirst();

                  Intent intent = new Intent(MainActivity.this, MapsActivity.class);

                  startActivity(intent);




              }
          }
      });





        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

             odjavi.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     Intent i = new Intent(MainActivity.this,LoginActivity.class);
                     startActivity(i);

                 }
             });





             locirajClient.setOnClickListener(new AdapterView.OnClickListener() {
                 @Override
                 public void onClick(View v) {


                     String ClientID = sifraKlient.getText().toString();
                     boolean CL = Dbh.checkID(ClientID);

                     if (CL == true)
                     {
                        DBManager dbManager = new DBManager(MainActivity.this);
                         try {
                             dbManager.open();
                         } catch (Exception e) {
                             e.printStackTrace();
                         }


                         Cursor cursor = dbManager.fetch(ClientID);


                             cursor.moveToFirst();
                             Name = findViewById(R.id.Ime);
                             Location = findViewById(R.id.Location);
                             loc2 = findViewById(R.id.tipObjekt);
                             Longitude = findViewById(R.id.longituda);
                             Lattitude = findViewById(R.id.latituda);
                             Name.setText(cursor.getString(0));
                             Location.setText(cursor.getString(1));
                             loc2.setText(cursor.getString(2));
                             Longitude.setText(cursor.getString(3));
                             Lattitude.setText(cursor.getString(4));


                     }
                 }
             });

    }


/////
//    promena
//
//
//
//
//


}







