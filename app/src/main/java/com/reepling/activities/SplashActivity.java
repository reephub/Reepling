package com.reepling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.reepling.R;


/**
 * Created by Michaël on 08/02/2018.
 */

public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    //private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*Check If the user already connected or not
        then call Login Activity or Sign In Activity*/

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, TransitionActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();

                 /* Apply our splash exit (fade out) and main
                    entry (fade in) animation transitions. */
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
        }, 3500);
    }
}
