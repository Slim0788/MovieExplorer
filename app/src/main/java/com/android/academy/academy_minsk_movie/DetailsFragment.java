package com.android.academy.academy_minsk_movie;

import android.app.Activity;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailsFragment extends Fragment {

    private static final String ITEM_POSITION = "position";

    private ImageView backdropImageView;
    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView overviewTextView;
    private TextView releaseDateTextView;
    private MaterialButton movieTrailerButton;
    private FloatingActionButton fab;

    static Fragment newInstance(int position) {
        // Возвращаем экземпляр фрагмента DetailsFragment с переданными ему аргументами

        Fragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ITEM_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_details, container, false);
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = 0;

        // Проверьем, есть ли аргументы, переданные фрагменту.
        Bundle args = getArguments();
        if (args != null) {
            position = args.getInt(ITEM_POSITION);
        }

        // Инициализируем данные фильма
        Movie movie = DataStorage.getInstance().getMovieList().get(position);

        setContent(movie);

        // Вешаем слушатель на кнопку
        movieTrailerButton.setOnClickListener(v ->
                showMovieTrailer(movie.getTrailerUrl()));

        fab.setOnClickListener(v ->
                showMovieTrailer(movie.getTrailerUrl()));

    }

    private void init(View view) {
        Activity root = getActivity();
        if (root != null){
            fab = root.findViewById(R.id.floatingActionButton);
        }
        backdropImageView = view.findViewById(R.id.details_iv_backdrop);
        posterImageView = view.findViewById(R.id.details_iv_poster);
        titleTextView = view.findViewById(R.id.details_tv_title);
        overviewTextView = view.findViewById(R.id.details_tv_overview_text);
        releaseDateTextView = view.findViewById(R.id.details_tv_released_date);
        movieTrailerButton = view.findViewById(R.id.details_btn_trailer);
    }

    private void setContent(Movie movie) {

        // Сетим данные во вьюхи
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

    private void showMovieTrailer(String trailerUrl) {
        // Переходим на YouTube

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl)));
    }

}
