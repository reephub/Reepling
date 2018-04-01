package com.reepling.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.google.android.gms.ads.MobileAds;
import com.reepling.R;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Michaël on 08/02/2018.
 */

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    public  Context context;
    private static MyApplication mInstance;

    private boolean isNightModeEnabled = false;


    public MyApplication(){
        Log.i(TAG, "Construct de MyApplication");
        mInstance = this;
    }


    @Override
    public void onCreate() {
        //Init fabric stuff
//        Fabric.with(this,  new Crashlytics());
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Answers(), new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        super.onCreate();

        context = this;
        MobileAds.initialize(this, getString(R.string.admob_app_id) );

        // We load the Night Mode state here
        this.isNightModeEnabled = MySharedPrefs.getSharedPrefs( this ).getBoolean("NIGHT_MODE", false);
        setTheme( MySharedPrefs.getUserTheme( context ) );

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public Context getAppContext() {
        Log.i(TAG, "GetApplication de MyApplication");
        return context;
    }


    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }
    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;
    }


}
