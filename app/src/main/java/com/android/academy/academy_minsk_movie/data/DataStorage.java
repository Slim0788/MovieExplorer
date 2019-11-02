package com.android.academy.academy_minsk_movie.data;

import com.android.academy.academy_minsk_movie.network.NetworkService;
import com.android.academy.academy_minsk_movie.network.dto.MovieDto;
import com.android.academy.academy_minsk_movie.network.dto.Result;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataStorage {

    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";
    private static final String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780";
    private static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";

    public interface OnResponseListener {
        void updateMovieList(List<Movie> responseMovieList);
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

    public void getMoviesList(OnResponseListener responseListener) {
        NetworkService.getInstance()
                .getJsonApi()
                .getPopularMovies(NetworkService.TMDB_API_KEY)
                .enqueue(new Callback<MovieDto>() {
                    @Override
                    public void onResponse(@NotNull Call<MovieDto> call, @NotNull Response<MovieDto> response) {
                        List<Result> resultList;
                        if (response.body() != null) {
                            resultList = response.body().getResults();

                            for (Result result : resultList) {
                                movieList.add(
                                        new Movie(
                                                result.getTitle(),
                                                result.getOverview(),
                                                POSTER_BASE_URL + result.getPosterPath(),
                                                BACKDROP_BASE_URL + result.getBackdropPath(),
                                                result.getReleaseDate(),
                                                result.getOriginalTitle()
                                        ));
                            }

                            responseListener.updateMovieList(movieList);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<MovieDto> call, @NotNull Throwable t) {

                    }
                });
    }

}
