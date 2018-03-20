package com.varchar.whatwatch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.varchar.whatwatch.utils.NetworkVerification;
import com.varchar.whatwatch.utils.PreferedTheme;

//import com.varchar.WhatWatch.R;

public class NoNetworkActivity extends AppCompatActivity {

    private Button againButton;
    private Button enterButton;
    final String  APPLICATION_THEME = "ApplicationTheme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeFromPreferences();
        setContentView(R.layout.activity_no_network);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        againButton = (Button)findViewById(R.id.againButton);
        enterButton = (Button)findViewById(R.id.enterButton);

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkVerification networkVerification = new NetworkVerification();
                Intent intent;
                boolean net = networkVerification.isNetAvailable(getSystemService(Context.CONNECTIVITY_SERVICE));

                if (net == true){
                    Toast.makeText(getBaseContext(), "Conexión establecida", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(getBaseContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
                }
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setThemeFromPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(NoNetworkActivity.this);
        int theme = preferences.getInt(APPLICATION_THEME,0);
        switch (theme){
            case 0:
                setTheme(R.style.AppTheme);
                break;
            case 1:
            default:
                setTheme(R.style.LightTheme);
                break;
        }
        Log.d("THEME", Integer.toString(theme));
    }



}
