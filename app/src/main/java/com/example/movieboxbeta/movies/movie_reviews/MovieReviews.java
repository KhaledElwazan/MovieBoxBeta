
package com.example.movieboxbeta.movies.movie_reviews;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieReviews implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
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
     * @param results
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public MovieReviews(Integer id, Integer page, List<Result> results, Integer totalPages, Integer totalResults) {
        super();
        this.id = id;
        this.page = page;
        this.results = results;
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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public MovieReviews withResults(List<Result> results) {
        this.results = results;
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
