
package com.example.movieboxbeta.movies.movie_reviews;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("url")
    @Expose
    private String url;
    private final static long serialVersionUID = 2425506906007858792L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Review() {
    }

    /**
     * 
     * @param content
     * @param id
     * @param author
     * @param url
     */
    public Review(String id, String author, String content, String url) {
        super();
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Review withId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Review withAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Review withContent(String content) {
        this.content = content;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Review withUrl(String url) {
        this.url = url;
        return this;
    }

}
