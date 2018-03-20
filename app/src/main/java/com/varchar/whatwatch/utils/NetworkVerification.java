package com.varchar.whatwatch.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Zeider on 19/03/2018.
 */

public class NetworkVerification {

    public boolean isNetAvailable(Object serviceSystemGet) {

        boolean networkState = false;
        boolean networkaccess = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)serviceSystemGet;


        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            networkaccess = connectivityManager.getActiveNetworkInfo().isConnected();
            networkState = true;
        }
        else
            networkState = false;

        if (networkState==true && networkaccess==true){
            return true;
        }
        else {
            return false;
        }
    }

}
