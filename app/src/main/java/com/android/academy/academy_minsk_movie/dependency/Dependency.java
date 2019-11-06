package com.android.academy.academy_minsk_movie.dependency;

import com.android.academy.academy_minsk_movie.App;
import com.android.academy.academy_minsk_movie.data.db.AppDatabase;
import com.android.academy.academy_minsk_movie.network.NetworkService;
import com.android.academy.academy_minsk_movie.network.api.TmdbServiceApi;
import com.android.academy.academy_minsk_movie.repository.MoviesRepository;
import com.android.academy.academy_minsk_movie.repository.cache.MoviesCache;
import com.android.academy.academy_minsk_movie.repository.cache.MoviesCacheImpl;
import com.android.academy.academy_minsk_movie.repository.cache.SharedPreferencesApi;
import com.android.academy.academy_minsk_movie.repository.cache.SharedPreferencesImpl;
import com.android.academy.academy_minsk_movie.service.TmdbMapper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dependency {

    private static AppDatabase db;
    private static MoviesRepository repository;

    Dependency() {
        db = createAppDatabase();
        repository = createMoviesRepository();
    }

    private static MoviesRepository createMoviesRepository() {
        return new MoviesRepository(
                createTmdbServiceApi(),
                createTmdbMapper(),
                createMoviesCache()
        );
    }

    private static TmdbMapper createTmdbMapper() {
        return new TmdbMapper();
    }

    private static TmdbServiceApi createTmdbServiceApi() {
        return NetworkService.getInstance().getJsonApi();
    }

    private static MoviesCache createMoviesCache() {
        return new MoviesCacheImpl(
                db.movieDao(),
                db.videoDao(),
                createSharedPreferences()
        );
    }

    private static AppDatabase createAppDatabase() {
        return AppDatabase.getInstance(App.instance);
    }

    private static SharedPreferencesApi createSharedPreferences() {
        return SharedPreferencesImpl.getInstance(App.instance);
    }

}
