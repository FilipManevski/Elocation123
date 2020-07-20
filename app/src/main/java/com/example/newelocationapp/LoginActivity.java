package com.example.newelocationapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DatabaseHandler DbH;
    EditText sifraPatrola,sifraKorisnik;
    Button login;

    String EmpID,PinCode;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        DbH = new DatabaseHandler(this);
        db = DbH.getReadableDatabase();
        sifraPatrola = findViewById(R.id.sifraPatrola);
        sifraKorisnik = findViewById(R.id.editText);
        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if (sifraPatrola.getText().toString().equals(EmpID) &&
             sifraKorisnik.getText().toString().equals(PinCode))
             {
                 Toast.makeText(getApplicationContext(),"Успешно најавени",Toast.LENGTH_SHORT).show();
             }else
             {
                 Toast.makeText(getApplicationContext(),"Корисникот не постои.",Toast.LENGTH_SHORT).show();
             }



              getEmployee(EmpID,PinCode);


            }

          
        });


    }



    public void getEmployee(String EmpID, String PinCode)
    {


        MyResponse mt = new MyResponse();
        mt.execute();

    }

    class MyResponse extends AsyncTask
    {


        @Override
        protected Object doInBackground(Object[] objects) {
            loadData();

            return objects;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }



    public void loadData()
    {
        String URL = "https://elocation.nikob.com.mk/WebService/WebServiceVcitajServerPromeni.asmx?fbclid=IwAR2Oe7rljxeB-adtA1Io9tkYhce5nlJVB_gHzOnubHp4w8Xzq25chfA9U4I";
        String SOAP_ACTION = "https://elocation.nikob.com.mk/WebService/WebServiceVcitajServerPromeni.asmx/GetEmloyee";
        String METHOD_NAME = "GetEmployee";
        String NAMESPACE = "https://elocation.nikob.com.mk/WebService/WebServiceVcitajServerPromeni.asmx";


        SoapObject soapObject = new SoapObject(NAMESPACE,METHOD_NAME);




        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(org.ksoap2.SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE hts = new HttpTransportSE(URL);

        try {
            hts.call(SOAP_ACTION,envelope);

            SoapObject soapObject1 = (SoapObject) envelope.bodyIn;


            if (soapObject1 != null)
            {
                sifraPatrola.setText(soapObject1.getProperty(0).toString());
            }else if (soapObject1 != null)
            {
                sifraKorisnik.setText(soapObject1.getProperty(5).toString());
            }else
            {
                Toast.makeText(getApplicationContext(), "No Response",Toast.LENGTH_SHORT).show();
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


    }


}
