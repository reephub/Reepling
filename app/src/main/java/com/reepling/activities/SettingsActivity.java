package com.reepling.activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;

import com.reepling.R;
import com.reepling.adapters.ColorAdapter;
import com.reepling.app.MyAppCompatActivity;
import com.reepling.app.MySharedPrefs;
import com.reepling.model.ThemeItem;
import com.reepling.utils.CompatibilityManagerUtils;
import com.reepling.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Michaël on 06/03/2018.
 */

public class SettingsActivity extends MyAppCompatActivity implements AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    //TAG & Context
    private static final String TAG = SettingsActivity.class.getSimpleName();
    private Context mContext;

    //Views
    @BindView(R.id.gv_color_picker)
    GridView gridView;

    @BindView(R.id.sw_night_mode)
    Switch swNightMode;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public int[] colorPickerList;
    public int[] themePickerList;

    private ThemeItem[] mThemeItemList;
    ColorAdapter mColorAdapter;


    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            return true;
        }
    };

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e( TAG, "Before setContentView()" );
        setContentView(R.layout.activity_settings);


        mContext = this;
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        setListener();

        prepareData();
        mColorAdapter = new ColorAdapter(this, mThemeItemList);
        gridView.setAdapter(mColorAdapter);


    }

    public void setListener(){
        gridView.setOnItemClickListener( this );
        swNightMode.setOnCheckedChangeListener( this );
    }

    private void prepareData(){

        colorPickerList =  mContext.getResources().getIntArray( R.array.user_favorite_color );

        TypedArray ar = getResources().obtainTypedArray(R.array.themePicker);
        int length = ar.length();
        themePickerList = new int[length];
        for (int i = 0; i < length; i++)
            themePickerList[i] = ar.getResourceId(i, 0);
        ar.recycle();

        mThemeItemList = new ThemeItem[ length ];
        for ( int i = 0 ; i < length ;  i ++ ){
            mThemeItemList[ i ] = new ThemeItem( colorPickerList [i], themePickerList [i] );
        }

    }


    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }


    /**
     * {@inheritDoc}
     */
    public boolean onIsMultiPane() {
        return CompatibilityManagerUtils.isXLargeTablet(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final ThemeItem item = mThemeItemList[ position ];

        Log.e( "OHOH",  item.getResourceThemeId() + "" );

        Utils.saveTheme( this, item.getResourceThemeId() );
        Utils.changeToTheme(this, item.getResourceThemeId());

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e( TAG, "switch check listener" );

        if ( !isChecked) {
            MySharedPrefs.setSharedPrefsKeyNightMode(this, false);
            swNightMode.setText(Html.fromHtml("<b><big>" + "Activer le thème sombre" + "</big></b>" + "<br />" +
                    "<small>" + "Désactivé" + "</small>" + "<br />"));
        }else {
            MySharedPrefs.setSharedPrefsKeyNightMode(this, true);
            swNightMode.setText(Html.fromHtml("<b><big>" + "Activer le thème sombre" + "</big></b>" + "<br />" +
                    "<small>" + "Activé" + "</small>" + "<br />"));
        }
    }
}
