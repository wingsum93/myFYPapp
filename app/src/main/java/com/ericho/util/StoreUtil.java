package com.ericho.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ericho.fyp.datatype.internal.StorePref;

/**
 * Created by steve_000 on 15/4/2017.
 * for project myFYPapp
 * package name com.ericho.util
 */

public class StoreUtil {
    public static SharedPreferences getSharePreference(Context context, StorePref storePref) {
        return context.getSharedPreferences(storePref.getPreferenceName(), Context.MODE_PRIVATE);
    }

    public static String getValue(Context context, StorePref storePref) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(storePref.getPreferenceName(), Context.MODE_PRIVATE);
        return sharedPreferences.getString(storePref.getKey(), "");
    }

    public static void setValue(Context context, StorePref storePref, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(storePref.getPreferenceName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(storePref.getKey(), value).apply();
    }
}
