package com.android.academy.academy_minsk_movie.data.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.academy.academy_minsk_movie.data.model.Movie;

@Database(entities = Movie.class, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static final String MOVIE_DB_NAME = "Movie.db";
    private static MovieDatabase instance;

    public static MovieDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, MovieDatabase.class, MOVIE_DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
