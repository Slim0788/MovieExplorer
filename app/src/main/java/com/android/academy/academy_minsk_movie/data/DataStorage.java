package com.android.academy.academy_minsk_movie.data;

import com.android.academy.academy_minsk_movie.data.model.Movie;
import com.android.academy.academy_minsk_movie.network.NetworkService;
import com.android.academy.academy_minsk_movie.network.dto.MovieVideoDto;
import com.android.academy.academy_minsk_movie.network.dto.PopularMovieDto;
import com.android.academy.academy_minsk_movie.service.TmdbMapper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataStorage {

    public interface OnMovieListResponseListener {
        void updateMovieList(List<Movie> responseMovieList);
    }

    public interface OnMovieTrailerResponseListener {
        void setMovieTrailer(String trailerUrl);
    }

    private static DataStorage instance;
    private List<Movie> movieList = new ArrayList<>();

    private DataStorage() {

    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void getMoviesList(OnMovieListResponseListener movieListResponseListener) {
        NetworkService.getInstance()
                .getJsonApi()
                .getPopularMovies(NetworkService.TMDB_API_KEY)
                .enqueue(new Callback<PopularMovieDto>() {
                    @Override
                    public void onResponse(@NotNull Call<PopularMovieDto> call, @NotNull Response<PopularMovieDto> response) {
                        if (response.body() != null) {

                            movieList = TmdbMapper.mapMovieList(response.body());

                            movieListResponseListener.updateMovieList(movieList);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<PopularMovieDto> call, @NotNull Throwable t) {

                    }
                });
    }

    public void getMovieTrailer(int id, OnMovieTrailerResponseListener movieTrailerResponseListener) {
        NetworkService.getInstance()
                .getJsonApi()
                .getMovieVideos(id, NetworkService.TMDB_API_KEY)
                .enqueue(new Callback<MovieVideoDto>() {
                    @Override
                    public void onResponse(@NotNull Call<MovieVideoDto> call, @NotNull Response<MovieVideoDto> response) {
                        if (response.body() != null) {
                            String trailerUrl = TmdbMapper.mapTrailerUrl(response.body().getVideoDetails().get(0));
                            movieTrailerResponseListener.setMovieTrailer(trailerUrl);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MovieVideoDto> call, @NotNull Throwable t) {

                    }
                });
    }

}
