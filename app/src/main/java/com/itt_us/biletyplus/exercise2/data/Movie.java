package com.itt_us.biletyplus.exercise2.data;

public class Movie {

    private String title;
    private String overview;
    private String posterRes;
    private String releaseDate;
    private String trailerUrl;

    public Movie(String title, String overview, String posterRes, String releaseDate, String trailerUrl) {
        this.title = title;
        this.overview = overview;
        this.posterRes = posterRes;
        this.releaseDate = releaseDate;
        this.trailerUrl = trailerUrl;
    }

    public Movie(String title, String overview) {
        this.title = title;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterRes() {
        return posterRes;
    }

    public void setPosterRes(String posterRes) {
        this.posterRes = posterRes;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}
