package com.example.lab9_2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
public class BDatesDB {
    // Declare the constants for each row of the database to be used in the sql queries
    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_BDATE = "bdate";
    //Declare the constants for Dabatbase name, tables and version like given as below
    private final String DATABASE_NAME = "BDatesDB";
    private final String DATABASE_TABLE = "BDatesTable";
    private final int DATABASE_VERSION = 1;
    //Create objects for following classes. Read lab sheet 8 for the details of these classes
    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;
    //Constructor of this class
    public BDatesDB(Context context) {
        ourContext = context;
    }
    //DBHelper class for creation of table and other operations
    private class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
/* this is SQL query:
CREATE TABLE BDatesTable (id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL, bdate TEXT NOT NULL);
Follwoing is the way of writing it in android:
*/
            String sql_q = "CREATE TABLE " + DATABASE_TABLE +" (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_BDATE + " TEXT NOT NULL);";
            db.execSQL(sql_q); //execSQL() method is used to execute the sql query
        }
        //Upgrade of database requires to first drop the tables and create new ones
        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }
    //open() method uses getWritableDatabase() method of DBHelper class to open the database
    public BDatesDB open() throws Exception{
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    //Smilarly close() method
    public void close(){
        ourHelper.close();
    }
    /*insert requires to create an obejct of ContentValues class and put() and insert() methods
    of this class inserts the records in the table*/
    public long insertRecord(String name,String bdate){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_BDATE,bdate);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }
    /* Cursor class is used to fetch the records from the database. In the following function,
    fetched records are concatinated in a string “result”. The methods
    movetofirst(),isAfterlast() and movetoNext() are the methods of cursor class to position the
    items in the records.
    */
    public String getRecord(){
        String[] columns = new String[] {KEY_ROWID,KEY_NAME,KEY_BDATE};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result = " ";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iBDate = c.getColumnIndex(KEY_BDATE);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result = result + c.getString(iRowID) + ": " + c.getString(iName) + " " +

                    c.getString(iBDate) + "\n";
        }
        c.close();
        return result;
    }
    // Here the fetched records are stored in arraylist
    public ArrayList<String> getList(){
        String[] columns = new String[] {KEY_ROWID,KEY_NAME,KEY_BDATE};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        ArrayList<String> dateList = new ArrayList<String>();

        String result = " ";
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iBDate = c.getColumnIndex(KEY_BDATE);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result = result + c.getString(iRowID) + ": " + c.getString(iName) + " " +

                    c.getString(iBDate) + "\n";
            dateList.add(result);
        }
        c.close();
        return dateList;
    }
    //You can understand delete and update operations on the same lines
    public long deleteRecord(String rowID){
        return ourDatabase.delete(DATABASE_TABLE,KEY_ROWID + "=?", new String[]{rowID});
    }
    public long updateRecord(String rowID, String name, String bdate){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_BDATE,bdate);
        return ourDatabase.update(DATABASE_TABLE,cv,KEY_ROWID + "=?", new String[] {rowID});
    }
}