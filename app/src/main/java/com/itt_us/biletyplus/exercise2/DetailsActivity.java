package com.itt_us.biletyplus.exercise2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private static final String trailerURL = "https://www.youtube.com/watch?v=6ZfuNTqbHE8";

    private Button movieTrailerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_recycler_view_item);
//        setTitle("Academy Minsk Movies");

        movieTrailerButton = findViewById(R.id.details_btn_trailer);

        movieTrailerButton.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailerURL))));
    }

}
