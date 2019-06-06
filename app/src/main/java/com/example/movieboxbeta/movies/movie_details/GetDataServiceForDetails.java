package com.example.movieboxbeta.movies.movie_details;


import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataServiceForDetails {


    @PUT("{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String movie_id, @Query("api_key") String apikey);

}
