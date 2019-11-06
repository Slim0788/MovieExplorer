package com.android.academy.academy_minsk_movie.repository.cache;

import com.android.academy.academy_minsk_movie.data.db.MovieDao;
import com.android.academy.academy_minsk_movie.data.db.VideoDao;
import com.android.academy.academy_minsk_movie.data.model.Movie;
import com.android.academy.academy_minsk_movie.data.model.Video;

import java.util.List;

public class MoviesCacheImpl implements MoviesCache {

    private MovieDao movieDao;
    private VideoDao videoDao;
    private SharedPreferencesApi sharedPreferencesApi;

    public MoviesCacheImpl(
            MovieDao movieDao,
            VideoDao videoDao,
            SharedPreferencesApi sharedPreferencesApi) {

        this.movieDao = movieDao;
        this.videoDao = videoDao;
        this.sharedPreferencesApi = sharedPreferencesApi;
    }

    @Override
    public boolean isCached() {
        return false;
    }

    @Override
    public List<Movie> getMovies() {

        return null;
    }

    @Override
    public String getMovieTrailerUrl(int movieId) {
        return null;
    }

    @Override
    public void insertMovies(List<Movie> movies) {

    }

    @Override
    public void insertVideo(Video video) {

    }

    @Override
    public void clearCache() {

    }

}
