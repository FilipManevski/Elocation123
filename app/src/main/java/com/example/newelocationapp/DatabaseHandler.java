package com.example.newelocationapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.EditText;

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

    EditText sifraKlient;
    MainActivity main;



    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        if (Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";

        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.context = context;

        copyDatabase();


        this.getReadableDatabase();
    }


    public void updateDatabase() throws IOException
    {
        if (mNeedUpdate)
        {
            File dbFile = new File(DB_PATH + DATABASE_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDatabase();

            mNeedUpdate = false;
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


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;





    }



    public boolean checkUser( String PinCode)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TEmployee where  PinCode=?", new String[]{PinCode});
        if (cursor.getCount()>0)  return true;
        else return false;
    }

    public boolean checkUserID( String UserID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Muser where  UserID=?", new String[]{UserID});
        if (cursor.getCount()>0)  return true;
        else return false;
    }




    public boolean checkID(String ClientID)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Memalarm where ClientID=?  " ,new String[]{ClientID});

        if (cursor.getCount()>0) return true;
        else return false;

    }






}














