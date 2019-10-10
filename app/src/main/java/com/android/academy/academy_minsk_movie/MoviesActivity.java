package com.android.academy.academy_minsk_movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesActivity extends AppCompatActivity implements MoviesAdapter.OnItemClickListener {

    private static final String ADVERTISING_URL = "https://www.youtube.com/playlist?list=PLH434_oX84wNp7NYYdFV5JqJozpWXIjlA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        RecyclerView recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);

        MoviesAdapter adapter = new MoviesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(MoviesAdapter.ITEM_VIEW_TYPE_ADVERTISING, 1);
    }

//    @Override
//    public void onBackPressed() {
//        new MaterialAlertDialogBuilder(this)
//                .setTitle(getString(R.string.exit_alert_title))
//                .setMessage(getString(R.string.exit_alert_message))
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, (arg0, arg1) ->
//                        MoviesActivity.super.onBackPressed()).create().show();
//    }

    @Override
    public void onItemClick(int position) {
//        startActivity(DetailsActivity.newIntent(this, position));

        Fragment detailsFragment = DetailsFragment.newInstance(position);
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.container, detailsFragment)
                .commit();
    }

    @Override
    public void onAdvertisingClick() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ADVERTISING_URL)));
    }

}
