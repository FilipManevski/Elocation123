package com.example.newelocationapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {


    public static final String TABLE1 = "TStatus";
    private static final String TABLE2 = "Memalarm";
    private static final String TABLE3 = "tMemalarmPictures";
    private static final String TABLE4 = "TChangedLocation";
    private static final String TABLE5 = "Muser";
    public static final String TABLE6 = "TEmployee";
    private static final String TABLE7 = "Cities";
    private static final String TABLE8 = "UserToClient";
    private static final String TABLE9 = "Intervention";
    private static final String TABLE10 = "SysDiagrams";


    private static final String DATABASE_NAME = "ElocationNewTest";



    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TStatus = "CREATE TABLE "+TABLE1+"(StatusID INTEGER PRIMARY KEY, Status TEXT, Description TEXT)";

        String Memalarm = "CREATE TABLE "+TABLE2+"(Client_No TEXT PRIMARY KEY, ClientID TEXT, Name TEXT, Location TEXT, loc2 TEXT, Bus_Phone TEXT," +
                " MobileNo TEXT, PagerNo TEXT, IDCities INTEGER, Description TEXT, Longitude REAL, Lattitude REAL, NOTE TEXT, MemalarmID INTEGER," +
                " status INTEGER, IsDeleted NUMERIC, PictureExist NUMERIC, SystemID INTEGER, Client_No_Original TEXT)";

        String tMemalarmPictures = "CREATE TABLE "+TABLE3+"(IDIntervention INTEGER PRIMARY KEY, ClientNo TEXT, UserID INTEGER, InterventionStart TEXT, " +
                "InterventionEnd TEXT, status INTEGER, EmpID INTEGER)";

        String TChangedLocation = "CREATE TABLE "+TABLE4+"(ChLID INTEGER PRIMARY KEY, ClientNo TEXT, EmpID INTEGER, Longitude REAL, Lattitude REAL," +
                " Date TEXT, StatusID INTEGER, UserID INTEGER)";

        String Muser = "CREATE TABLE "+TABLE5+"(UserID INTEGER PRIMARY KEY, User_Name TEXT, PhoneNo1 TEXT, PhoneNo2 TEXT, PhoneNo3 TEXT, PhoneNo4 TEXT)";

        String TEmployee = "CREATE TABLE "+TABLE6+"(EmpID INTEGER PRIMARY KEY, Name TEXT, Surname TEXT, GroupID INTEGER, GroupTask TEXT, PinCode TEXT)";

        String Cities = "CREATE TABLE "+TABLE7+"(IDCities INTEGER PRIMARY KEY, Name TEXT)";

        String UserToClient = "CREATE TABLE "+TABLE8+"(UserID INTEGER, ClientNo TEXT, IDUserToClient INTEGER PRIMARY KEY)";

        String Intervention = "CREATE TABLE "+TABLE9+"(IDIntervention INTEGER PRIMARY KEY, ClientNo TEXT, UserID INTEGER, InterventionStart TEXT," +
                " InterventionEnd TEXT, status INTEGER, EmpID INTEGER)";

        String SysDiagrams = "CREATE TABLE " + TABLE10 + "(Name INTEGER PRIMARY KEY, PrincipalID TEXT, DIagramID TEXT, Version TEXT, Definition TEXT)";




        db.execSQL(TStatus);
        db.execSQL(Memalarm);
        db.execSQL(tMemalarmPictures);
        db.execSQL(TChangedLocation);
        db.execSQL(Muser);
        db.execSQL(TEmployee);
        db.execSQL(Cities);
        db.execSQL(UserToClient);
        db.execSQL(Intervention);
        db.execSQL(SysDiagrams);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE5);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE6);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE7);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE8);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE9);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE10);

        onCreate(db);
    }










}
