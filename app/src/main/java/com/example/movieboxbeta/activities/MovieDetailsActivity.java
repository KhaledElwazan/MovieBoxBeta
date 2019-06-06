package com.example.movieboxbeta.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieboxbeta.R;
import com.example.movieboxbeta.movies.RetrofitClientInstance;
import com.example.movieboxbeta.movies.movie_details.GetDataServiceForDetails;
import com.example.movieboxbeta.movies.movie_details.MovieDetails;
import com.example.movieboxbeta.movies.movie_reviews.GetDataServiceForReviews;
import com.example.movieboxbeta.movies.movie_reviews.MovieReviews;
import com.example.movieboxbeta.movies.movies_list.Movie;
import com.example.movieboxbeta.movies.videos.GetDataServiceForVideos;
import com.example.movieboxbeta.movies.videos.Results;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private boolean isFavorited = false;
    private Context context;
    private FloatingActionButton favorite;
    private Movie movie;
    private ImageView poster;

    // TODO: create gui and infrastructure for the MovieDetails Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        this.context = this;


        Bundle b = getIntent().getExtras();
        movie = null;
        if (b != null) {
            movie = (Movie) b.getSerializable("movie");


            // setting movie poster
            poster = findViewById(R.id.posterInDetailsFragment);
            new AsyncTask<String, Void, Bitmap>() {

                @SuppressLint("LongLogTag")
                @Override
                protected Bitmap doInBackground(String... strings) {

                    Bitmap bitmap = null;

                    try {
                        InputStream inputStream = new URL(strings[0]).openStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);

                    } catch (IOException e) {
                        Log.e("loading poster in movie details", e.toString());
                    }

                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    poster.setImageBitmap(bitmap);
                }
            }.execute(movie.getPosterURL());


            // setting movie title
            setTitle(movie.getTitle());

            //setting release date
            TextView rDate = findViewById(R.id.releaseDate), voteAvg = findViewById(R.id.voteAverage);

            rDate.setText(movie.getReleaseDate());
            voteAvg.setText(movie.getVoteAverage().toString());

// loading details
            {
                GetDataServiceForDetails service = RetrofitClientInstance.getRetrofitInstance(getString(R.string.baseURL)).create(GetDataServiceForDetails.class);
                Call<MovieDetails> call = service.getMovieDetails(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                        System.out.println(response.toString());

                        if (response.isSuccessful()) {

                        }

                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });
            }



// loading reviews
            {

                GetDataServiceForReviews service = RetrofitClientInstance.getRetrofitInstance(getString(R.string.baseURL)).create(GetDataServiceForReviews.class);
                Call<MovieReviews> call = service.getMovieReviews(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<MovieReviews>() {
                    @Override
                    public void onResponse(Call<MovieReviews> call, Response<MovieReviews> response) {

                        System.out.println(response.toString());

                        if (response.isSuccessful()) {

                        }

                    }

                    @Override
                    public void onFailure(Call<MovieReviews> call, Throwable t) {

                    }
                });
            }

            //loading videos

            {
                GetDataServiceForVideos service = RetrofitClientInstance.getRetrofitInstance(getString(R.string.baseURL)).create(GetDataServiceForVideos.class);
                Call<Results> call = service.getMovieVideos(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {

                        System.out.println(response.toString());

                        if (response.isSuccessful()) {

                        }

                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {

                    }
                });
            }



        } else {// TODO error while loading movie details

        }


    }
}
