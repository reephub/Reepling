package com.reepling.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.reepling.R;
import com.reepling.app.BaseReeplingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Michaël on 08/02/2018.
 */

public class TransitionActivity extends BaseReeplingActivity {

    @BindView(R.id.button_template_login)
    Button btnTemplateLogin;

    @BindView(R.id.button_template_signin)
    Button btnTemplateSignIn;

    @BindView(R.id.button_template_mainactivity)
    Button btnTemplateMainActivity;

    @BindView(R.id.button_template_ad_activity)
    Button btnTemplateAdActivity;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_transition);

        // bind the view using butterknife
        ButterKnife.bind(this);

        mContext = this;
    }

    @OnClick(R.id.button_template_login)
    public void onButtonLoginClick() {
        Toast.makeText(mContext, "You have clicked on login", Toast.LENGTH_SHORT).show();

        mActivityLauncher.AppCompatActivity(mContext, LoginActivity.class);
    }

    @OnClick(R.id.button_template_signin)
    public void onButtonSignInClick() {
        Toast.makeText(mContext, "You have clicked on sign in", Toast.LENGTH_SHORT).show();

        mActivityLauncher.AppCompatActivity(mContext, SignUpActivity.class);
    }

    @OnClick(R.id.button_template_mainactivity)
    public void onButtonMainActivityClick() {
        Toast.makeText(mContext, "You have clicked on main activity", Toast.LENGTH_SHORT).show();

        mActivityLauncher.FragmentActivity(mContext, MainActivity.class);
    }

    @OnClick(R.id.button_template_ad_activity)
    public void onButtonAdActivityClick() {
        Toast.makeText(mContext, "You have clicked on ad activity", Toast.LENGTH_SHORT).show();

        mActivityLauncher.FragmentActivity(mContext, AdMobActivity.class);
    }
}
