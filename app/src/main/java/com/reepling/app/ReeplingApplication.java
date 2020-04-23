package com.reepling.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.reepling.data.local.model.User;
import com.reepling.data.repository.ReeplingRepository;


/**
 * Created by Michaël on 08/02/2018.
 */

public class ReeplingApplication extends Application {

    public static final String TAG = ReeplingApplication.class.getSimpleName();

    public Context context;
    private static ReeplingApplication mInstance;

    private boolean isNightModeEnabled = false;

    ReeplingRepository reeplingRepository;
    public User currentUser;

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

        //Firebase crashlytics initialization
        FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();
        crashlytics.setCrashlyticsCollectionEnabled(true);
        crashlytics.setUserId("myAppUserId - Reepling");

        super.onCreate();

        context = this;

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // We load the Night Mode state here
        this.isNightModeEnabled = MySharedPrefs.getSharedPrefs(this).getBoolean("NIGHT_MODE", false);

        Log.e(TAG, "Set Theme");
        setTheme(MySharedPrefs.getUserTheme(context));

        reeplingRepository = new ReeplingRepository(this);
        reeplingRepository.loadUsersInDatabase();
    }

    public static synchronized ReeplingApplication getInstance() {
        return mInstance;
    }


    public Context getAppContext() {
        Log.i(TAG, "GetApplication de MyApplication");
        return context;
    }


    public User getCurrentUser() {
        if (null != currentUser)
            return currentUser;

        return null;
    }

    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;
    }
}
