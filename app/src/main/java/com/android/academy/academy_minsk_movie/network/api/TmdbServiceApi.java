package com.android.academy.academy_minsk_movie.network.api;

import com.android.academy.academy_minsk_movie.network.dto.MovieDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbServiceApi {

    @GET("movie/popular")
    Call<MovieDto> getPopularMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movieId}/videos")
    Call<MovieDto> getMovieVideos(
            @Path("movieId") int movieId,
            @Query("api_key") String apiKey
    );

}
