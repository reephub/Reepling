package com.reepling.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.reepling.R;
import com.reepling.utils.Utils;

/**
 * Created by Michaël on 24/03/2018.
 */

public abstract class BaseReeplingActivity extends AppCompatActivity {

    private static final String TAG = BaseReeplingActivity.class.getSimpleName();


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
        onStartNewActivity();
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
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
     *      protected final void onCreate(Bundle savedInstanceState) { }
     * 
     * @param savedInstanceState
     * @param layoutId
     */
    protected final void onCreate(Bundle savedInstanceState, int layoutId) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "onCreate() -- Shared theme id -> " + MySharedPrefs.getUserTheme(this));
        MySharedPrefs.getUserTheme(this);
        Utils.onActivityCreateSetTheme(this);

        setContentView(layoutId);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
