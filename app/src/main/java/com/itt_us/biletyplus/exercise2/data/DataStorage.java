package com.itt_us.biletyplus.exercise2.data;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {

    private static DataStorage instance;

    private DataStorage() {
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public List<Movie> getMovieList() {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie("Title1", "Overview"));
        list.add(new Movie("Title2", "Overview"));
        list.add(new Movie("Title3", "Overview"));
        list.add(new Movie("Title4", "Overview"));
        list.add(new Movie("Title5", "Overview"));
        list.add(new Movie("Title6", "Overview"));
        return list;
    }
}
