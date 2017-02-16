package com.example.komal.dbattend;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView text;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);
        db = new DatabaseHelper(this);
        
        db.insertData("45", "FCP", "programming", 2);
        Cursor c = db.showData();
        if(c != null && c.moveToNext()){
            int i = c.getColumnIndex("courseName");
            String name = c.getString(i);
           text.setText(name);
        }

    }
}
