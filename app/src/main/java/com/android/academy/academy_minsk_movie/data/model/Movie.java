package com.android.academy.academy_minsk_movie.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie {

    @PrimaryKey
    private int id;
    private String title;
    private String overview;
    private String poster;
    private String backdrop;
    private String releaseDate;

    public Movie(int id, String title, String overview, String poster, String backdrop, String releaseDate) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster = poster;
        this.backdrop = backdrop;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster() {
        return poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}
