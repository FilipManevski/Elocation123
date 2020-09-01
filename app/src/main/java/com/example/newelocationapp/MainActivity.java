package com.example.newelocationapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.newelocationapp.Utillities.MemalaUtil;

import java.util.ArrayList;

public class MainActivity extends Activity {


Button client,odjavi,locirajClient,zapocniRuta;
EditText sifraKlient;
TextView Name,Location,loc2;
ListView listClients;
ArrayList<MemalaUtil> list;
DatabaseHandler Dbh;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listClients = findViewById(R.id.list);
        Dbh = new DatabaseHandler(this);
        list = new ArrayList<>();
        zapocniRuta = findViewById(R.id.zapocni);
        Name = findViewById(R.id.name);



        odjavi = findViewById(R.id.button3);
        locirajClient = findViewById(R.id.button);
        sifraKlient = findViewById(R.id.clientID);




        client = findViewById(R.id.button);






      zapocniRuta.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String ClientID = sifraKlient.getText().toString();


              boolean ClientLL = Dbh.checkID(ClientID);


              if (ClientLL == true) {

                  Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                  startActivity(intent);


                  DBManager dbManager = new DBManager(MainActivity.this);
                  try {
                      dbManager.open();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }

                   Cursor cursor = dbManager.getLatLng(ClientID);




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
                             Name = findViewById(R.id.name);
                             Location = findViewById(R.id.location);
                             loc2 = findViewById(R.id.loc2);
                             Name.setText(cursor.getString(0));
                             Location.setText(cursor.getString(1));
                             loc2.setText(cursor.getString(2));









                     }
                 }
             });

    }






 /* private void viewData() {
      String ClientID = sifraKlient.getText().toString();
        Cursor cursor = Dbh.viewData(ClientID);

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this,"Nema podatoci. ", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.getCount()>0)
            {

            }


            listClients.setAdapter(adapter);
        }
    }
*/



}







