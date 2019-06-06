
package com.example.movieboxbeta.movies.videos;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;
    private final static long serialVersionUID = 783974421779319256L;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Results withId(Integer id) {
        this.id = id;
        return this;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public Results withResults(List<Video> videos) {
        this.videos = videos;
        return this;
    }

}
