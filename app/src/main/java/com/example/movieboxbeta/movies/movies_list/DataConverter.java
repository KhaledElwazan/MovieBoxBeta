package com.example.movieboxbeta.movies.movies_list;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class DataConverter {


    @TypeConverter
    public List<Integer> stringToGenreID(String value) {
        String[] langs = value.split(",");

        List<Integer> genreIds = new ArrayList<>();


        for (String t : langs) {
            genreIds.add(Integer.parseInt(t));
        }

        return genreIds;
    }

    @TypeConverter
    public String genreIDToString(List<Integer> genreIds) {
        String value = "";

        for (Integer lang : genreIds)
            value += lang.toString() + ",";

        return value;
    }

}