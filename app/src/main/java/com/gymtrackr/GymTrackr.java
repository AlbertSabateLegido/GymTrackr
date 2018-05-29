package com.gymtrackr;


import android.app.Application;
import android.content.Context;

public class GymTrackr extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        GymTrackr.context = getApplicationContext();
    }

    public static Context getContext() {
        return GymTrackr.context;
    }
}
