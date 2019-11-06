package com.android.academy.academy_minsk_movie.repository;

import com.android.academy.academy_minsk_movie.data.model.Movie;
import com.android.academy.academy_minsk_movie.network.api.TmdbServiceApi;
import com.android.academy.academy_minsk_movie.repository.cache.MoviesCache;
import com.android.academy.academy_minsk_movie.service.TmdbMapper;

import java.util.List;

public class MoviesRepository {

    private TmdbServiceApi tmdbServiceApi;
    private TmdbMapper tmdbMapper;
    private MoviesCache cache;

    public MoviesRepository(TmdbServiceApi tmdbServiceApi, TmdbMapper tmdbMapper, MoviesCache cache) {
        this.tmdbServiceApi = tmdbServiceApi;
        this.tmdbMapper = tmdbMapper;
        this.cache = cache;
    }

    public List<Movie> getPopularMovies() {

        return null;
    }

    public String getMovieTrailerUrl(Movie movie) {

        return "";
    }

    public void deleteCachedData() {
        cache.clearCache();
    }

}
