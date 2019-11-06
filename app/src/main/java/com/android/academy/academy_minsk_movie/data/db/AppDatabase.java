package com.android.academy.academy_minsk_movie.data.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.android.academy.academy_minsk_movie.App;
import com.android.academy.academy_minsk_movie.data.model.Movie;

@Database(entities = Movie.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String MOVIE_DB_NAME = "Movie.db";

    private static AppDatabase instance;

    public abstract MovieDao movieDao();
    public abstract VideoDao videoDao();

    public static AppDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, AppDatabase.class, MOVIE_DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
