package com.android.academy.academy_minsk_movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.android.academy.academy_minsk_movie.data.DataStorage;
import com.android.academy.academy_minsk_movie.data.Movie;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    private ImageView backdropImageView;
    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView overviewTextView;
    private TextView releaseDateTextView;
    private MaterialButton movieTrailerButton;
    private Movie movie;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt(MoviesActivity.ITEM_POSITION);
        } else {
            onBackPressed();
        }

        init();
        setContent();
        setListeners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        movie = DataStorage.getInstance().getMovieList().get(position);

        backdropImageView = findViewById(R.id.details_iv_backdrop);
        posterImageView = findViewById(R.id.details_iv_poster);
        titleTextView = findViewById(R.id.details_tv_title);
        overviewTextView = findViewById(R.id.details_tv_overview_text);
        releaseDateTextView = findViewById(R.id.details_tv_released_date);
        movieTrailerButton = findViewById(R.id.details_btn_trailer);
    }

    private void setContent() {
        Glide.with(backdropImageView)
                .load(movie.getBackdropRes())
                .into(backdropImageView);
        Glide.with(posterImageView)
                .load(movie.getPosterRes())
                .into(posterImageView);
        titleTextView.setText(movie.getTitle());
        overviewTextView.setText(movie.getOverview());
        releaseDateTextView.setText(movie.getReleaseDate());
    }

    private void setListeners() {
        movieTrailerButton.setOnClickListener(v -> showMovieTrailer());
    }

    private void showMovieTrailer() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getTrailerUrl())));
    }

}
