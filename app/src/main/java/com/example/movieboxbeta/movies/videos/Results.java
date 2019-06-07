
package com.example.movieboxbeta.movies.videos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Results implements Serializable
{
    private final static long serialVersionUID = -8270249560984899830L;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    /**
     * No args constructor for use in serialization
     *
     */
    public Results() {
    }

    /**
     *
     * @param id
     * @param videos
     */
    public Results(Integer id, List<Video> videos) {
        super();
        this.id = id;
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Results{" +
                "id=" + id +
                ", videos=" + videos +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}