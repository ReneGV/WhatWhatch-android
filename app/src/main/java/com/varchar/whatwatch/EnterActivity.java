package com.varchar.whatwatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.varchar.whatwatch.utils.NetworkVerification;

//import com.varchar.WhatWatch.R;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NetworkVerification networkVerification = new NetworkVerification();
        Intent intent;
        boolean net = networkVerification.isNetAvailable(getSystemService(Context.CONNECTIVITY_SERVICE));

        if (net == true){
            Toast.makeText(getBaseContext(), "Conexión establecida", Toast.LENGTH_SHORT).show();
            intent = new Intent(getApplicationContext(), MainActivity.class);

        }
        else {
            Toast.makeText(getBaseContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
            intent = new Intent(getApplicationContext(), NoNetworkActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
