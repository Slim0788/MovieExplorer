package com.android.academy.academy_minsk_movie.data.db;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.academy.academy_minsk_movie.data.model.Video;

public interface VideoDao {

    @Query("SELECT * FROM Video WHERE movieId = :movieId")
    Video getVideoByMovieId(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Video video);

    @Query("DELETE FROM Video")
    void deleteAll();
}
