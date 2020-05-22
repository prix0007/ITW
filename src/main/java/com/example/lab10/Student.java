package com.example.lab10;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Student extends AppCompatActivity{
    Button getDetails;
    DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ActionBar actionbar = getSupportActionBar();
//        actionbar.setIcon(R.drawable.ic_college);
        actionbar.setTitle("Student Activity");
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        myDb = new DataBaseHelper(getApplicationContext());
        displayOnClick(findViewById(R.id.student_root));
    }
    public void displayOnClick(View v){
       getDetails = v.findViewById(R.id.getStudentDetails);
       final EditText id = v.findViewById(R.id.enrollment_no);
       final TextView details = v.findViewById(R.id.displayData);

       getDetails.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String input = id.getText().toString().trim();
               if (checkEnrollmentNo(input)) {
                   Toast.makeText(v.getContext(), input, Toast.LENGTH_LONG).show();
                   String data = myDb.getEnrollmentIdData(input).toString();
                   String[] datalist = data.split(",");
                   data = "";
                   for(String a: datalist){
                       data += a + "\n";
                   }
                   details.setText(data);
               }
               else
                   Toast.makeText(v.getContext(), "Wrong Enrollment Number", Toast.LENGTH_LONG).show();
           }
       });
    }
    private boolean checkEnrollmentNo(String input){
        if(input.length()!= 10)
            return false;
        if(!input.substring(0,2).equals("BT"))
            return false;
        if(!input.substring(4,7).equals("CSE") )
            return false;
        if((Integer.parseInt(input.substring(2,4)) < 16) || (Integer.parseInt(input.substring(2,4)) > 20))
            return false;
        if((Integer.parseInt(input.substring(7,input.length())) < 0) || (Integer.parseInt(input.substring(7,input.length())) > 150))
            return false;
        return true;
    }
}
