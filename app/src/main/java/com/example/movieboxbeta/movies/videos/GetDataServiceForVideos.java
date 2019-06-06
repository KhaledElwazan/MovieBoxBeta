package com.example.movieboxbeta.movies.videos;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataServiceForVideos {


    @PUT("{movie_id}/videos")
    Call<Results> getMovieVideos(@Path("movie_id") String movie_id, @Query("api_key") String apikey);
}
