package com.reepling.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.reepling.app.AppCompatPreferenceActivity;

/**
 * Created by Michaël on 08/02/2018.
 */

public class ActivityLauncher {

    private Context context;

    public ActivityLauncher(Context context) {
        this.context = context;
    }

    public void Activity(Context context, Class<? extends AppCompatActivity> aimedClass) {
        launchActivity(context, aimedClass, null);
    }
    public void Activity(Context context, Class<? extends AppCompatActivity> aimedClass, Bundle bundle) {
        launchActivity(context, aimedClass, bundle);
    }

    public void FragmentActivity(Context context, Class<? extends FragmentActivity> aimedClass) {
        launchActivity(context, aimedClass, null);
    }
    public void FragmentActivity(Context context, Class<? extends FragmentActivity> aimedClass, Bundle bundle) {
        launchActivity(context, aimedClass, bundle);
    }

    public void AppCompatActivity(Context context, Class<? extends AppCompatActivity> aimedClass) {
        launchActivity(context, aimedClass, null);
    }

    public void AppCompatActivity(Context context, Class<? extends AppCompatActivity> aimedClass, Bundle bundle) {
        launchActivity(context, aimedClass, bundle);
    }


    public void AppCompatPreferenceActivity(Context context, Class<? extends AppCompatPreferenceActivity> aimedClass) {
        launchActivity(context, aimedClass, null);
    }


    void launchActivity(Context context, Class aimedClass, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, aimedClass);

        if (null != bundle)
            intent.putExtras(bundle);

        this.context.startActivity(intent);
    }
}
