package com.example.lab10;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.example.lab10.SubjectMark;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CalculateGrade extends AppCompatActivity implements SubjectMark.SubjectMarkListener {

    TextView edata, eres;
    final Context context = this;
    int totmks=0, obtmks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_grade);
        ActionBar actionbar = getSupportActionBar();
//        actionbar.setIcon(R.drawable.ic_college);
        actionbar.setTitle("Grading Subject");
        actionbar.setDisplayUseLogoEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);

        final TextView[] edata = {findViewById(R.id.dataSubject)};
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Log.d("Input Dialog box", userInput.getText().toString());
                                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                        ft.add(R.id.my_placeholder, new SubjectMark(userInput.getText().toString()));
                                        ft.commit();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        FloatingActionButton grade = findViewById(R.id.grade);
        grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d("Log Clicked","True");
                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                    edata[0] = findViewById(R.id.dataSubject);
                    edata[0].setText("");
                }
            }
        });
    }

    @Override
    public void getData(String data) {
        edata = findViewById(R.id.dataSubject);
        eres = findViewById(R.id.result);
        String datal = edata.getText().toString();
        datal += "\n";
        String[] list = data.split(",");
        datal += "Subject: " + list[0] + " | Obtained Marks: " +list[1]+" | MaximumMarks: "+list[2];
        totmks += Integer.parseInt(list[2]);
        obtmks += Integer.parseInt(list[1]);
        edata.setText(datal);
        float percentage = (Float.parseFloat(String.valueOf(obtmks))/Float.parseFloat(String.valueOf(totmks)))*100;
        eres.setText("Result: "+String.valueOf(percentage));
    }
}
