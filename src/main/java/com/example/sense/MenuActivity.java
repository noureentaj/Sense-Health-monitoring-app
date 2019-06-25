package com.example.sense;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
Button rep,rep2,rep3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rep = (Button) findViewById(R.id.button3);
        rep2 = (Button) findViewById(R.id.button5);
        rep3 = (Button) findViewById(R.id.button2);

        // Capture button clicks
        rep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MenuActivity.this,
                        HealthActivity.class);
                startActivity(myIntent);
            }
        });
        rep2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MenuActivity.this,
                        HumidityActivity.class);
                startActivity(myIntent);
            }
        });

        rep3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MenuActivity.this,
                        MapsActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
