package com.android.academy.academy_minsk_movie.data;

import com.android.academy.academy_minsk_movie.network.NetworkService;
import com.android.academy.academy_minsk_movie.network.dto.MovieDto;
import com.android.academy.academy_minsk_movie.network.dto.PopularMovieDto;

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
                .enqueue(new Callback<PopularMovieDto>() {
                    @Override
                    public void onResponse(@NotNull Call<PopularMovieDto> call, @NotNull Response<PopularMovieDto> response) {
                        List<MovieDto> resultList;
                        if (response.body() != null) {

                            resultList = response.body().getPopularMovieList();

                            for (MovieDto result : resultList) {
                                movieList.add(
                                        new Movie(
                                                result.getId(),
                                                result.getTitle(),
                                                result.getOverview(),
                                                POSTER_BASE_URL + result.getPosterPath(),
                                                BACKDROP_BASE_URL + result.getBackdropPath(),
                                                result.getReleaseDate()
                                        ));
                            }

                            responseListener.updateMovieList(movieList);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<PopularMovieDto> call, @NotNull Throwable t) {

                    }
                });
    }

}
