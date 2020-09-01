package com.example.newelocationapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

MainActivity main;

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHandler db;

    public DBManager(Context c)
    {
        this.context = c;
    }

    public DBManager() {

    }

    public DBManager open() throws Exception
    {
        this.db = new DatabaseHandler(this.context);
        this.database = this.db.getReadableDatabase();
        return this;
    }

    public void close()
    {
        this.db.close();
    }






    public Cursor fetch(String ClientID)
    {

        //String checkID = db.checkID1(ClientID);
        Cursor cursor = this.database.rawQuery("select Name,Location,loc2 from Memalarm where ClientID=" + ClientID,null );


       if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();

        }
        return cursor;

    }

    public Cursor getLatLng(String ClientID)
    {

        Cursor cursor = this.database.rawQuery("select Longitude,Lattitude from Memalarm where ClientID= " + ClientID,null );

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();

        }
        return cursor;
    }



}
