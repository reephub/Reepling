package com.reepling.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reepling.R;
import com.reepling.fragments.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Michaël on 12/02/2018.
 */

public class MainFragment extends Fragment {

    private static final String TAG = MainFragment.class.getSimpleName();

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.tabViewPager)
    ViewPager mViewPager;

    private Context mContext;
    private FragmentActivity mActivity;

    private MainFragment fragment;

    private Unbinder unbinder;

    public MainFragment(){
        fragment = this;
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        unbinder  = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabLayout.setupWithViewPager(mViewPager);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(mActivity.getSupportFragmentManager());

        adapter.addFragment(new CreationFragment(), mActivity.getApplicationContext().getResources().getString(R.string.tabs_text_creation));
        adapter.addFragment(new ListeningFragment(), mActivity.getApplicationContext().getResources().getString(R.string.tabs_text_listening));
        adapter.addFragment(new CommunityFragment(), mActivity.getApplicationContext().getResources().getString(R.string.tabs_text_community));

        viewPager.setAdapter(adapter);
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
