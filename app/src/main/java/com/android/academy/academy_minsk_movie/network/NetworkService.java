package com.android.academy.academy_minsk_movie.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.android.academy.academy_minsk_movie.network.api.TmdbServiceApi;

public class NetworkService {

    public static final String TMDB_API_KEY = "873215d3ea53e93d91e123cf6cbd0db5";
    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/";

    private static NetworkService instance;

    private Retrofit retrofit;

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    private void getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public TmdbServiceApi getJsonApi() {
        return retrofit.create(TmdbServiceApi.class);
    }

}
