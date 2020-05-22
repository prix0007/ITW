package com.example.lab9_2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import java.sql.SQLException;
public class MainActivity extends AppCompatActivity {
    EditText etName,etbdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Object Creation of the two Edittext components
        etName = (EditText)findViewById(R.id.name);
        etbdate = (EditText)findViewById(R.id.bdate);
    }
    //Following are the click events of all the buttons
    public void AddRecord(View v) throws Exception {
        String name = etName.getText().toString().trim();
        String bdate = etbdate.getText().toString().trim();
//Following Code invokes the methods: open(), insertRecord() and close() of BDatesDB class
        try{
            BDatesDB db = new BDatesDB(this);
            db.open();
            db.insertRecord(name,bdate);
            Toast.makeText(this,"Successfully added!..",Toast.LENGTH_LONG).show();
            etName.setText("");
            etbdate.setText("");
            db.close();
        }
        catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    //Following Code invokes the methods: open(), deleteRecord() and close() of BDatesDB class
    public void DeleteRecord(View v) throws Exception {
        try{
            BDatesDB db = new BDatesDB(this);
            db.open();
            db.deleteRecord("1");
            Toast.makeText(this,"Successfully Deleted!..",Toast.LENGTH_LONG).show();
            db.close();
        }
        catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    //Following Code invokes the methods: open(), updateRecord() and close() of BDatesDB class
    public void UpdateRecord(View v) throws Exception {
        try{
            BDatesDB db = new BDatesDB(this);
            db.open();
            db.updateRecord("1","Mayuri","26/12/1979");
            Toast.makeText(this,"Successfully updated!..",Toast.LENGTH_LONG).show();
            db.close();
        }
        catch (SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    /*Following Code opens the activity_data.xml file, data.java file contains the code for
    displaying the records on textview */
    public void ShowRecord(View view) {
        Intent intent =new Intent(MainActivity.this,data.class);
        startActivity(intent);
    }

    /*Following Code opens the activity_d_o_b_list.xml file, DOBList.java file contains the code
    for displaying the records on Listview*/
    public void DisplayList(View view){
        Intent intent =new Intent(MainActivity.this,DOBList.class);
        startActivity(intent);
    }
}