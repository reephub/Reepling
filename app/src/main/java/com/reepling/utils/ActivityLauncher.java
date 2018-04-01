package com.reepling.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.reepling.app.AppCompatPreferenceActivity;

/**
 * Created by Michaël on 08/02/2018.
 */

public class ActivityLauncher {

    private Context context;

    public ActivityLauncher(Context context){
        this.context = context;
    }

    public void Activity(Context context, Class<? extends Activity> aimedClass){
        launchActivity(context, aimedClass);
    }

    public void FragmentActivity(Context context, Class<? extends FragmentActivity> aimedClass){
        launchActivity(context, aimedClass);
    }

    public void AppCompatActivity(Context context, Class<? extends AppCompatActivity> aimedClass){
        launchActivity(context, aimedClass);
    }


    public void AppCompatPreferenceActivity(Context context, Class<? extends AppCompatPreferenceActivity> aimedClass){
        launchActivity(context, aimedClass);
    }

    void launchActivity(Context context, Class aimedClass){
        Intent intent = new Intent(context, aimedClass);
        this.context.startActivity(intent);
    }
}
