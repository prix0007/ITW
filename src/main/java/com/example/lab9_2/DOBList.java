package com.example.lab9_2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class DOBList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_o_b_list);
        ListView DatesList = (ListView) findViewById(R.id.dateList);
        try{
            BDatesDB db = new BDatesDB(this);
            db.open();
            ArrayAdapter<String> bdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, db.getList());

            DatesList.setAdapter(bdAdapter);
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}