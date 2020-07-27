package com.example.newelocationapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "aaaa.db";


    private static  String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db;
    private Context context;
    private boolean mNeedUpdate = false;


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";

        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases";
        this.context = context;

        copyDatabase();
        mNeedUpdate = false;

    }

    public void updateDatabase() throws IOException
    {
        if (mNeedUpdate)
        {
            File dbFile = new File(DB_PATH + DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();
        }
    }

    private boolean checkDatabase()
    {
        File dbFile = new File(DB_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDatabase()
    {
        if (!checkDatabase())
        {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            }catch (IOException mIOException)
            {
                throw new Error("ErrorCopyingDatabase");
            }
        }
    }
    private void copyDBFile() throws IOException
    {
        InputStream input = context.getAssets().open(DATABASE_NAME);
        OutputStream output = new FileOutputStream(DB_PATH + DATABASE_NAME);
        byte[] Buffer = new byte[1024];
        int Length;
        while ((Length = input.read(Buffer)) > 0)
            output.write(Buffer,0,Length);
        output.flush();
        output.close();
        input.close();
    }

    public boolean openDatabase() throws SQLException
    {
        db = SQLiteDatabase.openDatabase(DB_PATH + DATABASE_NAME,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        return db != null;
    }

    public synchronized void close()
    {
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table TEmployee(EmpID text,Name text, Surname text,GroupID text, GroupTask text" +
                ",PinCode text primary key)");

        db.execSQL("Create table Cities (IDCities text primary key, Name text)");
        db.execSQL("Create table Intervention (IDIntervention text primary key, ClientNo text, UserID text, " +
                "InterventionStart text, InterventionEnd text, status text, EmpID text)");
        db.execSQL("Create table Memalarm (Client_No text primary key, ClientID text, Name text, Location text," +
                "loc2 text, Bus_Phone text, MobileNo text, PagerNo text,IDCities text, Description text, Longitude text," +
                "Lattitude text, NOTE text, MemalarmID text, status text, IsDeleted text, PictureExist text, SystemID text," +
                "Client_No_Original text )");
        db.execSQL("Create table Muser (UserID text primary key, User_Name text, PhoneNo1 text, PhoneNo2 text, PhoneNo3 text," +
                "PhoneNo4 text)");
        db.execSQL("Create table TChangedLocation (ChLID text primary key, Client_No text, EmpID text, Longitude text, Lattitude text," +
                "Date text, StatusID text, UserID text)");
        db.execSQL("Create table TStatus (StatusID text primary key, Status text, Description text)");
        db.execSQL("Create table UserToClient (UserId text primary key, ClientNo text, IDUserToClient text)");
        db.execSQL("Create table tMemalarmPictures (IDPicture text primary key, ClientNo text, UserID text, TimeIns text,PictureName text," +
                "Path text,EmpID text, Deleted text)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
        db.execSQL("drop table if exists TEmployee");
        db.execSQL("drop table if exists Cities");
        db.execSQL("drop table if exists Intervention");
        db.execSQL("drop table if exists Memalarm");
        db.execSQL("drop table if exists Muser");
        db.execSQL("drop table if exists TChangedLocation");
        db.execSQL("drop table if exists TStatus");
        db.execSQL("drop table if exists UserToClient");
        db.execSQL("drop table if exists tMemalarmPictures");

    }



    public boolean checkUser(String EmpID, String PinCode)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TEmployee where EmpID=? and PinCode=?", new String[]{EmpID,PinCode});
        if (cursor.getCount()>0)  return true;
        else return false;
    }
}














