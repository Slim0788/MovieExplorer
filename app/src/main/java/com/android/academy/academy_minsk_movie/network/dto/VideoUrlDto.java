package com.android.academy.academy_minsk_movie.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoUrlDto {

    @SerializedName("key")
    @Expose
    private String videoUrlKey;

    public String getVideoUrlKey() {
        return videoUrlKey;
    }

}
