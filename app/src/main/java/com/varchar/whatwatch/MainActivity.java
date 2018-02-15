package com.varchar.whatwatch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // TODO Edit scope
    final String  APPLICATION_THEME = "ApplicationTheme";


    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    //private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Fetch the theme acording to shared preferences configuration eg: set
        setThemeFromPreferences();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.containerMain,SeriesFragment.newInstance("",""),"SERIES").commit();

        //floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
       /* floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Cierra y vuelve a abrir",Snackbar.LENGTH_LONG).show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                if (preferences.getInt(APPLICATION_THEME,0) == 0){
                    editor.putInt(APPLICATION_THEME, 1);
                }else {
                    editor.putInt(APPLICATION_THEME, 0);
                }
                editor.apply();
            }
        });*/

    }

    public  void setThemeFromPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        navigationView.setCheckedItem(item.getItemId());
        item.setChecked(true);
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        if (id == R.id.nav_movie) {
            // Handle the camera action
        } else if (id == R.id.nav_serie) {
            getSupportFragmentManager().popBackStack();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_premium) {

        } else if (id == R.id.nav_mail) {
            switchFragment(R.id.containerMain, MailFragment.newInstance("",""), "MAIL");
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_mail) {
            switchFragment(R.id.containerMain, MailFragment.newInstance("",""), "MAIL");
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_settings) {
            switchFragment(R.id.containerMain, SettingsFragment.newInstance("",""), "SETTINGS");
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

        return false;
    }

    private void switchFragment(int idContainer, Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment != null){
            FragmentTransaction transaction = null;
            while (fragmentManager.popBackStackImmediate());
            transaction = fragmentManager.beginTransaction().replace(idContainer, fragment);
            if (!(fragment instanceof SeriesFragment)){
                transaction.addToBackStack(tag);
            }
            transaction.commit();
        }
    }
}
