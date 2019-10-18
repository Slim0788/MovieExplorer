package com.android.academy.academy_minsk_movie.ui.gallery_movies_details;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.android.academy.academy_minsk_movie.data.DataStorage;
import com.android.academy.academy_minsk_movie.ui.movie_details.DetailsFragment;

public class GalleryDetailsAdapter extends FragmentStatePagerAdapter {

    private int movieListSize;

    GalleryDetailsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        movieListSize = DataStorage.getInstance().getMovieList().size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return movieListSize;
    }

}
