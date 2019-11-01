package com.android.academy.academy_minsk_movie.repository;

import com.android.academy.academy_minsk_movie.network.api.TmdbServiceApi;

public class MoviesRepository {

    private TmdbServiceApi tmdbServiceApi;
    private TmdbServiceMapper tmdbServiceMapper;

    MoviesRepository(TmdbServiceApi tmdbServiceApi, TmdbServiceMapper tmdbServiceMapper) {
        this.tmdbServiceApi = tmdbServiceApi;
        this.tmdbServiceMapper = tmdbServiceMapper;
    }


}
