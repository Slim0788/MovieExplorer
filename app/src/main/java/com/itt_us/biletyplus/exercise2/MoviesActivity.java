package com.itt_us.biletyplus.exercise2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesActivity extends AppCompatActivity {

    public static final String POSITION = "position";

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        recyclerView = findViewById(R.id.movies_recycler_view);

        adapter = new MoviesAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void showDetails() {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(POSITION, 0);
        startActivity(intent);
    }
}
