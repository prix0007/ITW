package com.example.lab10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "basicDetails";
    public static final String COL_1 = "ID";
    public static final String COL_2= "ENROLLMENT_NO";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "DOB";
    public static final String COL_5 = "AGE";
    public static final String COL_6 ="CURRENT_SEMESTER";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ENROLLMENT_NO TEXT,NAME TEXT,DOB TEXT,AGE INTEGER,CURRENT_SEMESTER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String eno, String name,String dob,Integer age, String current_Semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,eno);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,dob);
        contentValues.put(COL_5, age);
        contentValues.put(COL_6, current_Semester);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public String getEnrollmentIdData(String eno){
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE  ENROLLMENT_NO = '" + eno +"'";
        Cursor c = this.getAllData();
        if(c.getCount() == 0){
            return "NULL NO DATA";
        }
        String data = "ERROR NOT FOUND";
        while(c.moveToNext()){
            if(c.getString(1).equals(eno)) {
                data = " ID: "+c.getString(0) + " , ";
                data += "ENROLLMENT-NO: " +c.getString(1) + " , ";
                data += "Name: "+c.getString(2) + " , ";
                data += "DOB: "+c.getString(3) + " , ";
                data += "Age: "+c.getString(4) + " , ";
                data += "Semster: "+c.getString(5) + " , ";
            }
        }
        return data;
    }

    public boolean updateData(String eno,String name,String dob,Integer age,String current_Semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,eno);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,dob);
        contentValues.put(COL_5,age);
        contentValues.put(COL_6,current_Semester);
        db.update(TABLE_NAME, contentValues, "ENROLLMENT_NO = ?",new String[] { eno });
        return true;
    }

    public Integer deleteData (String eno) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ENROLLMENT_NO = ?",new String[] {eno});
    }
}