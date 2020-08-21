package com.example.newelocationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newelocationapp.Utillities.MemalaUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


Button client,odjavi,locirajClient;
EditText sifraKlient;
ListView listClients;
ArrayList<MemalaUtil> list;
DatabaseHandler Dbh;
ArrayAdapter<String> adapter;
MainActivity main;

String Proverka;
 MemalaUtil CLID;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listClients = findViewById(R.id.list);
        Dbh = new DatabaseHandler(this);
        list = new ArrayList<>();


        odjavi = findViewById(R.id.button3);
        locirajClient = findViewById(R.id.button);
        sifraKlient = findViewById(R.id.clientID);


        client = findViewById(R.id.button);



       Proverka = sifraKlient.getText().toString();









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



                         CLID =    Dbh.clientInfo(ClientID);



                         adapter  = new ArrayAdapter<>(MainActivity.this ,android.R.layout.simple_list_item_1, (List<String>) CLID);

                         listClients.setAdapter(adapter);




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







