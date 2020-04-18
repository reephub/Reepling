package com.reepling.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.reepling.R;


/**
 * Created by Michaël on 08/02/2018.
 */

public class ReeplingApplication extends Application {

    public static final String TAG = ReeplingApplication.class.getSimpleName();

    public Context context;
    private static ReeplingApplication mInstance;

    private boolean isNightModeEnabled = false;


    public ReeplingApplication() {
        Log.i(TAG, "Construct de MyApplication");
        mInstance = this;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        //Init fabric stuff
//        Fabric.with(this,  new Crashlytics());
//        final Fabric fabric = new Fabric.Builder(this)
//                .kits(new Answers(), new Crashlytics())
//                .debuggable(true)
//                .build();
//        Fabric.with(fabric);

        //Firebase crashlytics initialization
        FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();
        crashlytics.setCrashlyticsCollectionEnabled(true);
        crashlytics.setUserId("myAppUserId - Reepling");

        super.onCreate();

        context = this;

        // Old initialization of google ad mob
//        MobileAds.initialize(this, getString(R.string.admob_app_id));


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        // We load the Night Mode state here
        this.isNightModeEnabled = MySharedPrefs.getSharedPrefs(this).getBoolean("NIGHT_MODE", false);

        Log.e(TAG, "Set Theme");
        setTheme(MySharedPrefs.getUserTheme(context));

    }

    public static synchronized ReeplingApplication getInstance() {
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
