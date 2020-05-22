package com.example.lab9;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// The following function connects the activity_main.xml to this java file
        setContentView(R.layout.activity_main);
/*findViewByid() function in the following statement creates an object “courseList”
of listview component. Once an object is created, we can access all the methods
corresponding to listview with the help of this object. */
        ListView courseList = (ListView)findViewById(R.id.lstCourses);
/*Object of arraylist is created as below and add() function of arraylist is used
to add the elements to the list.*/
        ArrayList<String> alCourses = new ArrayList<>();
        alCourses.add("AI");
        alCourses.add("ML");
        alCourses.add("DBMS");
        alCourses.add("OS");
        alCourses.add("CA");
//Array adapter defined below is used to connect arraylist with listview.
        ArrayAdapter courseAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,alCourses);
        courseList.setAdapter(courseAdapter);
    }
}