package com.example.movieboxbeta.movies.movies_list;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataServiceForList {



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

}