package com.varchar.whatwatch;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.varchar.WhatWatch.R;

public class NoNetworkActivity extends AppCompatActivity {

    private Button againButton;
    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        againButton = (Button)findViewById(R.id.againButton);
        enterButton = (Button)findViewById(R.id.enterButton);

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkVerification networkVerification = new NetworkVerification();
                Intent i;
                boolean net = networkVerification.isNetAvailable(getSystemService(Context.CONNECTIVITY_SERVICE));

                if (net == true){
                    Toast.makeText(getBaseContext(), "Conexión establecida", Toast.LENGTH_SHORT).show();
                    i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(getBaseContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
                }
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }


}
