package com.example.lab10;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Attendance extends AppCompatActivity {
 EditText eobt, etot;
 TextView res;
 Button onClac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setIcon(R.drawable.ic_college);
        actionbar.setTitle(" Attendance Record");
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        onClac = findViewById(R.id.button);
        onClac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eobt = findViewById(R.id.obtAttendance);
                etot = findViewById(R.id.totAttendance);
                res = findViewById(R.id.attres);
                Float obtAtt= Float.parseFloat(eobt.getText().toString().trim());
                Float totAtt= Float.parseFloat(etot.getText().toString().trim());
                Float percentage = (obtAtt/totAtt)*100;
                res.setText("Percentage of Attendance is : "+String.valueOf(percentage));
            }
        });

    }

}