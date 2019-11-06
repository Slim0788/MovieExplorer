package com.android.academy.academy_minsk_movie.repository.cache;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesImpl implements SharedPreferencesApi {

    private static final String PREF_KEY_LAST_CACHE = "last_cache";
    private static final String PREF_PACKAGE_NAME = "org.academy.preferences";

    private static SharedPreferencesImpl instance;

    private SharedPreferences sharedPreferences;

    private SharedPreferencesImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesImpl getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesImpl(context);
        }
        return instance;
    }

    @Override
    public long getLastCacheTime() {
        return sharedPreferences.getLong(PREF_KEY_LAST_CACHE, 0);
    }

    @Override
    public void setLastCacheTime(long lastCacheTime) {
        sharedPreferences.edit()
                .putLong(PREF_KEY_LAST_CACHE, lastCacheTime)
                .apply();
    }


}
