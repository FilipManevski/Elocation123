package com.example.newelocationapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.newelocationapp.Utillities.MemalaUtil;

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


     main = new MainActivity();

      String CREATE_MEMALARM_TABLE = "CREATE TABLE " +  MemalaUtil.TABLE_NAME + "(" + MemalaUtil.CLIENT_NO + "INTEGER PRIMARY KEY," + MemalaUtil.Client_ID + " TEXT," +
              MemalaUtil.NAME + " TEXT," + MemalaUtil.LOCATION + " TEXT,"+ MemalaUtil.LOC2 + " TEXT," + MemalaUtil.BUS_PHONE + " TEXT," + MemalaUtil.MOBILE_NO + " TEXT," +
              MemalaUtil.PAGER_NO + " TEXT," + MemalaUtil.ID_CITIES + " TEXT," + MemalaUtil.DESCRIPTION + " TEXT," + MemalaUtil.LONGITUDE + " TEXT," + MemalaUtil.LATITUDE + " TEXT," +
              MemalaUtil.NOTE + " TEXT," + MemalaUtil.MEMALARM_ID + " TEXT," + MemalaUtil.STATUS + " TEXT," + MemalaUtil.IS_DELETED + " TEXT," + MemalaUtil.SYSTEM_ID + " TEXT," +
              MemalaUtil.CLIENT_NO_ORIGINAL + " TEXT" + ")";
      db.execSQL(CREATE_MEMALARM_TABLE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;

        db.execSQL("DROP TABLE IF EXISTS " + MemalaUtil.TABLE_NAME);
        onCreate(db);



    }

    public String checkCL(String ClientID)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from Memalarm where ClientID = " + main.sifraKlient, new String[Integer.parseInt(ClientID)] );
        cursor.moveToNext();
        return ClientID;

    }




    public boolean checkUser(String EmpID, String PinCode)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TEmployee where EmpID=? and PinCode=?", new String[]{EmpID,PinCode});
        if (cursor.getCount()>0)  return true;
        else return false;
    }


    public boolean checkID(String ClientID)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Memalarm where ClientID=? ",new String[]{ClientID});

        if (cursor.getCount()>0) return true;
        else return false;

    }

 /*public ArrayList<MemalaUtil> getList()
 {


     SQLiteDatabase db = this.getReadableDatabase();
     ArrayList<MemalaUtil> memalaUtils = new ArrayList<>();
     MemalaUtil memalaUtil;
     Cursor cursor = db.rawQuery("select * from Memalarm where ClientID=" +main.ClientID1 ,null);

     if (cursor.getCount() > 0)
     {
         for (int i = 0; i < cursor.getCount(); i++)
         {
             cursor.moveToNext();
             memalaUtil = new MemalaUtil();
             memalaUtil.setNAME(cursor.getString(1));
             memalaUtil.setLOCATION(cursor.getString(2));
             memalaUtil.setLOC2(cursor.getString(3));
             memalaUtils.add(memalaUtil);

         }
     }

     cursor.close();
     db.close();
     return memalaUtils;

 }
 *?
  */


 public  MemalaUtil  clientInfo(String ClientID)
 {
     SQLiteDatabase db = this.getReadableDatabase();
     Cursor cursor = db.rawQuery("select * from Memalarm where ClientID = " + main.Proverka, new String[Integer.parseInt(ClientID)]);


     cursor.moveToFirst();

     MemalaUtil memalaUtil = new MemalaUtil();
     memalaUtil.setNAME(cursor.getString(cursor.getColumnIndex("Name")));
     memalaUtil.setLOCATION(cursor.getString(cursor.getColumnIndex("Location")));
     memalaUtil.setLOC2(cursor.getString(cursor.getColumnIndex("loc2")));
     cursor.close();
     db.close();

     return memalaUtil;




 }


public Cursor viewData(String ClientID)
{


    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery("select * from " + MemalaUtil.TABLE_NAME + " where " + MemalaUtil.Client_ID + " = " + main.Proverka  ,new String[Integer.parseInt(ClientID)]);
    cursor.moveToFirst();

    cursor.getColumnIndex("Name");
    cursor.getColumnIndex("Location");
    cursor.getColumnIndex("loc2");

    return cursor;
}



}














