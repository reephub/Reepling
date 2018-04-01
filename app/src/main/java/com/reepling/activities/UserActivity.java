package com.reepling.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reepling.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Michaël on 08/02/2018.
 */

public class UserActivity extends AppCompatActivity {

     private static final String TAG = UserActivity.class.getSimpleName();

     //Views
    @BindView(R.id.iv_user_profile)
     ImageView ivUserImage;

    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

     //Const
    public static final String BUNDLE_USER_NAME = "user_name";
    public static final String BUNDLE_USER_FIRST_NAME = "user_first_name";
    public static final String BUNDLE_USER_PROFILE_IMAGE = "user_profile_image";

    private Context mContext;

    private Bundle mExtras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mContext = this;

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getBundle();
    }

    private void getBundle(){
        mExtras  = getIntent().getExtras();

        if (null == mExtras){
            Log.e(TAG, "Bundle is null");
            return;
        }

        String name, firstname, url = "";
        String separator = " / ";

        Log.i(TAG, "Bundle has a value\n"
                + "Name : " + mExtras.getString(BUNDLE_USER_NAME) + "\n"
                + "First Name : " + mExtras.getString(BUNDLE_USER_FIRST_NAME) + "\n"
                + "URL : " + mExtras.getString(BUNDLE_USER_PROFILE_IMAGE) + "\n");

        name = mExtras.getString(BUNDLE_USER_NAME);
        firstname = mExtras.getString(BUNDLE_USER_FIRST_NAME);
        url = mExtras.getString(BUNDLE_USER_PROFILE_IMAGE);


        tvUserName.setText( name /*+ separator + firstname*/ );

        Glide.with(this)
                .load(url)
                .into(ivUserImage);

    }
}
