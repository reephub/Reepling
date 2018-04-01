package com.reepling.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.reepling.R;
import com.reepling.utils.ActivityLauncher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Michaël on 08/02/2018.
 */

public class TransitionActivity extends AppCompatActivity {

    @BindView(R.id.button_template_login)
    Button btnTemplateLogin;

    @BindView(R.id.button_template_signin)
    Button btnTemplateSignIn;

    @BindView(R.id.button_template_mainactivity)
    Button btnTemplateMainActivity;


    @BindView(R.id.button_template_ad_activity)
    Button btnTemplateAdActivity;

    private Context mContext;
    private ActivityLauncher mActivityLauncher;

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        mContext = this;
        mActivityLauncher = new ActivityLauncher(mContext);

        // bind the view using butterknife
        ButterKnife.bind(this);

    }

    @OnClick (R.id.button_template_login)
    public void onButtonLoginClick(View view){
        Toast.makeText(mContext, "You have clicked on login", Toast.LENGTH_SHORT).show();

        mActivityLauncher.AppCompatActivity(mContext, LoginActivity.class);
    }

    @OnClick (R.id.button_template_signin)
    public void onButtonSignInClick(View view){
        Toast.makeText(mContext, "You have clicked on sign in", Toast.LENGTH_SHORT).show();

        mActivityLauncher.AppCompatActivity(mContext, SignUpActivity.class);
    }

    @OnClick (R.id.button_template_mainactivity)
    public void onButtonMainActivityClick(View view){
        Toast.makeText(mContext, "You have clicked on main activity", Toast.LENGTH_SHORT).show();

        mActivityLauncher.FragmentActivity(mContext, MainActivity.class);
    }

    @OnClick (R.id.button_template_ad_activity)
    public void onButtonAdActivityClick(View view){
        Toast.makeText(mContext, "You have clicked on ad activity", Toast.LENGTH_SHORT).show();

        mActivityLauncher.FragmentActivity(mContext, AdMobActivity.class);
    }
}
