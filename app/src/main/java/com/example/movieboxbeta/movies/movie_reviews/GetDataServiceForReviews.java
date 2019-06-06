package com.example.movieboxbeta.movies.movie_reviews;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataServiceForReviews {


    @PUT("{movie_id}/reviews")
    Call<MovieReviews> getMovieReviews(@Path("movie_id") String movie_id,@Query("api_key") String apikey);


}
