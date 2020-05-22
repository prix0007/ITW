package com.example.lab9_2_mod;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DOBList extends AppCompatActivity {
    DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dob_list);
        ListView DatesList = (ListView) findViewById(R.id.dateList);
        myDb = new DataBaseHelper(this);

        ArrayList<String> dataList = new ArrayList<String>();

        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            Toast.makeText(this, "No data Found", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("DOB :"+ res.getString(2)+"\n");
            buffer.append("Age :"+ res.getString(3)+"\n ,");
        }

        String[] list = buffer.toString().split(",");
        for (String a : list)
            dataList.add(a);
        System.out.println("Length of list : "+list.length);
        for (String a: dataList)
            System.out.println(a);
        ArrayAdapter<String> bdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        DatesList.setAdapter(bdAdapter);

    }
}