package com.android.academy.academy_minsk_movie.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Video {

    @PrimaryKey
    private int movieId;
    private String videoUrlKey;

    public int getMovieId() {
        return movieId;
    }

    public String getVideoUrlKey() {
        return videoUrlKey;
    }
}
