package com.varchar.whatwatch.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.varchar.whatwatch.MainActivity;
import com.varchar.whatwatch.R;

/**
 * Created by Zeider on 19/03/2018.
 */

public class PreferedTheme {
    final String  APPLICATION_THEME = "ApplicationTheme";

    public void setThemeFromPreferences(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int theme = preferences.getInt(APPLICATION_THEME,0);
        switch (theme){
            case 0:
                context.setTheme(R.style.AppTheme);
                break;
            case 1:
            default:
                context.setTheme(R.style.LightTheme);
                break;
        }
        Log.d("THEME", Integer.toString(theme));
    }

}
