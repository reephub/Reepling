package com.reepling.utils;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.reepling.R;
import com.reepling.app.MySharedPrefs;


/**
 * Created by Michaël on 08/02/2018.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public Utils() {
    }

    public static void showActionInToast(Context context, String textToShow) {
        Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
    }

    public static void showActionInSnackBar(Context context, View view, String message, SnackBarType type) {
        // create instance
        Snackbar snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        /*switch (type){
            case NORMAL:
                // set action button color
                snackBar.setActionTextColor(context.getResources().getColor(R.color.indigo));
                break;

            case WARNING:
                snackBar.setActionTextColor(context.getResources().getColor(R.color.indigo));
                break;

            case ALERT:
                snackBar.setActionTextColor(context.getResources().getColor(R.color.indigo));
                break;
        }*/

        // get snackBar view
        View snackBarView = snackBar.getView();

        // change snackbar text color
        int snackBarTextId = com.google.android.material.R.id.snackbar_text;
        TextView textView = (TextView) snackBarView.findViewById(snackBarTextId);
        switch (type) {
            case NORMAL:
                // set action button color
                textView.setTextColor(context.getResources().getColor(R.color.white));
                break;

            case WARNING:
                textView.setTextColor(context.getResources().getColor(R.color.filterListViewColorPrimary));
                break;

            case ALERT:
                textView.setTextColor(context.getResources().getColor(R.color.locationColorPrimary));
                break;
        }

        snackBar.show();

        /*
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setActionTextColor()
                .setAction("Action", null).show();
                */
    }


    public static boolean isSameDomain(String url, String url1) {
        return getRootDomainUrl(url.toLowerCase()).equals(getRootDomainUrl(url1.toLowerCase()));
    }

    private static String getRootDomainUrl(String url) {
        String[] domainKeys = url.split("/")[2].split("\\.");
        int length = domainKeys.length;
        int dummy = domainKeys[0].equals("www") ? 1 : 0;
        if (length - dummy == 2)
            return domainKeys[length - 2] + "." + domainKeys[length - 1];
        else {
            if (domainKeys[length - 1].length() == 2) {
                return domainKeys[length - 3] + "." + domainKeys[length - 2] + "." + domainKeys[length - 1];
            } else {
                return domainKeys[length - 2] + "." + domainKeys[length - 1];
            }
        }
    }


    /**
     * Function to convert milliseconds time to
     * Timer Format
     * Hours:Minutes:Seconds
     */
    public String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    /**
     * Function to get Progress percentage
     *
     * @param currentDuration
     * @param totalDuration
     */
    public int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

    /**
     * Function to change progress to timer
     *
     * @param progress      -
     * @param totalDuration returns current duration in milliseconds
     */
    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }

    private static int sTheme;
    public final static int THEME_RED = 0;
    public final static int THEME_BLUE = 1;
    public final static int THEME_GREEN = 2;
    public final static int THEME_YELLOW = 3;
    public final static int THEME_BROWN = 4;
    public final static int THEME_GREY = 5;
    public final static int THEME_WHITE = 6;
    public final static int THEME_PURPLE = 7;
    public final static int THEME_PINK = 8;
//    public final static int THEME_DEFAULT = 9;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(AppCompatActivity activity, int theme) {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /**
     * Set the theme of the activity, according to the configuration.
     */
    public static void onActivityCreateSetTheme(AppCompatActivity activity) {
        if (-1 != sTheme)
            activity.setTheme(sTheme);
        else
            activity.setTheme(MySharedPrefs.getUserTheme(activity));
        /*
        switch (sTheme)
        {
            case THEME_RED:
                activity.setTheme(R.style.RedTheme);
                break;
            case THEME_BLUE:
                activity.setTheme(R.style.BlueTheme);
                break;
            case THEME_GREEN:
                activity.setTheme(R.style.GreenTheme);
                break;
            case THEME_YELLOW:
                activity.setTheme(R.style.YellowTheme);
                break;
            case THEME_BROWN:
                activity.setTheme(R.style.BrownTheme);
                break;
            case THEME_GREY:
                activity.setTheme(R.style.GreyTheme);
                break;
            case THEME_WHITE:
                activity.setTheme(R.style.WhiteTheme);
                break;
            case THEME_PURPLE:
                activity.setTheme(R.style.PurpleTheme);
                break;
            case THEME_PINK:
                activity.setTheme(R.style.PinkTheme);
                break;
            default:
                break;
        }
        */
    }

    public static void onActivityCreateSetTheme(AppCompatActivity activity, int theme) {
        sTheme = theme;
        if (-1 != sTheme)
            activity.setTheme(sTheme);
        else
            activity.setTheme(MySharedPrefs.getUserTheme(activity));
    }

    public static void saveTheme(Context context, int themId) {
        MySharedPrefs.setSharedPrefsKeyUserTheme(context, themId);
    }

}
