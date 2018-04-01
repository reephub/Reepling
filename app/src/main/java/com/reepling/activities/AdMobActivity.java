package com.reepling.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.reepling.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Michaël on 18/02/2018.
 */

public class AdMobActivity extends AppCompatActivity {

    private static final String TAG = AdMobActivity.class.getSimpleName();

    @BindView( R.id.adView )
    AdView mAdView;

    InterstitialAd mInterstitialAd;

    public AdRequest adRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob);

        ButterKnife.bind(this);

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.google_test_full_screen_id));

        //Testing
        adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("672278EF051F94F97789CDF61CB2BD36") //672278EF051F94F97789CDF61CB2BD36
                .build();

//        adRequest.isTestDevice(this);

//        mAdView.loadAd( adRequest );

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {

                Log.i(TAG,"Ad is loaded --> Show ad");
                showInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.e( TAG, "Error : " + errorCode );
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i(TAG,"Ad is opened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i(TAG,"Ad has left the app");

            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.e(TAG,"Ad is closed");
            }

        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}
