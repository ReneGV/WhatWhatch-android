package com.varchar.whatwatch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.varchar.whatwatch.fragment.DetailFragment;
import com.varchar.whatwatch.fragment.MailFragment;
import com.varchar.whatwatch.fragment.VideoMediaFragment;
import com.varchar.whatwatch.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // TODO Edit scope
    final String  APPLICATION_THEME = "ApplicationTheme";
    final String  CATALOG = "Catalog";

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.containerMain, VideoMediaFragment.newInstance("",""),"SERIES").commit();

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
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        /*
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
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

        //to save series or movies catalog
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        if (id == R.id.nav_movie) {
            editor.putInt(CATALOG, 0);
            editor.apply();
            switchFragment(R.id.containerMain, VideoMediaFragment.newInstance("",""), "MOVIE");
            drawer.closeDrawer(GravityCompat.START);

            // Handle the camera action
        } else if (id == R.id.nav_serie) {
            editor.putInt(CATALOG, 1);
            editor.apply();
            switchFragment(R.id.containerMain, VideoMediaFragment.newInstance("",""), "SERIE");
            getSupportFragmentManager().popBackStack();
            drawer.closeDrawer(GravityCompat.START);

        } //else if (id == R.id.nav_premium) {}
        else if (id == R.id.favourites) {
            switchFragment(R.id.containerMain, DetailFragment.newInstance("",""), "FAVORITE");
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.nav_mail) {
            switchFragment(R.id.containerMain, MailFragment.newInstance("",""), "MAIL");
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_settings) {
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
            if (!(fragment instanceof VideoMediaFragment)){
                transaction.addToBackStack(tag);
            }
            transaction.commit();
        }
    }


}
