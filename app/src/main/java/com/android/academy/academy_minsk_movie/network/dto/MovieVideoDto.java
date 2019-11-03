package com.android.academy.academy_minsk_movie.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideoDto {

    @SerializedName("results")
    @Expose
    private List<VideoUrlDto> videoDetails;

    public List<VideoUrlDto> getVideoDetails() {
        return videoDetails;
    }

}
