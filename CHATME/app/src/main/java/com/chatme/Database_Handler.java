package com.chatme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.jar.Attributes;

public class Database_Handler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "chat_me";
    private static final String TABLE = "user_details";
    private static final String USER_ID="ID";
    private static final String USER_NAME = "NAME";
    private static final String USER_EMAIL = "EMAIL";
    private static final String USER_PASS = "PASSWORD";
    private static final String USER_PICTURE = "PICTURE";
    private static final String USER_STATUS = "STATUS";

    SQLiteDatabase sldb;
    Context cn;

    public Database_Handler(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        cn = ctx;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE+ "("
                + USER_ID + " TEXT ," + USER_NAME + " TEXT,"
                + USER_EMAIL + " TEXT,"+ USER_PASS + " TEXT,"+ USER_PICTURE
                + " TEXT,"+ USER_STATUS + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addUser(String id,String u_name,String u_email,String u_pass){
        sldb = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_ID,id);
        values.put(USER_NAME,u_name);
        values.put(USER_EMAIL,u_email);
        values.put(USER_PASS,u_pass);
        values.put(USER_PICTURE,"demo.jpeg");
        values.put(USER_STATUS,"Hey..I M Using ChatME");

        // Inserting user details
        sldb.insert(TABLE, null, values);
        sldb.close(); // Closing database connection

    }


    public void deleteuser(){
        sldb = this.getWritableDatabase();
        sldb.delete(TABLE,null,null);
        sldb.close();
    }

    public String myinfo(){
        sldb = this.getReadableDatabase();
        String result = "";
        String[] columns = new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_PASS,USER_PICTURE,USER_STATUS};
        Cursor c = sldb.query(TABLE,columns,null,null,null,null,null);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(c.getColumnIndex(USER_ID))+"\n"+
                    c.getString(c.getColumnIndex(USER_NAME))+"\n"+
                    c.getString(c.getColumnIndex(USER_EMAIL))+"\n"+
                    c.getString(c.getColumnIndex(USER_PASS))+"\n"+
                    c.getString(c.getColumnIndex(USER_PICTURE))+"\n"+
                    c.getString(c.getColumnIndex(USER_STATUS))+"\n"+"\n";
        }
        sldb.close();
        return result;
    }

    public void addusr(String id,String name, String email, String password, String picture, String status) {
        sldb = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(USER_ID,id);
        value.put(USER_NAME,name);
        value.put(USER_EMAIL,email);
        value.put(USER_PASS,password);
        value.put(USER_PICTURE,picture);
        value.put(USER_STATUS,status);

        sldb.insert(TABLE,null,value);
        sldb.close();
    }

    public boolean isUser(){
        sldb=this.getReadableDatabase();
        String[] columns=new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_PASS,USER_PICTURE,USER_STATUS};
        Cursor c=sldb.query(TABLE,columns,null,null,null,null,null);
        if(c.moveToNext()){
            return true;
        }
        return false;
    }

    public String[] getMailPass(){
        sldb = this.getReadableDatabase();
        String[] columns = new String[]{USER_ID,USER_NAME,USER_EMAIL,USER_PASS,USER_PICTURE,USER_STATUS};
        Cursor c = sldb.query(TABLE,columns,null,null,null,null,null);
        c.moveToFirst();
        String[] res = new String[]{c.getString(c.getColumnIndex(USER_EMAIL)),c.getString(c.getColumnIndex(USER_PASS))};
        return res;
    }
}
