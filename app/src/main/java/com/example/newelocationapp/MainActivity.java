package com.example.newelocationapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

     String URL = "https://elocation.nikob.com.mk/WebService/WebServiceVcitajServerPromeni.asmx?fbclid=IwAR2Oe7rljxeB-adtA1Io9tkYhce5nlJVB_gHzOnubHp4w8Xzq25chfA9U4I";
    String SOAP_ACTION = "https://elocation.nikob.com.mk/WebService/WebServiceVcitajServerPromeni.asmx/GetEmloyee";
   String METHOD_NAME = "GetEmployee";
     String NAMESPACE = "https://elocation.nikob.com.mk/WebService/WebServiceVcitajServerPromeni.asmx";
     String PARAMETER_NAME = "EmpID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }




}







