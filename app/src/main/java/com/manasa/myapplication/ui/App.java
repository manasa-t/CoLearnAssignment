package com.manasa.myapplication.ui;

import android.app.Application;
import android.net.http.HttpResponseCache;
import android.util.Log;

import androidx.constraintlayout.solver.Cache;


import java.io.File;
import java.io.IOException;

public class App extends Application {

    private final String TAG = App.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
