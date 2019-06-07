package com.example.movieboxbeta.movies;

import com.example.movieboxbeta.movies.movie_details.MovieDetails;
import com.example.movieboxbeta.movies.movie_reviews.MovieReviews;
import com.example.movieboxbeta.movies.movies_list.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {



    @GET("popular")
    Call<Results> getPopular(@Query("api_key") String apikey);


    @GET("top_rated")
    Call<Results>getTopRated(@Query("api_key") String apikey);

    @GET("upcoming")
    Call<Results>getUpComing(@Query("api_key") String apikey);

    @GET("latest")
    Call<Results>getLatest(@Query("api_key") String apikey);


    @GET("now_playing")
    Call<Results>getNowPlaying(@Query("api_key") String apikey);


    @GET("{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String apikey);


    @GET("{movie_id}/videos")
    Call<com.example.movieboxbeta.movies.videos.Results> getMovieVideos(@Path("movie_id") String movie_id, @Query("api_key") String apikey);

    @GET("{movie_id}/reviews")
    Call<MovieReviews> getMovieReviews(@Path("movie_id") String movie_id, @Query("api_key") String apikey);

}