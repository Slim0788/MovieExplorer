package com.android.academy.academy_minsk_movie.service;

import com.android.academy.academy_minsk_movie.data.model.Movie;
import com.android.academy.academy_minsk_movie.network.dto.MovieDto;
import com.android.academy.academy_minsk_movie.network.dto.PopularMovieDto;
import com.android.academy.academy_minsk_movie.network.dto.VideoUrlDto;

import java.util.ArrayList;
import java.util.List;

public class TmdbMapper {

    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";
    private static final String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780";
    private static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    public static List<Movie> mapMovieList(PopularMovieDto popularMovieDto) {
        List<Movie> resultList = new ArrayList<>();
        for (MovieDto movieDto : popularMovieDto.getPopularMovieList()) {
            resultList.add(mapMovie(movieDto));
        }
        return resultList;
    }

    private static Movie mapMovie(MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getOverview(),
                POSTER_BASE_URL + movieDto.getPosterPath(),
                BACKDROP_BASE_URL + movieDto.getBackdropPath(),
                movieDto.getReleaseDate()
        );
    }

    public static String mapTrailerUrl(VideoUrlDto videoUrlDto) {
        return YOUTUBE_BASE_URL + videoUrlDto.getVideoUrlKey();
    }

}
