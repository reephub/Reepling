package com.reepling.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.reepling.R;
import com.reepling.app.BaseReeplingActivity;
import com.reepling.utils.ActivityLauncher;
import com.reepling.utils.Utils;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Michaël on 08/02/2018.
 */

public class ProfileActivity extends BaseReeplingActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    @BindView(R.id.btn_modify_app_settings)
    Button buttonModifyAppSettings;

    @BindView(R.id.btn_modify_profile)
    Button buttonModifyProfile;

    @BindView(R.id.btn_help)
    Button buttonHelp;

    @BindView(R.id.btn_legal_notice)
    Button buttonLegalNotice;

    @BindView(R.id.tv_records_number)
    TextView tvRecordsNumber;

    @BindView(R.id.btn_disconnect)
    TextView buttonDisconnect;

    @BindView(R.id.tv_likes_number)
    TextView tvLikesNumber;

    @BindView(R.id.tv_offer_type)
    TextView tvOfferType;


    @SuppressLint("MissingSuperCall")
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_profile);

        ButterKnife.bind(this);

        mContext = this;


//        setSupportActionBar(toolbar);
//         toolbar fancy stuff
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
        getStatistics();
    }

    public void getStatistics(){
        checkRecords();
        checkLikes();
    }

    public void checkRecords(){
        Random r = new Random();
        int randomInt = r.nextInt(80 - 65) + 65;

        tvRecordsNumber.setText(randomInt + "");
    }

    public void checkLikes(){
        Random r = new Random();
        int randomInt = r.nextInt(100 - 32) + 85;

        tvLikesNumber.setText(randomInt + "");
    }


    @OnClick(R.id.btn_modify_app_settings)
    public void OnButtonModifyAppSettingsClick(View view) {
        mActivityLauncher.AppCompatActivity(mContext, SettingsActivity.class);

    }

    @OnClick(R.id.btn_modify_profile)
    public void OnButtonModifyProfileClick(View view) {
        mActivityLauncher.AppCompatActivity(mContext, UpdateProfileActivity.class);

    }


    @OnClick(R.id.btn_help)
    public void OnButtonHelpClick(View view) {
        mActivityLauncher.FragmentActivity(mContext, HelpActivity.class);
    }


    @OnClick(R.id.btn_disconnect)
    public void OnButtonDisconnectClick(View view){

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_profile_disconnection_message)
                .setTitle(R.string.dialog_profile_disconnection_title);

        // Add the buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                finish();
                mActivityLauncher.AppCompatActivity(mContext, LoginActivity.class);

                Utils.showActionInToast( mContext, mContext.getResources().getString(R.string.disconnected) );
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
