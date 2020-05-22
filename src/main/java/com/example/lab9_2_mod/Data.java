package com.example.lab9_2_mod;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.SQLException;

public class Data extends AppCompatActivity {
    TextView txtdisplay;
    DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DataBaseHelper(this);
        setContentView(R.layout.data);
        txtdisplay = (TextView) findViewById(R.id.txtDisplay);
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            txtdisplay.setText("Error Nothing found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("DOB :"+ res.getString(2)+"\n");
            buffer.append("Age :"+ res.getString(3)+"\n\n");
        }

        // Show all data
        txtdisplay.setText(buffer.toString());
    }
}