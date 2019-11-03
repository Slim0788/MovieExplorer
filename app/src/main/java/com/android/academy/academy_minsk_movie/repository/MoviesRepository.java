package com.android.academy.academy_minsk_movie.repository;

import android.app.Application;

import com.android.academy.academy_minsk_movie.data.db.MovieDao;
import com.android.academy.academy_minsk_movie.data.db.MovieDatabase;
import com.android.academy.academy_minsk_movie.data.model.Movie;
import com.android.academy.academy_minsk_movie.network.api.TmdbServiceApi;
import com.android.academy.academy_minsk_movie.service.TmdbMapper;

import java.util.List;

public class MoviesRepository extends Application {

    private TmdbServiceApi tmdbServiceApi;
    private TmdbMapper tmdbMapper;

    MoviesRepository(TmdbServiceApi tmdbServiceApi, TmdbMapper tmdbMapper) {
        this.tmdbServiceApi = tmdbServiceApi;
        this.tmdbMapper = tmdbMapper;
    }

    public List<Movie> getPopularMovies() {

        return null;
    }


    public void setMovieToDb() {
        MovieDatabase db = MovieDatabase.getInstance(this);
        MovieDao movieDao = db.movieDao();
    }

}
