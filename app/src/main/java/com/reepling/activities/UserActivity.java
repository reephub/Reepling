package com.reepling.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.reepling.R;
import com.reepling.app.BaseReeplingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Michaël on 08/02/2018.
 */

public class UserActivity extends BaseReeplingActivity {

    private static final String TAG = UserActivity.class.getSimpleName();

    //Views
    @BindView(R.id.iv_user_profile)
    ImageView ivUserImage;

    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    //Const
    public static final String BUNDLE_USER_NAME = "user_name";
    public static final String BUNDLE_USER_FIRST_NAME = "user_first_name";
    public static final String BUNDLE_USER_PROFILE_IMAGE = "user_profile_image";

    String separator = " / ";

    private Bundle mExtras;

    private String mUserName;
    private String mUserFirstName;
    private String mUserProfileImageURL;


    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_user);

        getBundle();

        mContext = this;

        ButterKnife.bind(this);

        displayData();
    }

    private void getBundle() {
        mExtras = getIntent().getExtras();

        if (null == mExtras) {
            Log.e(TAG, "Bundle is null");
            return;
        }

        Log.i(TAG, "Bundle has a value\n"
                + "Name : " + mExtras.getString(BUNDLE_USER_NAME) + "\n"
                + "First Name : " + mExtras.getString(BUNDLE_USER_FIRST_NAME) + "\n"
                + "URL : " + mExtras.getString(BUNDLE_USER_PROFILE_IMAGE) + "\n");

        mUserName = mExtras.getString(BUNDLE_USER_NAME);
        mUserFirstName = mExtras.getString(BUNDLE_USER_FIRST_NAME);
        mUserProfileImageURL = mExtras.getString(BUNDLE_USER_PROFILE_IMAGE);
    }

    private void displayData() {
        tvUserName.setText(mUserName);

        Glide.with(this)
                .load(mUserProfileImageURL)
                .into(ivUserImage);
    }
}
