package com.example.listviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnArrayAdapter ;
    Button btnCustomAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnArrayAdapter = (Button) findViewById(R.id.arrayAdapter);
        btnArrayAdapter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , ArrayAdapterActivity.class);
                startActivity(intent);
            }
        });

        btnCustomAdapter = (Button) findViewById(R.id.customAdapter);
        btnCustomAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , CustomAdapterActivity.class);
                startActivity(intent);
            }
        });
    }
}
