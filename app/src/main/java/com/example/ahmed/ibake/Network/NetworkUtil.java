package com.example.ahmed.ibake.Network;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtil {

    public static boolean isActiveAndConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if(null == cm) {
            return false;
        }

        return null != cm.getActiveNetworkInfo() && cm.getActiveNetworkInfo().isConnected();
    }
}
