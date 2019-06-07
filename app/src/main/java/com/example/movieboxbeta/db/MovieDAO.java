package com.example.movieboxbeta.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.movieboxbeta.movies.movies_list.Movie;

import java.util.List;

@Dao
public interface MovieDAO {


    @Query("SELECT * FROM " + Constants.DB_TABLE)
    List<Movie> getAll();


    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);

}