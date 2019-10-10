package com.android.academy.academy_minsk_movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.academy.academy_minsk_movie.data.DataStorage;
import com.android.academy.academy_minsk_movie.data.Movie;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class DetailsFragment extends Fragment {

    private static final String ITEM_POSITION = "position";

    private ImageView backdropImageView;
    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView overviewTextView;
    private TextView releaseDateTextView;
    private MaterialButton movieTrailerButton;
    private Movie movie;
    private int currentPosition;

    static Fragment newInstance(int position) {
        Fragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ITEM_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if (savedInstanceState != null) {
//            currentPosition = savedInstanceState.getInt(ITEM_POSITION);
//        }

        init(view);
        setContent(movie);
        setListeners();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ITEM_POSITION, currentPosition);
    }

    private void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            int position = args.getInt(ITEM_POSITION);
            movie = DataStorage.getInstance().getMovieList().get(position);
        }

        backdropImageView = view.findViewById(R.id.details_iv_backdrop);
        posterImageView = view.findViewById(R.id.details_iv_poster);
        titleTextView = view.findViewById(R.id.details_tv_title);
        overviewTextView = view.findViewById(R.id.details_tv_overview_text);
        releaseDateTextView = view.findViewById(R.id.details_tv_released_date);
        movieTrailerButton = view.findViewById(R.id.details_btn_trailer);
    }

    private void setContent(Movie movie) {
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
        movieTrailerButton.setOnClickListener(v -> showMovieTrailer(movie.getTrailerUrl()));
    }

    private void showMovieTrailer(String trailerUrl) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl)));
    }

}
