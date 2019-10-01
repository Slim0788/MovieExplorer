package com.android.academy.academy_minsk_movie.data;

public class Movie {

    private String title;
    private String overview;
//    @DrawableRes
    private int posterRes;
//    @DrawableRes
    private int backdropRes;
    private String releaseDate;
    private String trailerUrl;

    Movie(String title, String overview, int posterRes, int backdropRes, String releaseDate, String trailerUrl) {
        this.title = title;
        this.overview = overview;
        this.posterRes = posterRes;
        this.backdropRes = backdropRes;
        this.releaseDate = releaseDate;
        this.trailerUrl = trailerUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public int getPosterRes() {
        return posterRes;
    }

    public int getBackdropRes() {
        return backdropRes;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }
}
