package com.example.lab10;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
public class Grade extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setIcon(R.drawable.ic_college);
        actionbar.setTitle(" Grades");
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }
    public void calGradeOnClick(View v){
// Complete the logic here...
    }
}