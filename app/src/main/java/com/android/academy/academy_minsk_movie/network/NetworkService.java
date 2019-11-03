package com.android.academy.academy_minsk_movie.network;

import com.android.academy.academy_minsk_movie.network.api.TmdbServiceApi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    public static final String TMDB_API_KEY = "873215d3ea53e93d91e123cf6cbd0db5";
    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/";

    private static NetworkService instance;
    private static int pageNumber = 1;

    private Retrofit retrofit;
    private OkHttpClient client;

    private NetworkService() {
        createClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build();
    }

    public static NetworkService getInstance() {
        if (instance == null) {
            instance = new NetworkService();
        }
        return instance;
    }

    private void createClient() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("page", String.valueOf(pageNumber))
                                .build();

                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();
        pageNumber++;
    }

    public TmdbServiceApi getJsonApi() {
        return retrofit.create(TmdbServiceApi.class);
    }

}
