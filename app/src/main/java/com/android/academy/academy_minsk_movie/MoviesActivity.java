package com.android.academy.academy_minsk_movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MoviesActivity extends AppCompatActivity implements MoviesAdapter.OnItemClickListener {

    public static final String ITEM_POSITION = "position";

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new MoviesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(R.layout.activity_movies_recycler_view_advertising, 1);
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.exit_alert_title))
                .setMessage(getString(R.string.exit_alert_message))
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) ->
                        MoviesActivity.super.onBackPressed()).create().show();
    }

    @Override
    public void onItemClick(int position) {
        showDetails(position);
    }

    private void showDetails(int position) {
        if (position == MoviesAdapter.ADVERTISING_POSITION) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.advertising_url))));
//            Toast.makeText(getApplicationContext(), "Это левый элемент списка", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(ITEM_POSITION, position);
            startActivity(intent);
        }

    }

}
