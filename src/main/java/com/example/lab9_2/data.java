package com.example.lab9_2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.SQLException;

public class data extends AppCompatActivity {
    TextView txtdisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        txtdisplay = (TextView) findViewById(R.id.txtDisplay);
        try{
            BDatesDB db = new BDatesDB(this);
            db.open();
            txtdisplay.setText(db.getRecord());
            db.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}