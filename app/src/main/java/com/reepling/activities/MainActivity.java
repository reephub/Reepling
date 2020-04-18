package com.reepling.activities;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.reepling.R;
import com.reepling.app.BaseReeplingActivity;
import com.reepling.fragments.CommunityFragment;
import com.reepling.fragments.CreationFragment;
import com.reepling.fragments.ListeningFragment;
import com.reepling.fragments.adapter.ViewPagerAdapter;
import com.reepling.utils.ActivityLauncher;
import com.reepling.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseReeplingActivity {

    //TAG
    private static final String TAG = MainActivity.class.getSimpleName();

    //Views
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.tabViewPager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ActivityLauncher mActivityLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);

        mActivityLauncher = new ActivityLauncher(this);

        mTabLayout.setupWithViewPager(mViewPager);
        setupViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new CreationFragment(), getApplicationContext().getResources().getString(R.string.tabs_text_creation));
        adapter.addFragment(new ListeningFragment(), getApplicationContext().getResources().getString(R.string.tabs_text_listening));
        adapter.addFragment(new CommunityFragment(), getApplicationContext().getResources().getString(R.string.tabs_text_community));

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        /*
         * Make the image clickable because it's a custom toolbar
         */
        final MenuItem profileUserImageItem = menu.findItem(R.id.action_user);

        ConstraintLayout rootView =  (ConstraintLayout) MenuItemCompat.getActionView(profileUserImageItem);

        rootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.onOptionsItemSelected(profileUserImageItem);
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Log.e(TAG,"Id clicked : " + id);

        switch (id){
            case R.id.action_search:
                Log.i(TAG, "Search icon clicked");
                Utils.showActionInToast(this, "Search icon clicked");
                mActivityLauncher.AppCompatActivity(this, SearchUserActivity.class);
                break;

            case R.id.action_user:
                Log.i(TAG, "User icon clicked");
                Utils.showActionInToast(this, "User icon clicked");
                mActivityLauncher.AppCompatActivity(this, ProfileActivity.class);
                break;

                default:
                    return super.onOptionsItemSelected( item );
        }
        return true;
    }

}
