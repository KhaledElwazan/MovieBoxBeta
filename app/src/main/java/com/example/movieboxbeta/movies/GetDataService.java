package com.example.movieboxbeta.movies;

import com.example.movieboxbeta.movies.movie_details.MovieDetails;
import com.example.movieboxbeta.movies.movie_reviews.MovieReviews;
import com.example.movieboxbeta.movies.movies_list.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {


    @GET("movie/popular")
    Call<Results> getPopular(@Query("api_key") String apikey);


    @GET("movie/top_rated")
    Call<Results>getTopRated(@Query("api_key") String apikey);

    @GET("movie/upcoming")
    Call<Results>getUpComing(@Query("api_key") String apikey);

    @GET("movie/latest")
    Call<Results>getLatest(@Query("api_key") String apikey);


    @GET("movie/now_playing")
    Call<Results>getNowPlaying(@Query("api_key") String apikey);


    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String apikey);


    @GET("movie/{movie_id}/videos")
    Call<com.example.movieboxbeta.movies.videos.Results> getMovieVideos(@Path("movie_id") String movie_id, @Query("api_key") String apikey);

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviews> getMovieReviews(@Path("movie_id") String movie_id, @Query("api_key") String apikey);


    @GET("search/movie")
    Call<Results> getSearchByQuery(@Query("api_key") String apikey, @Query("query") String query);

}