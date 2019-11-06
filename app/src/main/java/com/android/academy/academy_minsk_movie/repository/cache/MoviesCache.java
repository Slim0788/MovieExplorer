package com.android.academy.academy_minsk_movie.repository.cache;

import com.android.academy.academy_minsk_movie.data.model.Movie;
import com.android.academy.academy_minsk_movie.data.model.Video;

import java.util.List;

public interface MoviesCache {

    boolean isCached();

    List<Movie> getMovies();

    String getMovieTrailerUrl(int movieId);

    void insertMovies(List<Movie> movies);

    void insertVideo(Video video);

    void clearCache();

}
