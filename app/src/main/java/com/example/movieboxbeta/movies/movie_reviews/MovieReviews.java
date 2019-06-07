
package com.example.movieboxbeta.movies.movie_reviews;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieReviews implements Serializable
{
    @Override
    public String toString() {
        return "MovieReviews{" +
                "id=" + id +
                ", page=" + page +
                ", reviews=" + reviews +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    private final static long serialVersionUID = 261879291557794642L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MovieReviews() {
    }

    /**
     * 
     * @param id
     * @param reviews
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public MovieReviews(Integer id, Integer page, List<Review> reviews, Integer totalPages, Integer totalResults) {
        super();
        this.id = id;
        this.page = page;
        this.reviews = reviews;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovieReviews withId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public MovieReviews withPage(Integer page) {
        this.page = page;
        return this;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public MovieReviews withResults(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public MovieReviews withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public MovieReviews withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

}
