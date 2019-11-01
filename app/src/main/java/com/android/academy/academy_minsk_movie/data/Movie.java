package com.android.academy.academy_minsk_movie.data;

public class Movie {

    private String title;
    private String overview;
    private String posterRes;
    private String backdropRes;
    private String releaseDate;
    private String trailerUrl;

    Movie(String title, String overview, String posterRes, String backdropRes, String releaseDate, String trailerUrl) {
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

    public String getPosterRes() {
        return posterRes;
    }

    public String getBackdropRes() {
        return backdropRes;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }
}
