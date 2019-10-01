package com.itt_us.biletyplus.exercise2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        recyclerView = findViewById(R.id.movies_recycler_view);

        adapter = new MoviesAdapter();
        recyclerView.setAdapter(adapter);
    }
}
