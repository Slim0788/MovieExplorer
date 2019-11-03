package com.android.academy.academy_minsk_movie.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopularMovieDto {

    @SerializedName("results")
    @Expose
    private List<MovieDto> popularMovieList;

    public List<MovieDto> getPopularMovieList() {
        return popularMovieList;
    }

}
