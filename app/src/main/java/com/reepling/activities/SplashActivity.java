package com.reepling.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.reepling.R;
import com.reepling.app.BaseReeplingActivity;
import com.reepling.app.MySharedPrefs;
import com.reepling.data.local.model.User;
import com.reepling.data.repository.ReeplingRepository;

import org.parceler.Parcels;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Michaël on 08/02/2018.
 */

public class SplashActivity extends AppCompatActivity {

    /**
     * Waiting duration
     **/
    //private final int SPLASH_DISPLAY_LENGTH = 3500;

    private static final String TAG = SplashActivity.class.getSimpleName();
    private Context mContext;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Intent mainIntent;

    ReeplingRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = this;
        repository = new ReeplingRepository(this);

        /*Check If the user already connected or not
        then call Login Activity or Sign In Activity*/

        // Use RX instead of Handler
        Disposable disposable =
                Single.fromCallable(() -> MySharedPrefs.getConnectedUser(mContext))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .delay(3, TimeUnit.SECONDS)
                        .subscribe(
                                result -> {

                                    // Create an Intent that will start the Menu-Activity.
                                    Intent mainIntent = new Intent(
                                            SplashActivity.this,
                                            ((String) result).isEmpty()
                                                    ? TransitionActivity.class
                                                    : MainActivity.class);

                                    String targetClassName = mainIntent.getComponent().getClassName();
                                    String mainActivityClassName = MainActivity.class.getCanonicalName();

                                    if (targetClassName.equals(mainActivityClassName)) {
                                        repository.saveCurrentUserSession(result);
                                    }

                                    gotToTargetActivity(mainIntent);


                                    /*Log.d(TAG, "Start target activity");
                                    SplashActivity.this.startActivity(mainIntent);
                                    SplashActivity.this.finish();

                                    // Apply our splash exit (fade out)
                                    // and main entry (fade in) animation transitions.
                                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);*/
                                },
                                throwable -> Log.e(TAG, Objects.requireNonNull(throwable.getMessage())));

        compositeDisposable.add(disposable);
    }


    private Single<User> getUser(String username) {
        return repository.getUserByUsername(username);
    }

    private void gotToTargetActivity(Intent intent) {

        Log.i(TAG, "Start target activity");
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();

        // Apply our splash exit (fade out)
        // and main entry (fade in) animation transitions.
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.dispose();
    }
}
