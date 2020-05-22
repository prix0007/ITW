package com.example.lab10;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Year;
import java.util.Calendar;

public class InsertStudent extends AppCompatActivity {

    DataBaseHelper myDb;
    Button getData;
    EditText eno, edob, ename;
    Spinner esem;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_student);
        ActionBar actionbar = getSupportActionBar();
//        actionbar.setIcon(R.drawable.ic_college);
        actionbar.setTitle("Insert Student");
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);

        myDb = new DataBaseHelper(getApplicationContext());
        eno = findViewById(R.id.eno);
        ename = findViewById(R.id.ename);
        edob = findViewById(R.id.edob);
        esem = findViewById(R.id.esem);
        getData = findViewById(R.id.insert);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enroll_no = eno.getText().toString().trim();
                String dob = edob.getText().toString().trim();
                String name = ename.getText().toString().trim();
                int year = Calendar.getInstance().get(Calendar.YEAR);
                Integer age  = year - Integer.parseInt(dob.substring(6,dob.length()));
                String sem = esem.getSelectedItem().toString().trim();

                if( myDb.insertData(enroll_no, name, dob, age, sem)){
                    Toast.makeText(getApplicationContext(), "Inserted Data Successfully", Toast.LENGTH_LONG).show();
                    eno.setText("");
                    edob.setText("");
                    ename.setText("");
                    esem.setSelection(0);
                } else {
                    Toast.makeText(getApplicationContext(), "Some Error Occured", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
