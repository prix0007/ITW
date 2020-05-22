package com.example.lab9_2_mod;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import java.sql.SQLException;
public class MainActivity extends AppCompatActivity {
    EditText etName,etbdate, etcdate, etid;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);
//Object Creation of the two Edittext components
        etName = (EditText)findViewById(R.id.name);
        etbdate = (EditText)findViewById(R.id.bdate);
        etcdate = (EditText)findViewById(R.id.cdate);
        etid = (EditText)findViewById(R.id.id_ud);
    }
    //Following are the click events of all the buttons
    public void AddRecord(View v) throws Exception {
        String name = etName.getText().toString().trim();
        String bdate = etbdate.getText().toString().trim();
        String cdate = etcdate.getText().toString().trim();
        Integer age = Integer.parseInt(cdate.substring(6,cdate.length())) -  Integer.parseInt(bdate.substring(6,bdate.length()));

        Log.d("Age Found", age.toString());
        boolean isInserted = myDb.insertData(name,bdate,age );
        if(isInserted == true) {
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
            etName.setText("");
            etcdate.setText("");
            etbdate.setText("");
            etid.setText("");
        }
        else
            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
    }
    //Following Code invokes the methods: open(), deleteRecord() and close() of BDatesDB class
    public void DeleteRecord(View v) throws Exception {
        String id = etid.getText().toString().trim();
        Integer deletedRows = myDb.deleteData(id);
        if(deletedRows > 0) {
            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
            etid.setText("");
        }
        else
            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
    }
    //Following Code invokes the methods: open(), updateRecord() and close() of BDatesDB class
    public void UpdateRecord(View v) throws Exception {
        String name = etName.getText().toString().trim();
        String bdate = etbdate.getText().toString().trim();
        String cdate = etcdate.getText().toString().trim();
        String id = etid.getText().toString().trim();
        Integer age = Integer.parseInt(cdate.substring(6,cdate.length())) -  Integer.parseInt(bdate.substring(6,bdate.length()));

        boolean isUpdate = myDb.updateData(id, name, bdate ,age);
        if(isUpdate == true) {
            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
            etName.setText("");
            etcdate.setText("");
            etbdate.setText("");
            etid.setText("");
        }
        else
            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
    }
    /*Following Code opens the activity_data.xml file, data.java file contains the code for
    displaying the records on textview */
    public void ShowRecord(View view) {
        Intent intent =new Intent(MainActivity.this, Data.class);
        startActivity(intent);
    }

    /*Following Code opens the activity_d_o_b_list.xml file, DOBList.java file contains the code
    for displaying the records on Listview*/
    public void DisplayList(View view){

        Intent intent =new Intent(MainActivity.this,DOBList.class);
        startActivity(intent);
    }
}