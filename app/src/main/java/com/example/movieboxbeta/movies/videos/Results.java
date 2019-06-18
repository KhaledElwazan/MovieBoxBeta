
package com.example.movieboxbeta.movies.videos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Results implements Serializable
{
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = -2990944167726625608L;
    @SerializedName("results")
    @Expose
    private List<Video> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Results() {
    }

    /**
     *
     * @param id
     * @param results
     */
    public Results(Integer id, List<Video> results) {
        super();
        this.id = id;
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Results{" +
                "id=" + id +
                ", results=" + results +
                '}';
    }
}