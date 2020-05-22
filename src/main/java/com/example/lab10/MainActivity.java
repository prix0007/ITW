package com.example.lab10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setIcon(R.drawable.ic_college);
        actionbar.setTitle(" Welcome to Student Corner");
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.DisplayStudent:
                Toast.makeText(this,"Display Student Details",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Student.class));
                break;
            case R.id.AttendanceRecord:
                Toast.makeText(this,"Display Attendance Record",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,Attendance.class));
                break;
            case R.id.GradeCalculator:
                Toast.makeText(this,"Display Grade",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,CalculateGrade.class));
                break;
            case R.id.InsertRecord:
                Toast.makeText(this,"Insert Student",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,InsertStudent.class));
                break;
            case R.id.Exit:
                Toast.makeText(this,"Exiting",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}