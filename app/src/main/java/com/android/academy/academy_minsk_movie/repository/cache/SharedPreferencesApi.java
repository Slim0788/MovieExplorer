package com.android.academy.academy_minsk_movie.repository.cache;

public interface SharedPreferencesApi {

    long getLastCacheTime();

    void setLastCacheTime(long lastCacheTime);

}
