package com.android.academy.academy_minsk_movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesFragment extends Fragment implements MoviesAdapter.OnItemClickListener {

    private static final String ADVERTISING_URL = "https://www.youtube.com/playlist?list=PLH434_oX84wNp7NYYdFV5JqJozpWXIjlA";

    static Fragment newInstance() {
        return new MoviesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);

        MoviesAdapter adapter = new MoviesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(MoviesAdapter.ITEM_VIEW_TYPE_ADVERTISING, 1);

    }

    @Override
    public void onItemClick(int position) {
        Fragment detailsFragment = DetailsFragment.newInstance(position);
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, detailsFragment)
                .commit();
    }

    @Override
    public void onAdvertisingClick() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ADVERTISING_URL)));
    }

}
