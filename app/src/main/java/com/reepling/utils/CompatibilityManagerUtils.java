package com.reepling.utils;

/**
 * Created by Michaël on 07/03/2017.
 */


import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

/**
 * Class that check compatibility, SDK's version and stuff to be sure that your device can handle the actions
 */
public class CompatibilityManagerUtils {


    /** This class can't be instantiated. */
    private CompatibilityManagerUtils() { }

    /** Get the current Android API level. */
    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    /** Determine if the device is running API level 19 or higher. */
    public static boolean isKitkat(){
        return getSdkVersion() <= Build.VERSION_CODES.KITKAT;
    }

    /** Determine if the device is running API level 21 or higher. */
    public static boolean isLollipop(){
        return getSdkVersion() >= Build.VERSION_CODES.LOLLIPOP;
    }

    /** Determine if the device is running API level 23 or higher. */
    public static boolean isMarshmallow(){
        return getSdkVersion() >= Build.VERSION_CODES.M;
    }

    /** Determine if the device is running API level is higher than 21. */
    public static boolean isLollipopPlus(){
        return getSdkVersion() > Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    public static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

}
