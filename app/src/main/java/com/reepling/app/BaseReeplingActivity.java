package com.reepling.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.reepling.R;
import com.reepling.activities.MainActivity;
import com.reepling.activities.TransitionActivity;
import com.reepling.data.local.model.User;
import com.reepling.utils.ActivityLauncher;
import com.reepling.utils.Utils;

import org.parceler.Parcels;

/**
 * Created by Michaël on 24/03/2018.
 */

public abstract class BaseReeplingActivity extends AppCompatActivity {

    private static final String TAG = BaseReeplingActivity.class.getSimpleName();
    protected Activity mActivity;
    protected Context mContext;

    Toolbar toolbar;

    protected ActivityLauncher mActivityLauncher;

    protected User user;
    public static final String BUNDLE_CURRENT_USER = "CURRENT_USER";

    /////////////////////////////////////
    //
    // OVERRIDE
    //
    /////////////////////////////////////


    // It's cleaner to animate the start of new activities the same way.
    // Override startActivity(), and call *overridePendingTransition*
    // right after the super, so every single activity transaction will be animated:

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        Log.e(TAG, "startActivity()");
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);

        Log.e(TAG, "startActivity(intent, options)");
        onStartNewActivity();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");

        // Check value
        MySharedPrefs.getUserTheme(this);

        Utils.onActivityCreateSetTheme(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        Log.e(TAG, "finish()");
        onLeaveThisActivity();
    }
    /////////////////////////////////////
    //
    // OVERRIDE
    //
    /////////////////////////////////////


    /////////////////////////////////////
    //
    // CLASS METHODS
    //
    /////////////////////////////////////

    /**
     * Override AppCompat's onCreate method in order to set toolbar with user theme
     * IMPORTANT : in inherited class declared onCreate following this :
     * protected final void onCreate(Bundle savedInstanceState) {
     * super.onCreate(savedInstanceState, R.layout.some_layout);
     * }
     *
     * @param savedInstanceState
     * @param layoutId
     */
    protected final void onCreate(Bundle savedInstanceState, int layoutId) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");

        MySharedPrefs.getUserTheme(this);
        // TODO: modify this properly
        Utils.onActivityCreateSetTheme(this, MySharedPrefs.getUserTheme(this));

        setContentView(layoutId);

        //Init
        mContext = this;
        mActivity = this;
        mActivityLauncher = new ActivityLauncher(mContext);

//        if (!(this instanceof SplashActivity)){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        }


        // If transition activity don't show return arrow in toolbar
        if (!(this instanceof TransitionActivity
                || this instanceof MainActivity)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (this instanceof TransitionActivity) {
            getSupportActionBar().setTitle("Transition activity");
        }

    }

    protected void onStartNewActivity() {
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    protected void onLeaveThisActivity() {
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }
    /////////////////////////////////////
    //
    // CLASS METHODS
    //
    /////////////////////////////////////
}
